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
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import base.EzAction

EzAction ez = new EzAction()

ez.tapElementByText('Trace Route')
Mobile.tap(findTestObject('Object Repository/ONT/Network test/btn_network_test'), 30)

Mobile.delay(40)

//Trace lỗi
if(Mobile.verifyElementExist(ez.createTestObjectFromText('Lấy dữ liệu không thành công'), 5, FailureHandling.OPTIONAL)) {
	println('Lấy dữ liệu không thành công!')
}

//Kiểm tra Trace thành công
TestObject txt_trace_result = ez.createTestObjectFromText('Complete')
Mobile.verifyElementExist(txt_trace_result, 0)

List<WebElement> list_trace_result = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "IP Address")]'))

if(list_trace_result.size() != 0) {
	println('Trace success')
} else println('Failed')
