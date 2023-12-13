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


Mobile.callTestCase(findTestCase('Test Cases/ONT/Connect device/OL_20 Connect ONT Local'), ['newSessionConnect': true], FailureHandling.STOP_ON_FAILURE)
Mobile.callTestCase(findTestCase('Test Cases/ONT/WAN Config/WAN config - Local/OL_125 View WAN list'), null, FailureHandling.STOP_ON_FAILURE)


EzAction ez = new EzAction() 
// Check số lượng WAN
TestObject wanList = findTestObject('Object Repository/ONT/Network config/WAN config/wanList')  
int wanNumber = (Mobile.getAttribute(wanList, 'contentDescription', 5)).substring(16, 17) as int 
println(wanNumber)


// Check chỉ có 1 WAN thì call case Add WAN 
if(wanNumber == 1) {
	Mobile.callTestCase(findTestCase('Test Cases/ONT/WAN Config/WAN config - Local/OL_126_1 Add WAN PPPoE'), null, FailureHandling.STOP_ON_FAILURE)
	// Tạo mới thành công > back về màn Tìm kiếm Serial => call case về lại màn danh sách WAN
	Mobile.callTestCase(findTestCase('Test Cases/ONT/Connect device/OL_20 Connect ONT Local'), ['newSessionConnect': true], FailureHandling.STOP_ON_FAILURE)
	Mobile.callTestCase(findTestCase('Test Cases/ONT/WAN Config/WAN config - Local/OL_125 View WAN list'), null, FailureHandling.STOP_ON_FAILURE) 
}


//Chọn Wan cuối và xoá
List<MobileElement> wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
wans.last().click()
if(Mobile.verifyElementNotExist(ez.createTestObjectFromText('Chi tiết WAN'), 5, FailureHandling.OPTIONAL)) {
	wans.last().click()
}
ez.tapElementByText('Xóa WAN')
ez.tapElementByText('Đồng ý') 


// Verify msg 
Mobile.verifyElementExist(findTestObject('Object Repository/ONT/Network config/Message/msg_deleteSuccess_local'), 120)
ez.tapElementByText('Xác nhận')
Mobile.verifyElementExist(findTestObject('Object Repository/Login/img_setting'), 30) 

Mobile.delay(60)








