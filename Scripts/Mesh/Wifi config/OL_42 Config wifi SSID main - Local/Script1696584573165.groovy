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
 
Mobile.startApplication(GlobalVariable.apk, false)
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_20 Verify connect Local success'), null, FailureHandling.STOP_ON_FAILURE)
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Wifi config/OL_41 Move to Wifi List'), null, FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()
List<MobileElement> wifis = ez.findManyContains('SSID Index', 0)

assert wifis.size() > 0

//View wifi detail
wifis.get(0).click()
MobileElement bandType = ez.find('Wireless 2.4G')
assert bandType != null

MobileElement ssidName = ez.find('Wireless 2.4G', 1)
String oldName = ssidName.text

if("$earlyAssert" == 'true') {
	assert oldName == "$ssidNameAssert"
	return
}

println 'current SSID name is ' + oldName

def meshSerial = GlobalVariable.meshSerial 
String newName = meshSerial + Randomv.getHexStringFromTimestamp().substring(6, 10)
ez.setTextFriendByText('Wireless 2.4G', newName, 1)
ez.tapElementByText('Lưu')
 
Mobile.waitForElementPresent(ez.createTestObjectFromText('Xác nhận'), 120)
assert ez.findContains('Lưu thành công', 0) != null
ez.tapElementByText('Xác nhận')
Mobile.delay(120)

GlobalVariable.wifiName_Mesh = newName

// Call lại case config để kiểm tra giá trị SSID name đã thay đổi chưa 
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Wifi config/OL_42 Config wifi SSID main - Local'), [('earlyAssert') : 'true', ('ssidNameAssert') : newName], FailureHandling.STOP_ON_FAILURE)
