import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
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
import internal.GlobalVariable
import io.appium.java_client.MobileElement
import one.telco.Commonv

import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys

//Mobile.startApplication(GlobalVariable.apk, false)
//Commonv.checkLogin()
EzAction ez = new EzAction()

//TestObject serialInputObj = findTestObject('Object Repository/Home/EditText_search_serial')
//TestObject searchBtn = findTestObject('Object Repository/Home/ImageView_search_btn')
//
////Tìm kiếm thiết bị
//Mobile.tap(serialInputObj, 0)
//Mobile.setText(serialInputObj, GlobalVariable.meshSerial, 0)
//Mobile.tap(searchBtn, 0)
//
////Vào màn cấu hình thiết bị
//Mobile.waitForElementPresent(findTestObject('Object Repository/Home/Label_Ket qua tim kiem'), 0)
//Mobile.tap(findTestObject('Object Repository/Home/Panel_device'), 0)

//Vào màn cấu hình WAN
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Button_Cai Dat'), 0)
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device setting/Button_Cau hinh Mang'), 0)
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device setting/Cau hinh Mang/Button_Cau hinh WAN'), 0)

TestObject wan0 = ez.createTestObject('//*[contains(@content-desc, "WAN Index: 0")]')
Mobile.waitForElementPresent(wan0, 5)

def newWanIndex = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]')).size()
if(newWanIndex == 4) {
	WebUI.callTestCase(findTestCase('WAN config/Delete WAN'), [:], FailureHandling.STOP_ON_FAILURE)
	newWanIndex = 3
}

ez.tapFriendByText('Danh sách WAN', 1)
ez.setTextFriendByText('VLAN ID', '7' + newWanIndex, 1)
ez.setTextFriendByText('Username', 'auto_test', 1)
ez.setTextFriendByText('Password:', 'auto_test', 1)
ez.tapElementByText('Lưu')

Mobile.delay(20)
Mobile.waitForElementPresent(ez.createTestObjectFromText('Xác nhận'), 120)

String txt_saveSuccess = 'Lưu thành công!\n Vui lòng truy cập WiFi và kết nối lại thiết bị'
Mobile.verifyElementExist(ez.createTestObjectFromText(txt_saveSuccess), 30)
ez.tapElementByText('Xác nhận')

//Check quay về màn Home
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')

Mobile.verifyElementExist(hello, 30)
