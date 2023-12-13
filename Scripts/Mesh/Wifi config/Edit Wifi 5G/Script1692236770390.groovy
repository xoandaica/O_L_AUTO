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
import one.telco.Randomv

import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Mesh/Wifi config/OL_41 Move to Wifi List'), null, FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()

//chuyển brand Type
ez.tapElementByText('Wireless 2.4G')
ez.tapElementByText('Wireless 5G')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách Wifi'), 0)

List<MobileElement> wifis = ez.findManyContains('SSID Index', 0)

assert wifis.size() > 0

//View wifi detail
wifis.get(0).click()
MobileElement bandType = ez.find('Wireless 5G')
assert bandType != null

MobileElement ssidName = ez.find('Wireless 5G', 1)
String oldName = ssidName.text

if("$earlyAssert" == 'true') {
	assert oldName == "$ssidNameAssert"
	return
}

println 'current SSID name is ' + oldName

def meshSerial = GlobalVariable.meshSerial
String newName = meshSerial[-6..-1] + '_UwU_' + Randomv.getHexStringFromTimestamp().substring(6, 10)
ez.setTextFriendByText('Wireless 5G', newName, 1)
ez.tapElementByText('Lưu')

Mobile.delay(20)
Mobile.waitForElementPresent(ez.createTestObjectFromText('Tiếp tục'), 120)
assert ez.findContains('Lưu thành công', 0) != null

//Bấm tiếp tục sẽ được đưa về màn hình home
ez.tapElementByText('Tiếp tục')
Mobile.closeApplication()

WebUI.callTestCase(findTestCase('Mesh/Wifi config/Edit Wifi 5G'), [('earlyAssert') : 'true', ('ssidNameAssert') : newName], FailureHandling.STOP_ON_FAILURE)


