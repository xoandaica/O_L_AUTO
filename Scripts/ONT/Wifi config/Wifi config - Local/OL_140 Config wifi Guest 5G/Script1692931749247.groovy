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
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import base.EzAction
import internal.GlobalVariable
import io.appium.java_client.MobileElement
import one.telco.Randomv

import org.openqa.selenium.Keys as Keys


Mobile.callTestCase(findTestCase('Test Cases/ONT/Connect device/OL_20 Connect ONT Local'), ['newSessionConnect': true], FailureHandling.STOP_ON_FAILURE)
Mobile.callTestCase(findTestCase('Test Cases/ONT/Wifi config/OL_136 Move to Wifi config'), null, FailureHandling.STOP_ON_FAILURE)


EzAction ez = new EzAction() 
// Click WiFi Guest 5G index 5
ez.tapFriendByText('Band Type', 1)
ez.tapElementByText('Wireless 5G')
TestObject wifiGuest = findTestObject('Object Repository/ONT/Network config/Wifi config/wifiGuest_index1')
Mobile.tap(wifiGuest, 0)


// Check Band type
Mobile.delay(5)
MobileElement bandType = ez.find('Wireless 5G')
assert bandType != null


// Kiểm tra giá trị SSID name 
String oldName = Mobile.getText(findTestObject('Object Repository/ONT/Network config/Wifi config/ssidName'), 0)
println 'Current SSID name is ' + oldName

if("$earlyAssert" == 'true') {
	assert oldName == "$ssidNameAssert"
	return
}


// Set new SSID name
String newName = 'testGuest5G' + Randomv.getHexStringFromTimestamp().substring(6, 10)
Mobile.tap(findTestObject('Object Repository/ONT/Network config/Wifi config/ssidName'), 0)
Mobile.setText(findTestObject('Object Repository/ONT/Network config/Wifi config/ssidName'), newName, 5)
ez.tapElementByText('Lưu')
Mobile.delay(10)
ez.findContains('Lưu thành công', 0) 
ez.tapElementByText('Xác nhận')


// Call lại case config để kiểm tra giá trị SSID name đã thay đổi chưa
Mobile.verifyElementExist(findTestObject('Object Repository/Login/img_setting'), 30) 
Mobile.delay(60) 
Mobile.callTestCase(findTestCase('Test Cases/ONT/Wifi config/Wifi config - Local/OL_140 Config wifi Guest 5G'), [('earlyAssert') : 'true', ('ssidNameAssert') : newName], FailureHandling.STOP_ON_FAILURE)
