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
import base.EzImage
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//Mobile.callTestCase(findTestCase('Test Cases/Mesh/Move to device dashboard'), null, FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()

ez.findContains('Cài đặt').click()
ez.tapElementByText('Cấu hình Mạng')
ez.tapElementByText('Thay đổi Channel Bandwidth')

Mobile.delay(3)
Mobile.verifyElementNotExist(ez.createTestObjectFromText('Lấy dữ liệu không thành công'), 5)
Mobile.verifyElementExist(ez.createTestObjectFromText('Cấu hình Channel, Bandwidth'), 0)

























