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
import org.openqa.selenium.Keys as Keys
import one.telco.Commonv 


if("$newSessionConnect" == 'true') {
	Mobile.startApplication(GlobalVariable.apk, true)
	Commonv.checkLogin()
} 


TestObject serialInputObj = findTestObject('Object Repository/Home/EditText_search_serial')
TestObject searchBtn = findTestObject('Object Repository/Home/ImageView_search_btn')


// Tìm kiếm thiết bị
Mobile.tap(serialInputObj, 0)
Mobile.setText(serialInputObj, GlobalVariable.Serial2, 0)
Mobile.tap(searchBtn, 0)


// Vào màn cấu hình thiết bị 
Mobile.waitForElementPresent(findTestObject('Object Repository/Home/Panel_device'), 30)
Mobile.tap(findTestObject('Object Repository/Home/Panel_device'), 0)


//Skip tutorial
TestObject skipBtn = findTestObject('Object Repository/ONT/Connect device/btn_Skip')
if(Mobile.verifyElementExist(skipBtn, 10, FailureHandling.OPTIONAL)) {
	Mobile.tap(skipBtn, 0)
}


// Kiểm tra đã vào được màn Dashboard 
Mobile.verifyElementExist(findTestObject('Object Repository/ONT/Dashboard/wifi2.4G'), 30) 