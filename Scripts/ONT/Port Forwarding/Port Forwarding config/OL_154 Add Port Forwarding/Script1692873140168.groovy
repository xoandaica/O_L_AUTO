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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


EzAction ez = new EzAction()
ez.tapFriendByText('Danh sách Port Forwarding', 1)
// Set data & Save
String indexPort = ez.find('WAN Index', 1).getText()
ez.setTextFriendByText('Name', 'port' + indexPort, 1)
ez.setTextFriendByText('IP Address', '192.168.1.1', 1)
ez.tapFriendByText('Protocol', 1)
ez.tapElementByText('TCP')
ez.setTextFriendByText('Start Remote Port', '1000', 1)
ez.setTextFriendByText('End Remote Port', '2000', 1)
ez.setTextFriendByText('Start Local Port', '3000', 1)
int height95 = (Mobile.getDeviceHeight())*95/100
int height5 = (Mobile.getDeviceHeight())*5/100
int width50 = (Mobile.getDeviceWidth())*50/100
Mobile.swipe(width50, height95, width50, height5)
ez.setTextFriendByText('End Local Port', '4000', 1)
ez.tapElementByText('Lưu')


// Verify msg
Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 120)
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách Port Forwarding'), 30)