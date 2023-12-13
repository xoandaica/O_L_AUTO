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
import io.appium.java_client.MobileElement
import one.telco.Commonv

import org.openqa.selenium.Keys as Keys
import base.EzAction


Mobile.startApplication(GlobalVariable.apk, false)
Commonv.checkLogin()

Mobile.callTestCase(findTestCase('Test Cases/ONT/Connect device/OL_21 Connect ONT Remote'), [:], FailureHandling.STOP_ON_FAILURE)
Mobile.callTestCase(findTestCase('Test Cases/ONT/LAN Config/LAN config - Remote/OL_133 Move to LAN config'), [:], FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()
// Check data
MobileElement IP_El = ez.findContains('IP Address', 1)
def ip = IP_El.getText()
if(ip == '192.168.1.1') {
	ip = '192.168.1.2'
}else {
	ip = '192.168.1.1'
}
MobileElement SM_El = ez.findContains('Subnet Mask', 1)
def sm = SM_El.getText()
if(sm == '255.255.255.0') {
	sm = '255.255.0.0'
}else {
	sm = '255.255.255.0'
}
 

// Set data & Save
ez.setTextFriendByText('IP Address', ip, 1)
ez.setTextFriendByText('Subnet Mask', sm, 1) 

ez.tapElementByText('Lưu')


// Verify msg 
Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 120) 
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Cấu hình Mạng'), 30)


// Back về Dashboard
Mobile.delay(60) 