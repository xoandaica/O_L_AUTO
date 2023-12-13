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

Mobile.startApplication(GlobalVariable.apk, false)
EzAction ez = new EzAction()

//Vào màn
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_20 Verify connect Local success'), [('newSessionConnect') : 'false'], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Changpws_device/btn_Setting'), 30)

Mobile.tap(findTestObject('Object Repository/Changpws_device/btn_setting_changpws'), 30)

Mobile.verifyElementExist(ez.createTestObjectFromText('Đổi mật khẩu thiết bị'), 30, FailureHandling.STOP_ON_FAILURE)

//Điền form
TestObject newPasswordObj = findTestObject('Object Repository/Changpws_device/newPass')
TestObject newPass_repeat_Obj = findTestObject('Object Repository/Changpws_device/newPass_repeat')
Mobile.tap(newPasswordObj, 30)

Mobile.hideKeyboard()
Mobile.setText(newPasswordObj, 'ttcn@99CN', 30)
Mobile.tap(findTestObject('Object Repository/Changpws_device/click_outside_screen'), 30)

Mobile.tap(newPass_repeat_Obj, 30)
Mobile.hideKeyboard()
Mobile.setText(newPass_repeat_Obj, 'ttcn@99CN', 30)
Mobile.hideKeyboard()

Mobile.tap(findTestObject('Object Repository/Changpws_device/btn_Save'), 30)
Mobile.delay(10)

//Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 30, FailureHandling.STOP_ON_FAILURE)
String txt_saveSuccess = 'Lưu thành công!'
TestObject saveSuccess = ez.createTestObject('//*[contains(@text, "' + txt_saveSuccess + '")]')
Mobile.verifyElementExist(saveSuccess, 30)

Mobile.verifyElementExist(ez.createTestObjectFromText('Xác nhận'), 30, FailureHandling.STOP_ON_FAILURE)
//ez.tapElementByText('Xác nhận')
Mobile.tap(findTestObject('Object Repository/Changpws_device/btn_confirm'), 5)

//Quay về màn Dashboard 
TestObject local_dashboardNav = findTestObject('Object Repository/Mesh/Connect device/Connect local/local_dashboardNav')
Mobile.delay(15)
Mobile.verifyElementExist(local_dashboardNav, 0)