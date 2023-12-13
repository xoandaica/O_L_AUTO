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

Mobile.startApplication(GlobalVariable.apk, false)
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_20 Verify connect Local success'), [('newSessionConnect') : 'false'], FailureHandling.STOP_ON_FAILURE)

//Commonv.checkLogin()

Mobile.callTestCase(findTestCase('Test Cases/Mesh/WAN config/OL_31 Move to WAN list'), [:], FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()
Mobile.delay(5)
TestObject wan0 = ez.createTestObject('//*[contains(@content-desc, "WAN Index: 0")]')
Mobile.waitForElementPresent(wan0, 5)

def newWanIndex = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]')).size()
if(newWanIndex == 4) {
	Mobile.callTestCase(findTestCase('Test Cases/Mesh/WAN config/Local/OL_36 Delete WAN'), [:], FailureHandling.STOP_ON_FAILURE) 
	Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_20 Verify connect Local success'), [:], FailureHandling.STOP_ON_FAILURE)
	Mobile.callTestCase(findTestCase('Test Cases/Mesh/WAN config/OL_31 Move to WAN list'), [:], FailureHandling.STOP_ON_FAILURE)
	newWanIndex = 3
}

ez.tapFriendByText('Danh sách WAN', 1)
ez.tapFriendByText('WAN Type', 1)
ez.tapElementByText('IPoE Static')
ez.tapFriendByText('IP Version', 1)
ez.tapElementByText('IPv6')
ez.setTextFriendByText('VLAN ID', '100' + newWanIndex, 1)
if(Mobile.verifyElementExist(ez.createTestObjectFromText('VLAN ID đã được sử dụng'), 5, FailureHandling.OPTIONAL)) {
	ez.setTextFriendByText('VLAN ID', '120' + newWanIndex, 1)
}
ez.setTextFriendByText('IPv6 Address', '2001:0db8:85a3:0000:0000:8a2e:0370:7334', 1)
ez.setTextFriendByText('IPv6 Gateway', '2001:0db8:85a3:0000:0000:8a2e:0370:7334', 1) 
ez.tapElementByText('Lưu')
 
 
Mobile.verifyElementExist(findTestObject('Object Repository/ONT/Network config/Message/msg_configSuccess_local'), 120)
ez.tapElementByText('Xác nhận')

//Check quay về màn Home
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)

Mobile.delay(300)
