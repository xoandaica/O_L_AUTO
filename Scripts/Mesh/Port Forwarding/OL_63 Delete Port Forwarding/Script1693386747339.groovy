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
import internal.GlobalVariable
import io.appium.java_client.MobileElement

import org.openqa.selenium.Keys as Keys


//Mobile.callTestCase(findTestCase('Test Cases/Mesh/Port Forwarding/OL_59 Move to Port Forwarding'), null, FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()

List<MobileElement> ports = ez.findManyContains('Rule name:')
if(ports.size() == 0) {
	Mobile.callTestCase(findTestCase('Test Cases/Mesh/Port Forwarding/OL_61 Create Port Forwarding'), null, FailureHandling.STOP_ON_FAILURE)
	ports = ez.findManyContains('Rule name:')
}

MobileElement port = ports.get(0)
port.click()

ez.tapElementByText('Xóa Port')
ez.tapElementByText('Đồng ý')

// Verify msg
Mobile.verifyElementExist(ez.createTestObjectFromText('Xóa thành công'), 120)
ez.tapElementByText('Đóng')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách Port Forwarding'), 10)

List<MobileElement> ports_new = ez.findManyContains('Rule name:')
assert ports.size() - ports_new.size() == 1

















