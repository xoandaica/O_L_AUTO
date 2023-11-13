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


EzAction ez = new EzAction()
// Click WiFi Guest 2.4G index 1
TestObject wifiGuest = findTestObject('Object Repository/ONT/Network config/Wifi config/wifiGuest_index1')
Mobile.tap(wifiGuest, 0)


// Check Band type
MobileElement bandType = ez.find('Wireless 2.4G')
assert bandType != null


// Kiểm tra giá trị SSID name 
String oldName = Mobile.getText(findTestObject('Object Repository/ONT/Network config/Wifi config/ssidName'), 0)
println 'Current SSID name is ' + oldName

if("$earlyAssert" == 'true') {
	assert oldName == "$ssidNameAssert"
	return
}


// Set new SSID name
String newName = 'testGuest2.4G' + Randomv.getHexStringFromTimestamp().substring(6, 10)
Mobile.tap(findTestObject('Object Repository/ONT/Network config/Wifi config/ssidName'), 0)
Mobile.setText(findTestObject('Object Repository/ONT/Network config/Wifi config/ssidName'), newName, 5)
ez.tapElementByText('Lưu')
Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 120)
ez.tapElementByText('Xác nhận')


// Call lại case config để kiểm tra giá trị SSID name đã thay đổi chưa
Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách WiFi'), 30)
Mobile.callTestCase(findTestCase('Test Cases/ONT/Wifi config/Wifi config - Remote/OL_139 Config wifi Guest 2.4G'), [('earlyAssert') : 'true', ('ssidNameAssert') : newName], FailureHandling.STOP_ON_FAILURE)
