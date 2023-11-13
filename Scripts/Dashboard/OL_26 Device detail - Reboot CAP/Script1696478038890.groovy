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
import base.EzAction
import one.telco.Commonv

//Mobile.startApplication(GlobalVariable.apk, false)
//Commonv.checkLogin()
Mobile.callTestCase(findTestCase('Test Cases/Dashboard/OL_25 Device detail - CAP'), null, FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()
ez.tapElementByText('Reboot')

//Check hiển thị popup xác nhận
String txt_confirm = 'Sau khi khởi động lại thiết bị vui lòng truy cập lại vào mạng của thiết bị để thực hiện cài đặt!'
TestObject popup_confirm_txt = ez.createTestObjectFromText(txt_confirm)
Mobile.waitForElementPresent(popup_confirm_txt, 10)

//Chọn Hủy
ez.tapElementByText('Hủy')
Mobile.verifyElementExist(ez.createTestObjectFromText('Chi tiết thiết bị'), 30)

//Chọn Đồng ý và check hiển thị thông báo reboot thành công
ez.tapElementByText('Reboot')
ez.tapElementByText('Đồng ý')

String txt_reboot_success = 'Gửi yêu cầu thành công, thiết bị đang thực hiện reboot, vui lòng chờ khoảng 5 phút trước khi thực hiện thao tác tiếp theo với thiết bị'
Mobile.waitForElementPresent(ez.createTestObjectFromText('txt_reboot_success'), 30)

ez.tapElementByText('Tiếp tục')

//Check quay về màn Home
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)

Mobile.delay(60)