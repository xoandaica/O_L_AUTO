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
import base.EzAction
import io.appium.java_client.MobileElement

import org.openqa.selenium.Keys as Keys

if("$newSession" == 'true') {
	Mobile.startApplication(GlobalVariable.apk, false)
}
Commonv.checkNotLogin()

EzAction ez = new EzAction()

TestObject usernameObj = findTestObject('Object Repository/Login/username')
TestObject passwordObj = findTestObject('Object Repository/Login/password')
TestObject loginBtn = findTestObject('Object Repository/Login/Button_Login')

//Thao tác điền form login
Mobile.tap(usernameObj, 0)
Mobile.setText(usernameObj, "$username", 0)
Mobile.tap(passwordObj, 0)
Mobile.setText(passwordObj, "$password", 0)
Mobile.tap(loginBtn, 0)

//Skip tutorial 
TestObject skipBtn = findTestObject('Object Repository/ONT/Connect device/btn_Skip')
if(Mobile.verifyElementExist(skipBtn, 10, FailureHandling.OPTIONAL)) {
	Mobile.tap(skipBtn, 0)
}

//Có phiên bản mới cập nhật
TestObject title_update_newVersion = findTestObject('Object Repository/Mesh/Connect device/Connect local/title_update_newVersion')
if(Mobile.verifyElementExist(title_update_newVersion, 5, FailureHandling.OPTIONAL)) {
	Mobile.tap(findTestObject('Object Repository/Mesh/Connect device/Connect local/btn_later'), 30)
}

//Xác nhận đang ở màn Home
Mobile.verifyElementExist(findTestObject('Object Repository/Login/img_setting'), 30)
String txt_hello = 'Xin chào\n' + "$username"
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)

