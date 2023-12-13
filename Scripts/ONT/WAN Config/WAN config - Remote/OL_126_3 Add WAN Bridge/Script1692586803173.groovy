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
import internal.GlobalVariable
import io.appium.java_client.MobileElement

import org.openqa.selenium.Keys as Keys
import base.EzAction 
import org.openqa.selenium.By

Mobile.callTestCase(findTestCase('Test Cases/ONT/WAN Config/WAN config - Remote/OL_125 View WAN list'), [:], FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()
// Check số lượng WAN
TestObject wanList = findTestObject('Object Repository/ONT/Network config/WAN config/wanList')  
int wanNumber = (Mobile.getAttribute(wanList, 'contentDescription', 5)).substring(16, 17) as int 
println(wanNumber)

// Nếu 8 WAN thì call case Delete WAN  
if(wanNumber == 8) {
	Mobile.callTestCase(findTestCase('Test Cases/ONT/WAN Config/WAN config - Remote/OL_131 Delete WAN other than 0'), [:], FailureHandling.STOP_ON_FAILURE)
} 


// Set data & Save
ez.tapFriendByText('Danh sách WAN', 1)
Mobile.delay(5) 
ez.tapFriendByText('WAN Index', 2)
ez.tapElementByText('Bridge') 
ez.setTextFriendByText('WAN Index', '30' + wanNumber, 4)
ez.setTextFriendByText('WAN Index', '3', 6) 
ez.tapElementByText('Lưu')


// Verify msg 
Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 120)  
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách WAN'), 30)


// Load lại data
Mobile.delay(60) 