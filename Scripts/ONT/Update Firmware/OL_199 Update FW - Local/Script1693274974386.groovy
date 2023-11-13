import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import io.appium.java_client.MobileElement

import base.EzAction

EzAction ez = new EzAction()

//Chọn model name và Firmware version
Mobile.tap(findTestObject('Object Repository/ONT/Update Firmware/model_name'), 30)
ez.tapElementByText('GW020-H')
ez.tapElementByText('Chọn file')
ez.tapElementByText('tclinux.bin.signed.G020E2VN00T005_230607_185608')

Mobile.swipe(383, 1031, 383, 577)

//Chọn thiết bị cần update Firmware
MobileElement checkbox_ONT = ez.findContainsAndTapOffset('ONT', -42, -42, 0)

TestObject btn_updateFW = findTestObject('Object Repository/ONT/Update Firmware/btn_updateFW')
Mobile.verifyElementAttributeValue(btn_updateFW, 'clickable', 'true', 10)

Mobile.tap(btn_updateFW, 30)
Mobile.delay(15)
 
//Xác nhận update FW thành công
String txt_updateFW_success = 'Gửi yêu cầu thành công, thiết bị đang thực hiện update firmware, vui lòng chờ khoảng 5 phút trước khi thực hiện thao tác tiếp theo với thiết bị'
TestObject updateFW_success = ez.createTestObjectFromText(txt_updateFW_success)
Mobile.waitForElementPresent(updateFW_success, 60)
ez.tapElementByText('Tiếp tục')
 
//Check quay về màn Home
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)

