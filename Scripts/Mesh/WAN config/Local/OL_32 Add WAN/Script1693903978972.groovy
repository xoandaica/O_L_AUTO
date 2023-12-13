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


Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/Connect wifi'), [:], FailureHandling.STOP_ON_FAILURE)
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_20 Verify connect Local success'), [:], FailureHandling.STOP_ON_FAILURE)
Mobile.callTestCase(findTestCase('Test Cases/Mesh/WAN config/OL_31 Move to WAN list'), [:], FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()
Mobile.delay(10)
TestObject wan0 = ez.createTestObject('//*[contains(@content-desc, "WAN Index: 0")]')
Mobile.waitForElementPresent(wan0, 5)

def newWanIndex = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]')).size()
if(newWanIndex == 4) {
	Mobile.callTestCase(findTestCase('Test Cases/Mesh/WAN config/Local/OL_36 Delete WAN'), [:], FailureHandling.STOP_ON_FAILURE)
	Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/Connect wifi'), [:], FailureHandling.STOP_ON_FAILURE)
	Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_20 Verify connect Local success'), [:], FailureHandling.STOP_ON_FAILURE)
	Mobile.callTestCase(findTestCase('Test Cases/Mesh/WAN config/OL_31 Move to WAN list'), [:], FailureHandling.STOP_ON_FAILURE)
	newWanIndex = 3
}

ez = new EzAction()
ez.tapFriendByText('Danh sách WAN', 1)
ez.setTextFriendByText('VLAN ID', '7' + newWanIndex, 1)
ez.setTextFriendByText('Username', 'auto_test', 1)
ez.setTextFriendByText('Password:', 'auto_test', 1)
ez.tapElementByText('Lưu')
 
 
Mobile.verifyElementExist(findTestObject('Object Repository/ONT/Network config/Message/msg_configSuccess_local'), 120)
ez.tapElementByText('Xác nhận')
Mobile.verifyElementExist(findTestObject('Object Repository/ONT/Connect device/input_serialNumber'), 30)







