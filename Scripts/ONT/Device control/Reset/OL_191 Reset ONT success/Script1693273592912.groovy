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

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import base.EzAction
import io.appium.java_client.MobileElement
import one.telco.Commonv


if (GlobalVariable.connectMode == 'local') {
	Mobile.callTestCase(findTestCase('Test Cases/ONT/Connect device/OL_20 Connect ONT Local'), ['newSessionConnect': true], FailureHandling.STOP_ON_FAILURE)
}
else { 
	Mobile.callTestCase(findTestCase('Test Cases/ONT/Connect device/OL_21 Connect ONT Remote'), ['newSessionConnect': true], FailureHandling.STOP_ON_FAILURE)
}
Mobile.callTestCase(findTestCase('Test Cases/ONT/Device control/Reset/OL_186 Access to Reset screen'), [:], FailureHandling.STOP_ON_FAILURE)


EzAction ez = new EzAction()
MobileElement ONT_reset = ez.findContains('ONT', 0)
ONT_reset.click()
	
ez.tapElementByText('Khôi phục cài đặt gốc của thiết bị')
Mobile.delay(3)
	
//Check hiển thị popup xác nhận
String txt_confirm_reset = 'Sau khi đưa thiết bị về trạng thái cài đặt gốc ban đầu của nhà sản xuất, vui lòng truy cập vào mạng của thiết bị để thực hiện lại cài đặt!'
TestObject popup_confirm_txt = ez.createTestObjectFromText(txt_confirm_reset)
Mobile.waitForElementPresent(popup_confirm_txt, 10)

//Chọn Đồng ý và check hiển thị thông báo reset thành công
ez.tapElementByText('Đồng ý')
String txt_reset_success = 'Gửi yêu cầu thành công, thiết bị đang thực hiện reset, vui lòng chờ khoảng 5 phút trước khi thực hiện thao tác tiếp theo với thiết bị'
Mobile.waitForElementPresent(ez.createTestObjectFromText(txt_reset_success), 5)
	
ez.tapElementByText('Xác nhận')
	
//Check quay về màn Home
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)

Mobile.delay(300)
