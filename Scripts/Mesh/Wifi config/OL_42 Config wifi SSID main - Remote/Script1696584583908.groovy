import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling

import base.EzAction
import internal.GlobalVariable
import io.appium.java_client.MobileElement
import one.telco.Randomv


 
Mobile.startApplication(GlobalVariable.apk, false)
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_21 Verify connect Remote success'), null, FailureHandling.STOP_ON_FAILURE)
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
Mobile.delay(300)

// Call lại case config để kiểm tra giá trị SSID name đã thay đổi chưa 
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Wifi config/OL_42 Config wifi SSID main - Remote'), [('earlyAssert') : 'true', ('ssidNameAssert') : newName], FailureHandling.STOP_ON_FAILURE)
