import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject

import base.EzAction
import internal.GlobalVariable
import io.appium.java_client.MobileElement

Mobile.startApplication(GlobalVariable.apk, false)

Mobile.callTestCase(findTestCase('Test Cases/Mesh/Device control/Reset/Local/OL_81 Access Reset screen success'), null, FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()

MobileElement checkbox_CAP = ez.findContains('CAP', 0)

checkbox_CAP.click()
	
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Reset/btn_factory_reset'), 30)
Mobile.delay(3)
	
//Check hiển thị popup xác nhận
Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Reset/title_reset_popup'), 30)
Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Reset/txt_reset_popup'), 30)
	
//Chọn Đồng ý và check hiển thị thông báo reset thành công
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Reset/btn_Accept'), 30)
Mobile.waitForElementPresent(findTestObject('Object Repository/Mesh/Device/Device control/Reset/txt_reset_success'), 30)
	
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Reset/btn_confirm'), 30)
	
//Check quay về màn Home
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)
Mobile.delay(300)
