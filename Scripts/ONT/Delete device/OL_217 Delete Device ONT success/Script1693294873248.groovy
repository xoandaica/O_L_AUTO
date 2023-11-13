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
import io.appium.java_client.MobileElement
import one.telco.Commonv

EzAction ez = new EzAction()

TestObject ONT = ez.createTestObject('//*[contains(@content-desc, "ONT")]')
 
Mobile.tap(ONT, 30)
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Delete Device/btn_delete_device'), 30)

TestObject confirm_delete = ez.createTestObjectFromText('Bạn có chắc chắn muốn xóa thiết bị?')
Mobile.verifyElementExist(confirm_delete, 30)

ez.tapElementByText('Đồng ý')

Mobile.delay(5)
String txt_delete_device_success = 'Xóa thành công'
Mobile.verifyElementExist(ez.createTestObjectFromText(txt_delete_device_success), 5)
	
ez.tapElementByText('Xác nhận')
Mobile.delay(10)

//Check sau khi xóa tự động quay lại màn home
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)
Mobile.delay(5)

//Kiểm tra không tìm thấy device vừa xóa
TestObject serialInputObj = findTestObject('Object Repository/Home/EditText_search_serial')
TestObject searchBtn = findTestObject('Object Repository/Home/ImageView_search_btn')

Mobile.tap(serialInputObj, 30)
Mobile.setText(serialInputObj, GlobalVariable.ONTSerial, 30)
Mobile.tap(searchBtn, 30)
Mobile.delay(5)

TestObject device_notFound = ez.createTestObject('//*[contains(@content-desc, "Không tìm thấy thiết bị")]')
Mobile.verifyElementExist(device_notFound, 30)
