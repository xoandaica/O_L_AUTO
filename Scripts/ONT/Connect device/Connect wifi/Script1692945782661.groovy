import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling

import base.EzAction
import internal.GlobalVariable
import io.appium.java_client.MobileElement
import one.telco.Commonv

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.testobject.TestObject
import io.appium.java_client.android.AndroidElement

Mobile.startExistingApplication('com.android.settings')

EzAction ez = new EzAction()
ez.tapElementByText('Kết nối')
ez.tapElementByText('Wi-Fi') 

Mobile.delay(10)
String ONTwifi = GlobalVariable.ONTwifi
String ONTpath = "//*[@text='" + ONTwifi + "']"
MobileElement wifi_name = MobileDriverFactory.driver.findElement(By.xpath(ONTpath)) 
if (wifi_name.isDisplayed() == true) { 
	wifi_name.click() 
}
else {
	Mobile.swipe(380, 2200, 380, 220)
	wifi_name = ez.driver.findElement(By.xpath(ONTpath))
	while(wifi_name.isDisplayed() == false) {
	    Mobile.swipe(380, 2200, 380, 220)
	    wifi_name = ez.driver.findElement(By.xpath(ONTpath))
	}
	wifi_name.click()
}

Mobile.closeApplication()

