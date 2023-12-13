import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import base.EzAction
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
  

Mobile.callTestCase(findTestCase('Test Cases/ONT/Voice Config/OL_168 Move to Voice Config'), null)
EzAction ez = new EzAction()
AppiumDriver driver
driver = MobileDriverFactory.getDriver()

Mobile.tap(findTestObject('Object Repository/ONT/Voice/sip_parameter'), 0)

MobileElement account = ez.findContains('Sip Account: 0')
List<MobileElement> list = account.findElementsByXPath('./*/*')
println list.size()


MobileElement element1 = list.get(1)
element1.click()
element1.clear()
element1.sendKeys('30')
driver.hideKeyboard()

for (int i=2 ; i<list.size() ; i++)
{

	MobileElement element = list.get(i)
	element.click()
	element.clear()
    element.sendKeys('0362826495')
	driver.hideKeyboard()

}

 
int height90 = (Mobile.getDeviceHeight())*90/100
int height20 = (Mobile.getDeviceHeight())*20/100
int width50 = (Mobile.getDeviceWidth())*50/100
Mobile.swipe(width50, height90, width50, height20)

MobileElement accountElement = ez.findContains('Sip Account: 1')
List<MobileElement> listElement = accountElement.findElementsByXPath('./*/*')
println listElement.size()


MobileElement element3 = listElement.get(1)
element3.click()
element3.clear()
element3.sendKeys('30')
driver.hideKeyboard()

for (int i=2 ; i<listElement.size() ; i++)
{

	MobileElement element = listElement.get(i)
	element.click()
	element.clear()
	element.sendKeys('0362826495')
	driver.hideKeyboard()
	
}

driver.hideKeyboard()

Mobile.delay(5)

//Mobile.tap(findTestObject('Object Repository/ONT/Voice/button_save'), 0)
ez.tapElementByText('Lưu')


Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 120)
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Cấu hình Mạng'), 10)
ez.tapFriendByText('Cấu hình Mạng', -1)

