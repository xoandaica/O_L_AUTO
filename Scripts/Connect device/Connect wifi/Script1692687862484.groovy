import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

import io.appium.java_client.MobileElement
import base.EzAction
import internal.GlobalVariable
import com.kms.katalon.core.model.FailureHandling

Mobile.startExistingApplication('com.android.settings')

EzAction ez = new EzAction()

TestObject connect = ez.createTestObjectFromText('Connect')
if(Mobile.verifyElementExist(connect, 30, FailureHandling.OPTIONAL)) {
	Mobile.tap(connect, 30)
}

TestObject ket_noi = ez.createTestObjectFromText('Kết nối')
if(Mobile.verifyElementExist(ket_noi, 30, FailureHandling.OPTIONAL)) {
	Mobile.tap(ket_noi, 30)
}

ez.tapElementByText('Wi-Fi')

Mobile.delay(5)

String wifi_nameLocal = "$wifi_nameLocal"
String wifi_passLocal = "$wifi_passLocal"

ez.tapElementByText(wifi_nameLocal)

Mobile.delay(15)
TestObject tbx_Password = findTestObject('Object Repository/Mesh/Connect device/Connect local/tbx_Password')
if(Mobile.verifyElementExist(tbx_Password, 30, FailureHandling.OPTIONAL)) {
	Mobile.tap(tbx_Password, 30)
	Mobile.setText(tbx_Password, wifi_passLocal, 30)
	ez.tapElementByText('Kết nối')
}

Mobile.closeApplication()

