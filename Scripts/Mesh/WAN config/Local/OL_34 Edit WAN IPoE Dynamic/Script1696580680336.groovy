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
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_20 Verify connect Local success'), [('newSessionConnect') : 'false'], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Test Cases/Mesh/WAN config/OL_31 Move to WAN list'), [:], FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()
Mobile.delay(5)
List<WebElement> wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
if(wans.size() == 1) {
	Mobile.callTestCase(findTestCase('Test Cases/Mesh/WAN config/Local/OL_32 Add WAN PPPoE'), [:], FailureHandling.STOP_ON_FAILURE) 
	Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_20 Verify connect Local success'), [:], FailureHandling.STOP_ON_FAILURE)
	Mobile.callTestCase(findTestCase('Test Cases/Mesh/WAN config/OL_31 Move to WAN list'), [:], FailureHandling.STOP_ON_FAILURE) 
}

//Chọn Wan 1 để sửa 
ez = new EzAction()
//wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
//wans.get(1).click()
ez.findContainsAndTapOffset("WAN Index: 1", 0, 0)
Mobile.delay(3)
if(Mobile.verifyElementNotExist(ez.createTestObjectFromText('Chi tiết WAN'), 5, FailureHandling.OPTIONAL)) {
	ez.findContainsAndTapOffset("WAN Index: 1", 0, 0)
}
ez.tapFriendByText('WAN Type', 1)
ez.tapElementByText('IPoE Dynamic') 
ez.tapFriendByText('IP Version', 1)
ez.tapElementByText('IPv4')
MobileElement vlanObj = ez.find('VLAN ID', 1)
def oldVlan = vlanObj.text as int
def newVlan = (Math.floor(oldVlan / 10) % 2 == 0) ? oldVlan - 10 : oldVlan + 10
ez.setTextFriendByText('VLAN ID', newVlan.toString(), 1)
if(Mobile.verifyElementExist(ez.createTestObjectFromText('VLAN ID đã được sử dụng'), 5, FailureHandling.OPTIONAL)) {
	ez.setTextFriendByText('VLAN ID', '1' + newVlan.toString(), 1)
} 
ez.setTextFriendByText('Primary DNS:', '8.8.8.8', 1)
ez.setTextFriendByText('Secondary DNS:', '8.8.8.8', 1)
ez.tapElementByText('Lưu') 

// Verify msg
Mobile.verifyElementExist(findTestObject('Object Repository/ONT/Network config/Message/msg_configSuccess_local'), 120)
ez.tapElementByText('Xác nhận')

//Check quay về màn Home
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)

Mobile.delay(300)
