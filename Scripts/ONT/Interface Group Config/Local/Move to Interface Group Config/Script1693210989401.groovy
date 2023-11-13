import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import base.EzAction as EzAction
import io.appium.java_client.AppiumDriver as AppiumDriver

// Connect thiết bị qua remote 
Mobile.callTestCase(findTestCase('Test Cases/ONT/Connect device/OL_21 Connect ONT Remote'), null, FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()

Mobile.delay(5)

ez.findContains('Cài đặt', 0).click()

ez.tapElementByText('Cấu hình Mạng')

Mobile.delay(5)

ez.tapElementByText('Cấu hình Interface Group')

Mobile.waitForElementPresent(ez.createTestObjectFromText('Cấu hình Interface Group'), 5)


