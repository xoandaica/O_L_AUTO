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


// Nếu hiển thị màn hình Nhập mật khẩu quản trị  
TestObject passGUI = findTestObject('Object Repository/ONT/Connect device/nhapPass')
if(Mobile.verifyElementExist(passGUI, 10, FailureHandling.OPTIONAL)) {
	ez.setTextFriendByText('Kết nối thiết bị', GlobalVariable.passGUI, 3)
	ez.tapElementByText('Kết nối')
//	Mobile.setText(findTestObject('Object Repository/ONT/Connect device/input_passGUI'), GlobalVariable.passGUI, 0)
//	Mobile.tap(findTestObject('Object Repository/ONT/Connect device/btnKetnoi'), 0)
}


//Skip tutorial
TestObject skipBtn = findTestObject('Object Repository/ONT/Connect device/btn_Skip')
if(Mobile.verifyElementExist(skipBtn, 10, FailureHandling.OPTIONAL)) {
	Mobile.tap(skipBtn, 0)
}


// Kiểm tra đã vào được màn Dashboard
Mobile.verifyElementExist(findTestObject('Object Repository/ONT/Dashboard/wifi2.4G'), 30) 

