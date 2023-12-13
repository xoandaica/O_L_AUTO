import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject

import base.EzAction
import internal.GlobalVariable
import io.appium.java_client.MobileElement

Mobile.startApplication(GlobalVariable.apk, false)
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Device control/Update Firmware/Remote/OL_88 Access Update FW Remote screen success'), null, FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()

//Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/modelName'), 30)
ez.tapFriendByText('Model Name', 1)

ez.tapElementByText(GlobalVariable.modelName)
Mobile.delay(5)

//Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/btn_FW_version'), 30)
ez.tapFriendByText('Firmware version', 1)
Mobile.delay(5)

//Chọn FW
ez.tapElementByText(GlobalVariable.firmwareMesh_remote)

//Chọn thiết bị CAP để update FW 
MobileElement checkbox_CAP = ez.findContainsAndTapOffset('CAP', -42, -42, 0)

TestObject btn_updateFW = findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/btn_updateFW')
Mobile.verifyElementAttributeValue(btn_updateFW, 'clickable', 'true', 10)
Mobile.tap(btn_updateFW, 30)
Mobile.delay(15)

//Xác nhận update FW thành công
Mobile.waitForElementPresent(findTestObject('Object Repository/Mesh/Device/Device control/Update Firmware/txt_update_success'), 60)
ez.tapElementByText('Tiếp tục')

//Check quay về màn Home
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)
Mobile.delay(400)
