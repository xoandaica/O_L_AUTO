import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import base.EzAction
import internal.GlobalVariable
import io.appium.java_client.MobileElement
import one.telco.Randomv



WebUI.callTestCase(findTestCase('Wifi Config/Move to Wifi List'), null, FailureHandling.STOP_ON_FAILURE)
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
String newName = meshSerial[-6..-1] + '_UwU_' + Randomv.getHexStringFromTimestamp().substring(6, 10)
ez.setTextFriendByText('Wireless 2.4G', newName, 1)
ez.tapElementByText('Lưu')

Mobile.delay(20)
Mobile.waitForElementPresent(ez.createTestObjectFromText('Xác nhận'), 120)
assert ez.findContains('Lưu thành công', 0) != null

//Bấm tiếp tục sẽ được đưa về màn hình home
ez.tapElementByText('Xác nhận')
Mobile.closeApplication()

WebUI.callTestCase(findTestCase('Wifi Config/Edit Wifi 2.4G'), [('earlyAssert') : 'true', ('ssidNameAssert') : newName], FailureHandling.STOP_ON_FAILURE)


