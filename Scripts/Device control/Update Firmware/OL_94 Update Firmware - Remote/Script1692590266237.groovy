import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject

import base.EzAction
import internal.GlobalVariable
import io.appium.java_client.MobileElement

Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/model_name'), 30)
Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/popup_model_name'), 30)
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/btn_model_name'), 30)
Mobile.delay(5)

Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/btn_FW_version'), 30)
Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/popup_FW_version'), 30)
Mobile.delay(5)

//Chọn FW update 005
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/FW_005'), 30)

//Chọn thiết bị CAP để update FW
EzAction ez = new EzAction()
MobileElement checkbox_CAP = ez.findContainsAndTapOffset('CAP', -42, -42, 0)

TestObject btn_updateFW = findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/btn_updateFW')
Mobile.verifyElementAttributeValue(btn_updateFW, 'clickable', 'true', 10)
Mobile.tap(btn_updateFW, 30)
Mobile.delay(15)

//Xác nhận update FW thành công
Mobile.waitForElementPresent(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/txt_update_success'), 60)
Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/btn_continue'), 30)
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/btn_continue'), 30)

//Check quay về màn Home
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)

