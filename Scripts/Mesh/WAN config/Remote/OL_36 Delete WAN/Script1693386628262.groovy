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
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

Mobile.startApplication(GlobalVariable.apk, false)

Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_21 Verify connect Remote success'), [('newSessionConnect') : 'false'], FailureHandling.STOP_ON_FAILURE)
Mobile.callTestCase(findTestCase('Test Cases/Mesh/WAN config/OL_31 Move to WAN list'), [:], FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()
Mobile.delay(5)

List<WebElement> wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
if(wans.size() == 1) {
	WebUI.callTestCase(findTestCase('Test Cases/Mesh/WAN config/Remote/OL_32 Add WAN PPPoE'), [:], FailureHandling.STOP_ON_FAILURE) 
	wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
}

//Chọn Wan cuối và xoá
ez = new EzAction()
wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
//wans.get(1).click()
wans.last().click()
ez.tapElementByText('Xóa WAN')
ez.tapElementByText('Đồng ý') 


// Verify msg  
Mobile.verifyElementExist(findTestObject('Object Repository/ONT/Network config/Message/msg_deleteSuccess_remote'), 120)
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách WAN'), 30) 

Mobile.delay(300)

