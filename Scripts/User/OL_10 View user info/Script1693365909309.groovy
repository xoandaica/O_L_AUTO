import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable
import one.telco.Commonv
import one.telco.Database
import base.EzAction
import org.openqa.selenium.Keys as Keys
import org.apache.commons.lang3.StringUtils;


Mobile.startApplication(GlobalVariable.apk, false)
Commonv.checkLogin()

//Di chuyển tới màn Xem thông tin tài khoản
TestObject userProfile = findTestObject('Object Repository/Home/ImageView_User profile') 
Mobile.tap(userProfile, 0) 
EzAction ez = new EzAction()
Mobile.waitForElementPresent(ez.createTestObjectFromText('Tài khoản'), 30)

// Get các trường trong DB
String queryFullname = 'SELECT fullname FROM users WHERE username = "' + GlobalVariable.username +'"'
String fullname = Database.executeQueryFirstRow(queryFullname)
String queryEmail = 'SELECT email FROM users WHERE username = "' + GlobalVariable.username +'"'
String email = Database.executeQueryFirstRow(queryEmail)
String queryPhone_number = 'SELECT phone_number FROM users WHERE username = "' + GlobalVariable.username +'"'
String phone_number = Database.executeQueryFirstRow(queryPhone_number)

// Kiểm tra có thông tin như trong DB không
TestObject userInfoObj = findTestObject('Object Repository/Account/userInfo') 
String userInfo = Mobile.getAttribute(userInfoObj, 'contentDescription', 0).replaceAll("[\n\r]", "")
println userInfo  
boolean matchFullname = StringUtils.substringBetween(userInfo, "Tên", "Email") == fullname
println matchFullname
boolean matchEmail = StringUtils.substringBetween(userInfo, "Email", "Số điện thoại") == email
println matchEmail
int getIndext = userInfo.lastIndexOf("Số điện thoại") + "Số điện thoại".length()
boolean matchPhone_number = userInfo.substring(getIndext) == phone_number
println matchPhone_number 

assert matchFullname && matchEmail && matchPhone_number