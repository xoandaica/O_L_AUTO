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
import one.telco.Randomv

import org.openqa.selenium.Keys as Keys


//Mobile.callTestCase(findTestCase('Test Cases/Mesh/Port Forwarding/OL_59 Move to Port Forwarding'), null, FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()

List<MobileElement> ports = ez.findManyContains('Rule name:')
if(ports.size() == 8) {
	Mobile.callTestCase(findTestCase('Test Cases/Mesh/Port Forwarding/OL_63 Delete Port Forwarding'), null, FailureHandling.STOP_ON_FAILURE)
	ddnss = ez.findManyContains('Rule name:')
}

ez.tapFriendByText('Danh sách Port Forwarding', 1)
Mobile.waitForElementPresent(ez.createTestObjectFromText('Tạo Port Forwarding'), 5)

//Điền thông Port Forwarding
String salt = Randomv.getHexStringFromTimestamp().substring(6)
ez.tapFriendByText('WAN Index', 1)
ez.tapElementByText('0')

ez.setTextFriendByText('Name', 'port_' + salt, 1)
ez.setTextFriendByText('IP Address', '122.117.52.' + new Random().nextInt(255), 1)
ez.tapFriendByText('Protocol', 1)
ez.tapElementByText('ALL')

ez.setTextFriendByText('Start Remote Port', '1', 1)
ez.setTextFriendByText('Start Local Port', '1', 1)
ez.tapElementByText('Lưu')

// Verify msg
Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 120)
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách Port Forwarding'), 10)

List<MobileElement> ports_new = ez.findManyContains('Rule name:')
assert ports_new.size() - ports.size() == 1
















