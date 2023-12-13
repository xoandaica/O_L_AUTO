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
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_21 Verify connect Remote success'), [('newSessionConnect') : 'false'], FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()

ez.findContains('Cài đặt', 0).click()
ez.tapElementByText('Điều khiển thiết bị')
ez.tapElementByText('Factory Reset')

//Xác nhận đã vào màn Reset
Mobile.waitForElementPresent(ez.createTestObjectFromText('Factory Reset'), 30)
Mobile.waitForElementPresent(ez.createTestObjectFromText('Khôi phục cài đặt gốc của thiết bị'), 30)
