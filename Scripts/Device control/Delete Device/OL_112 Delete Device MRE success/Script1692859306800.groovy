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

Mobile.startApplication(GlobalVariable.apk, false)

Mobile.callTestCase(findTestCase('Test Cases/Device control/Delete Device/OL_107 Access Delete Device screen success'), [('newSessionDelete') : 'false'], FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()

MobileElement MRE = ez.findContains('MRE', 0)

if(MRE == null) {
	println('Không có thiết bị MRE!')
} else {
	Mobile.tap(MRE, 30)
	Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Delete Device/btn_delete_device'), 30)

	TestObject confirm_delete = ez.createTestObjectFromText('Bạn có chắc chắn muốn xóa thiết bị?')
	Mobile.verifyElementExist(confirm_delete, 30)

	ez.tapElementByText('Đồng ý')

	Mobile.delay(5)
	Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Delete Device/txt_delete_device_success'), 5)
	
	Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device control/Delete Device/btn_confirm_delete'), 5)
	Mobile.delay(10)
	
	//Check sau khi xóa tự động quay lại màn home
	String txt_hello = 'Xin chào\n' + GlobalVariable.username
	TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
	Mobile.verifyElementExist(hello, 30)
}
