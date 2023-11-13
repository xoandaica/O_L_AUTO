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


Mobile.callTestCase(findTestCase('Test Cases/Mesh/DDNS/OL_48 Move to DDNS'), null, FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()

List<MobileElement> ddnss = ez.findManyContains('Index:')
if(ddnss.size() == 4) {
	Mobile.callTestCase(findTestCase('Test Cases/Mesh/DDNS/OL_51 Delete DDNS'), null, FailureHandling.STOP_ON_FAILURE)
	ddnss = ez.findManyContains('Index:')
}

ez.tapFriendByText('Danh sách DDNS', 1)
Mobile.waitForElementPresent(ez.createTestObjectFromText('Tạo mới DDNS'), 5)

//Điền thông DDNS
String salt = Randomv.getHexStringFromTimestamp().substring(7)
ez.tapFriendByText('Service Provider', 1)
ez.tapElementByText('www.no-ip.com')
ez.setTextFriendByText('Host name', 'uetube' + salt + '.com.vn', 1)
ez.setTextFriendByText('Username', 'Auto_test_' + salt, 1)
ez.setTextFriendByText('Password', '12345678', 1)
ez.tapElementByText('Lưu')

// Verify msg
Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 120)
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách DDNS'), 10)

List<MobileElement> ddnss_new = ez.findManyContains('Index:')
assert ddnss_new.size() - ddnss.size() == 1
















