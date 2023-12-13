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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import one.telco.Commonv
import base.EzAction

Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/Connect wifi'), null, FailureHandling.STOP_ON_FAILURE)

Mobile.startApplication(GlobalVariable.apk, false)

Commonv.checkLogin()
EzAction ez = new EzAction()

TestObject device_founded = findTestObject('Object Repository/Mesh/Connect device/Connect local/device_founded')
TestObject btn_connect = findTestObject('Object Repository/Mesh/Connect device/Connect local/btn_connect')
TestObject btn_skip = findTestObject('Object Repository/Mesh/Connect device/Connect local/btn_skip')
TestObject btn_addMesh = findTestObject('Object Repository/Mesh/Connect device/Connect local/btn_addMesh')

//Quét thiết bị
ez.tapElementByText('Quét thiết bị')
ez.tapElementByText('Đã kết nối')

Mobile.delay(5)

//Check tìm thiết bị
if (!Mobile.verifyElementVisible(device_founded, 0)) {
	println('Không tìm thấy thiết bị')
}

//Kết nối
Mobile.tap(btn_connect, 0)
Mobile.delay(5)

String device_pass = GlobalVariable.passGUI

//Thiết bị reset factory => cần đặt lại mật khẩu thiết bị
TestObject title_changePass = findTestObject('Object Repository/Mesh/Connect device/Connect local/title_popup_change_devicePass')
if(Mobile.verifyElementExist(title_changePass, 5, FailureHandling.OPTIONAL)) {
	TestObject newPass = findTestObject('Object Repository/Mesh/Connect device/Connect local/tbx_newPass')
	TestObject newPass_reinput = findTestObject('Object Repository/Mesh/Connect device/Connect local/newPass_reinput')
	
	Mobile.tap(newPass, 30)
	Mobile.setText(newPass, device_pass, 30)
	Mobile.tap(newPass_reinput, 30)
	Mobile.setText(newPass_reinput, device_pass, 30)
	
	Mobile.tap(findTestObject('Object Repository/Mesh/Connect device/Connect local/btn_Save'), 30)
	Mobile.waitForElementPresent(findTestObject('Object Repository/Mesh/Connect device/Connect local/txt_save_successfully'), 30)
	Mobile.tap(findTestObject('Object Repository/Mesh/Connect device/Connect local/btn_save_confirm'), 30)
}

//Thiết bị mất internet => Yêu cầu nhập mật khẩu tài khoản quản trị
TestObject title_inputAdminPass = findTestObject('Object Repository/Mesh/Connect device/Connect local/titlePopup_input_AdminPass')
if(Mobile.verifyElementExist(title_inputAdminPass, 5, FailureHandling.OPTIONAL)) {
	TestObject tbx_AdminPass = findTestObject('Object Repository/Mesh/Connect device/Connect local/tbx_AdminPass')
	TestObject btn_connect_AdminPass = findTestObject('Object Repository/Mesh/Connect device/Connect local/btn_connect_AdminPass')
	
	Mobile.tap(tbx_AdminPass, 30)
	Mobile.setText(tbx_AdminPass, device_pass, 30)
	Mobile.tap(btn_connect_AdminPass, 30)
}

//Skip tutorial
if(Mobile.verifyElementExist(btn_skip, 5, FailureHandling.OPTIONAL)) {
	Mobile.tap(btn_skip, 0)
}

//Xác nhận đang ở màn Dashboard local
Mobile.verifyElementExist(btn_addMesh, 0)
Mobile.delay(5)