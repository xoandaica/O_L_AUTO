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
import org.openqa.selenium.By


EzAction ez = new EzAction()
// Kiểm tra danh sách Port, nếu chưa có thì tạo mới để xóa
// Check số lượng Port
TestObject portList = findTestObject('Object Repository/ONT/Network config/portList')
int portNumber = (Mobile.getAttribute(portList, 'contentDescription', 5)).substring(28, 29) as int
println(portNumber)
// Check chưa có Port thì call case Add
if(portNumber == 0) {
	WebUI.callTestCase(findTestCase('Test Cases/ONT/Port Forwarding/Port Forwarding config/OL_154 Add Port Forwarding'), [:], FailureHandling.STOP_ON_FAILURE)
}


// Click vào Port đầu tiên để xóa, click Xác nhận
List<MobileElement> port1 = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "Rule name:")]'))
port1.get(0).click()
ez.tapElementByText('Xóa Port')
ez.tapElementByText('Đồng ý')


// Verify msg
Mobile.verifyElementExist(ez.createTestObjectFromText('Xóa thành công'), 120)
ez.tapElementByText('Đóng')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách Port Forwarding'), 30)