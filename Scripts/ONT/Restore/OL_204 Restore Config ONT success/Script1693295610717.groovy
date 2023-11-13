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

String fileBkup_name = GlobalVariable.fileBkup_name
println('Tên file backup: ' + fileBkup_name)

TestObject btn_select_file = findTestObject('Object Repository/Mesh/Device/Device control/Restore config/selectFile')
Mobile.tap(btn_select_file, 30)
Mobile.delay(5)

TestObject fileBkup = ez.createTestObject('//*[contains(@content-desc, "' + fileBkup_name + '")]')
TestObject btn_RestoreConfig = findTestObject('Object Repository/Mesh/Device/Device control/Restore config/btn_RestoreConfig')

Mobile.tap(fileBkup, 30)
Mobile.tap(btn_RestoreConfig, 30)
 
ez.tapElementByText('Đồng ý')
Mobile.delay(5)
 
TestObject txt_restore_success = ez.createTestObjectFromText('Gửi yêu cầu thành công, thiết bị đang thực hiện Restore Config, vui lòng chờ khoảng 5 phút trước khi thực hiện thao tác tiếp theo với thiết bị')
Mobile.verifyElementExist(txt_restore_success, 30)
ez.tapElementByText('Xác nhận')
 
//----------------------------------------Check sau khi restore tự động quay lại màn Home---------------------------------------------------
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)
