import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import base.EzAction
import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import one.telco.Commonv

import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import io.appium.java_client.MobileElement;
import io.appium.java_client.AppiumDriver

// Connect thiết bị qua remote 
Mobile.callTestCase(findTestCase('Test Cases/ONT/Connect device/OL_21 Connect ONT Remote'), null, FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()
AppiumDriver driver = MobileDriverFactory.getDriver()

Mobile.delay(5)
ez.findContains('Cài đặt', 0).click()
ez.tapElementByText('Cấu hình Mạng')
ez.tapElementByText('Cấu hình PON')


Mobile.waitForElementPresent(ez.createTestObjectFromText('Cấu hình PON'), 5)
TestObject popObj = findTestObject('Object Repository/ONT/PON/popup_PON')

MobileElement parentElement = driver.findElement(By.xpath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.view.View[1]/android.view.View[1]/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.view.View[1]/android.widget.ImageView[1]"))
List<MobileElement> listChild= ez.getChild(parentElement)
for (MobileElement element : listChild) {
    println(element.toString())
}