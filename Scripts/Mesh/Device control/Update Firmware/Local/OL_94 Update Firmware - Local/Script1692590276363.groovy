import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject

import base.EzAction
import internal.GlobalVariable
import io.appium.java_client.MobileElement

Mobile.startApplication(GlobalVariable.apk, false)
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Device control/Update Firmware/Local/OL_88 Access Update FW Local screen success'), null, FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/modelName'), 30)

ez.tapElementByText(GlobalVariable.modelName)

//Chọn file FW
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/btn_select_fileFW'), 30)
Mobile.delay(3)

//Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/FW_local'), 30)
//Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/FW_12ST_SSA04'), 30)

ez.tapElementByText(GlobalVariable.firmwareMesh_local)

Mobile.swipe(383, 1031, 383, 577)

//Chọn thiết bị CAP để update FW
MobileElement checkbox_CAP = ez.findContainsAndTapOffset('CAP', -42, -42, 0)

TestObject btn_updateFW = findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/btn_updateFW')
Mobile.verifyElementAttributeValue(btn_updateFW, 'clickable', 'true', 10)
Mobile.tap(btn_updateFW, 30)

//Xác nhận update FW thành công
Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/txt_update_success'), 30)
Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/btn_continue'), 30)
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/btn_continue'), 30)

//Check quay về màn Home
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)

Mobile.delay(400)