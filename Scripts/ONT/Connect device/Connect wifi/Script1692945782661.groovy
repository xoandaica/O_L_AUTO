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

TestObject ket_noi = ez.createTestObjectFromText('Kết nối')
if(Mobile.verifyElementExist(ket_noi, 10, FailureHandling.OPTIONAL)) {
	Mobile.tap(ket_noi, 5)
}
else {
	TestObject connect = ez.createTestObjectFromText('Connect') 
	Mobile.tap(connect, 5)
}

String wifi_nameLocal = "$wifi_nameLocal"
String wifi_passLocal = "$wifi_passLocal"
 
if(Mobile.verifyElementExist(ez.createTestObjectFromText(wifi_nameLocal), 10, FailureHandling.OPTIONAL)) {
	Mobile.closeApplication()
}

else {
	ez.tapElementByText('Wi-Fi')
	Mobile.delay(10)
	
	// Tìm wifi đúng để connect 
	if(Mobile.verifyElementExist(ez.createTestObjectFromText(wifi_nameLocal), 10, FailureHandling.OPTIONAL)) {
		ez.tapElementByText(wifi_nameLocal)
	}
	else {
		int height = Mobile.getDeviceHeight()
		int height90 = height*90/100
		int height50 = height*50/100
		int width = Mobile.getDeviceWidth()
		int width50 = width*50/100
		Mobile.swipe(width50, height90, width50, height50) 
		Mobile.delay(3)  
		while ((Mobile.verifyElementExist(ez.createTestObjectFromText(wifi_nameLocal), 10, FailureHandling.OPTIONAL)) == false) {
			Mobile.swipe(width50, height90, width50, height50)  
			Mobile.delay(3)
		}
		ez.tapElementByText(wifi_nameLocal)
	}
	
	// Nhập password (nếu có)
	Mobile.delay(10)
	TestObject tbx_Password = findTestObject('Object Repository/Mesh/Connect device/Connect local/tbx_Password')
	if(Mobile.verifyElementExist(tbx_Password, 10, FailureHandling.OPTIONAL)) {
		Mobile.tap(tbx_Password, 5)
		Mobile.setText(tbx_Password, wifi_passLocal, 5)
		ez.tapElementByText('Kết nối')
	}
	
	Mobile.closeApplication()
}