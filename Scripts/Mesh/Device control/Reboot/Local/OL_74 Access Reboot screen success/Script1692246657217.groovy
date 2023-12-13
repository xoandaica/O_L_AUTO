import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling

import base.EzAction
import internal.GlobalVariable

Mobile.startApplication(GlobalVariable.apk, false)
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_20 Verify connect Local success'), [('newSessionConnect') : 'false'], FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()

ez.findContains('Cài đặt', 0).click()
ez.tapElementByText('Điều khiển thiết bị')
ez.tapElementByText('Reboot') 

//Xác nhận đã vào màn hình reboot
//Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Reboot/reboot_title'), 0)
//Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Reboot/btn_reboot_device'), 0)
Mobile.waitForElementPresent(ez.createTestObjectFromText('Reboot'), 30)
Mobile.waitForElementPresent(ez.createTestObjectFromText('Khởi động lại thiết bị'), 30)
