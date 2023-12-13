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

Mobile.callTestCase(findTestCase('Test Cases/ONT/Connect device/OL_20 Connect ONT Local'), [('newSessionConnect') : true], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Changpws_device/btn_setting_ONT'), 30)

Mobile.tap(findTestObject('Object Repository/Changpws_device/btn_changpws_ONT'), 30)

Mobile.verifyElementExist(findTestObject('Object Repository/Changpws_device/text_title_Changpws_ONT'), 30, FailureHandling.STOP_ON_FAILURE)
 
EzAction ez = new EzAction()
ez.setTextFriendByText('Mật khẩu mới', 'ttcn@99CN', 1)
ez.setTextFriendByText('Nhập lại mật khẩu', 'ttcn@99CN', 1)

Mobile.tap(findTestObject('Object Repository/Changpws_device/btn_Save_ONT'),30)
Mobile.delay(10)
Mobile.verifyElementExist(findTestObject('Changpws_device/text_Confirm_changpws_ONT'), 0, FailureHandling.STOP_ON_FAILURE)
Mobile.verifyElementExist(findTestObject('Changpws_device/text_sucessfull_ONT'), 0, FailureHandling.STOP_ON_FAILURE)
Mobile.tap(findTestObject('Changpws_device/bnt_confirmsucessfull_changpws_ONT'),30)

//Quay về màn Dashboard 
Mobile.verifyElementExist(findTestObject('Object Repository/ONT/Dashboard/wifi2.4G'), 30) 