import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import base.EzAction
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement

// vào màn hình cấu hình voice
Mobile.callTestCase(findTestCase('Test Cases/ONT/Voice Config/Remote/Move to Voice Config'), null, FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()
AppiumDriver driver
driver = MobileDriverFactory.getDriver()

Mobile.tap(findTestObject('Object Repository/ONT/Voice/global_parameter'), 0)
MobileElement account = ez.findContains('Enable Voice')
List<MobileElement> list = account.findElementsByXPath('./*/*')
println list.size()

MobileElement element1 = list.get(0)
element1.click()

MobileElement element2 = list.get(1)
element2.click()
Mobile.delay(5)
ez.tapElementByText('WAN1')

MobileElement element3 = list.get(3)
element3.click()
Mobile.delay(5)
ez.tapElementByText('BRA-BRAZIL')

MobileElement element4 = list.get(5)
element4.click()
element4.clear()
element4.sendKeys('test.vn')
driver.hideKeyboard()

Mobile.swipe(383, 1000, 383, 400)

MobileElement accountElement = ez.findContains('Use Sip Registrar')
List<MobileElement> listElement = accountElement.findElementsByXPath('./*/*')
println listElement.size()


MobileElement element5 = listElement.get(0)
element5.click()
element5.clear()
element5.sendKeys('test.vn')
driver.hideKeyboard()

MobileElement element6 = listElement.get(1)
element6.click()
element6.clear()
element6.sendKeys('5000')
driver.hideKeyboard()


MobileElement element7 = listElement.get(2)
element7.click()
element7.clear()
element7.sendKeys('1.1.1.1')
driver.hideKeyboard()

MobileElement element8 = listElement.get(3)
element8.click()
element8.clear()
element8.sendKeys('5000')
driver.hideKeyboard()

MobileElement element9 = listElement.get(4)
element9.click()
element9.clear()
element9.sendKeys('test.vn')
driver.hideKeyboard()

MobileElement element10 = listElement.get(5)
element10.click()
element10.clear()
element10.sendKeys('5000')
driver.hideKeyboard()

Mobile.delay(5)
Mobile.tap(findTestObject('Object Repository/ONT/Voice/button_save'), 0)


Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 120)
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Cấu hình mạng'), 10)

