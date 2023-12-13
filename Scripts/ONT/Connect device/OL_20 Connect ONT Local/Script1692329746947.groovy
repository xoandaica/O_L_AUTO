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

import base.EzAction
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import one.telco.Commonv
import io.appium.java_client.android.AndroidElement


// Connect wifi
Mobile.callTestCase(findTestCase('Test Cases/ONT/Connect device/Connect wifi'), null, FailureHandling.STOP_ON_FAILURE)


// Open app
if("$newSessionConnect" == 'true') {
	Mobile.startApplication(GlobalVariable.apk, false)
	Commonv.checkLogin()
}


//Check quét thiết bị
EzAction ez = new EzAction()
ez.tapElementByText('Quét thiết bị')
ez.tapElementByText('Đã kết nối')  
Mobile.waitForElementPresent(findTestObject('Object Repository/ONT/Connect device/btnKetnoi'), 180)
Mobile.delay(3)
Mobile.tap(findTestObject('Object Repository/ONT/Connect device/btnKetnoi'), 0) 


//Thiết bị không có onl token => Yêu cầu nhập mật khẩu tài khoản quản trị
TestObject title_inputAdminPass = findTestObject('Object Repository/Mesh/Connect device/Connect local/titlePopup_input_AdminPass')
if(Mobile.verifyElementExist(title_inputAdminPass, 5, FailureHandling.OPTIONAL)) {
	TestObject tbx_AdminPass = findTestObject('Object Repository/Mesh/Connect device/Connect local/tbx_AdminPass')
	TestObject btn_connect_AdminPass = findTestObject('Object Repository/Mesh/Connect device/Connect local/btn_connect_AdminPass')
	
	Mobile.tap(tbx_AdminPass, 30)
	Mobile.setText(tbx_AdminPass, GlobalVariable.passGUI, 30)
	Mobile.tap(btn_connect_AdminPass, 30)
}


//Skip tutorial
TestObject skipBtn = findTestObject('Object Repository/ONT/Connect device/btn_Skip')
if(Mobile.verifyElementExist(skipBtn, 5, FailureHandling.OPTIONAL)) {
	Mobile.tap(skipBtn, 0)
}


// Kiểm tra đã vào được màn Dashboard
ez = new EzAction()
String model = GlobalVariable.ONTModel
ez.find(model, 0) 
return GlobalVariable.connectMode = 'local'
