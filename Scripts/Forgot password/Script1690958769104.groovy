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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable
import one.telco.Commonv
import one.telco.Database
import base.EzAction
import org.openqa.selenium.Keys as Keys

Mobile.startApplication(GlobalVariable.apk, false)
Commonv.checkNotLogin()
EzAction ez = new EzAction()

//Di chuyển tới màn quên mật khẩu
ez.tapElementByText('Quên mật khẩu')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Quên mật khẩu'), 30)
Mobile.waitForElementPresent(ez.createTestObjectFromText('Gửi'), 30)

//Lấy email và token cũ từ DB
String dataRaw = Database.executeQueryFirstRow('SELECT email, forgot_pwd_token FROM users WHERE username = "' + GlobalVariable.username +'"')
String[] data = dataRaw.split(',')
String email = data[0]
String old_forgot_pwd_token = data[1]

//Điền form
TestObject emailObj = findTestObject('Object Repository/Forgot password/input_email') 
Mobile.tap(emailObj, 0)
Mobile.setText(emailObj, email, 0)
ez.tapElementByText('Gửi')

TestObject resultObj = findTestObject('Object Repository/Forgot password/View_message success or duplicate')
Mobile.verifyElementExist(resultObj, 0)
String message = Mobile.getText(resultObj, 0)

//Kiểm tra lại dữ liệu trong DB
KeywordUtil.logInfo(message)
if(message == 'Gửi yêu cầu thành công. Vui lòng kiểm tra email và làm theo hướng dẫn') {
	String forgot_pwd_token = Database.executeQueryFirstRow('SELECT forgot_pwd_token FROM users WHERE username = "' + GlobalVariable.username +'"')
	assert forgot_pwd_token != old_forgot_pwd_token
}else {
	assert message.contains('Vui lòng kiểm tra email')
}

ez.tapElementByText('Đóng')
//Kiểm tra quay lại màn login
Mobile.waitForElementPresent(ez.createTestObjectFromText('Đăng nhập'), 30)















