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

import base.EzAction
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.MobileElement
import org.openqa.selenium.Keys as Keys
 
Mobile.startApplication(GlobalVariable.apk, false)
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_20 Verify connect Local success'), null, FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()

ez.findContains('Cài đặt', 0).click()
ez.tapElementByText('Cấu hình Mạng')
ez.tapElementByText('Cấu hình LAN')

TestObject ipAddressObj = findTestObject('Object Repository/Mesh/Device/Device setting/Cau hinh Mang/Cau hinh LAN/EditText_IP Address')
def ip = Mobile.getText(ipAddressObj, 0)
if(ip == '192.168.88.1') {
	ip = '192.168.88.2'
}else {
	ip = '192.168.88.1'
}

Mobile.tap(ipAddressObj, 0)
Mobile.setText(ipAddressObj, ip, 10)

ez.tapElementByText('Lưu')
TestObject saveSuccess = ez.createTestObjectFromText('Lưu thành công!\nVui lòng truy cập WiFi và kết nối lại thiết bị')
Mobile.verifyElementExist(saveSuccess, 120)
ez.tapElementByText('Xác nhận')

//Check quay về màn Home
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)

Mobile.delay(300)