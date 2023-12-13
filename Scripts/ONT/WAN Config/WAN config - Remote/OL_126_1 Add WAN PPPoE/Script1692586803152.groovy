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
import io.appium.java_client.android.AndroidElement
import one.telco.Commonv

import org.openqa.selenium.Keys as Keys
import base.EzAction 
import org.openqa.selenium.By


Mobile.callTestCase(findTestCase('Test Cases/ONT/WAN Config/WAN config - Remote/OL_125 View WAN list'), [:], FailureHandling.STOP_ON_FAILURE) 

// Check số lượng WAN
TestObject wanList = findTestObject('Object Repository/ONT/Network config/WAN config/wanList')  
int wanNumber = (Mobile.getAttribute(wanList, 'contentDescription', 5)).substring(16, 17) as int 
println(wanNumber)


// Nếu 8 WAN thì call case Delete WAN  
if(wanNumber == 8) {
	Mobile.callTestCase(findTestCase('Test Cases/ONT/WAN Config/WAN config - Remote/OL_131 Delete WAN other than 0'), [:], FailureHandling.STOP_ON_FAILURE)
} 


// Set data & Save
EzAction ez = new EzAction()
ez.tapFriendByText('Danh sách WAN', 1)
Mobile.delay(5)  
ez.setTextFriendByText('WAN Index', '10' + wanNumber, 5) 
ez.setTextFriendByText('WAN Index', '1', 7)  
MobileElement info = ez.findContains('Username')
List<MobileElement> infoBlock = info.findElements(By.xpath("//*[@class = 'android.widget.EditText' and @resource-id = '' and (@text = '' or . = '')]"))
for (int i=0; i<infoBlock.size(); i++) { 
	infoBlock.get(i).click()
	infoBlock.get(i).clear()
	infoBlock.get(i).sendKeys('auto_test') 
	Mobile.hideKeyboard()
} 
ez.tapElementByText('Lưu')


// Verify msg 
Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 120)  
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách WAN'), 30)


// Load lại data
Mobile.delay(60) 