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
import internal.GlobalVariable
import io.appium.java_client.MobileElement

import org.openqa.selenium.Keys as Keys


Mobile.callTestCase(findTestCase('Test Cases/ONT/Channel Bandwidth/OL_66 Move to Channel Bandwidth'), null, FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()

MobileElement bandWidth = ez.find('Bandwidth', 1)
MobileElement channel = ez.find('Channel', 1)
MobileElement transmitPower = ez.find('Transmit power', 1)
String currentBandWidth = bandWidth.text
String currentChannel = channel.text
String currentTransmitPower = transmitPower.text
String newBandWidth = (currentBandWidth == '20MHz') ? '40MHz' : '20MHz'
String newChannel = (currentChannel == 'Auto') ? '1' : 'Auto'
String newTransmitPower = (currentTransmitPower == '75') ? '100' : '75'

println 'Change Bandwidth ' + currentBandWidth + ' to ' + newBandWidth
println 'Change Channel ' + currentChannel + ' to ' + newChannel
println 'Change Transmit Power ' + currentTransmitPower + ' to ' + newTransmitPower

bandWidth.click()
ez.tapElementByText(newBandWidth)
channel.click()
ez.tapElementByText(newChannel)
transmitPower.click()
ez.tapElementByText(newTransmitPower)
ez.tapElementByText('Lưu')

// Verify msg
Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 120)
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Cấu hình Mạng'), 10)
ez.tapElementByText('Thay đổi Channel Bandwidth')

bandWidth = ez.find('Bandwidth', 1)
channel = ez.find('Channel', 1)
transmitPower = ez.find('Transmit power', 1)
assert bandWidth.text == newBandWidth
assert channel.text == newChannel
assert transmitPower.text == newTransmitPower

//if(GlobalVariable.connectMode == 'remote') {
//	// Verify msg
//	Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 120)
//	ez.tapElementByText('Xác nhận')
//	Mobile.waitForElementPresent(ez.createTestObjectFromText('Cấu hình Mạng'), 10)
//	ez.tapElementByText('Thay đổi Channel Bandwidth')
//	
//	bandWidth = ez.find('Bandwidth', 1)
//	channel = ez.find('Channel', 1)
//	transmitPower = ez.find('Transmit power', 1)
//	assert bandWidth.text == newBandWidth
//	assert channel.text == newChannel 
//	assert transmitPower.text == newTransmitPower
//} 
//else if(GlobalVariable.connectMode == 'local'){
//	Mobile.verifyElementExist(findTestObject('Object Repository/ONT/Network config/Message/msg_configSuccess_local'), 120)
//	ez.tapElementByText('Xác nhận')
//	Mobile.verifyElementExist(findTestObject('Object Repository/ONT/Connect device/input_serialNumber'), 30)
//}
 
