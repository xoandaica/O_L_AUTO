import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject

import base.EzAction
import internal.GlobalVariable
import io.appium.java_client.MobileElement

EzAction ez = new EzAction()

MobileElement checkbox_MRE = ez.findContains('MRE', 0)

if(checkbox_MRE == null) {
	println('Không có thiết bị MRE!')
} else {
	checkbox_MRE.click()
	
	Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Reset/btn_factory_reset'), 30)
	Mobile.delay(3)
	
	//Check hiển thị popup xác nhận
	Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Reset/title_reset_popup'), 30)
	Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Reset/txt_reset_popup'), 30)
	
	//Chọn Đồng ý và check hiển thị thông báo reboot thành công
	Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Reset/btn_Accept'), 30)
	Mobile.waitForElementPresent(findTestObject('Object Repository/Mesh/Device/Device control/Reset/txt_reset_success'), 30)
	
	Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Reset/btn_confirm'), 30)
	
	//Check quay về màn Home
	String txt_hello = 'Xin chào\n' + GlobalVariable.username
	TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
	Mobile.verifyElementExist(hello, 30)
}
