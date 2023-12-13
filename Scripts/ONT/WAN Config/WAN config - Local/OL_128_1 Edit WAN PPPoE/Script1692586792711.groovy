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
// Tìm WAN PPPoE thứ 2 để sửa (trừ WAN 1)
int countPage = 0
String wanIndex
int height90 = (Mobile.getDeviceHeight())*90/100
int height20 = (Mobile.getDeviceHeight())*20/100
int width50 = (Mobile.getDeviceWidth())*50/100
Mobile.swipe(width50, height20, width50, height90)
List<MobileElement> list1 = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
for (int i=2; i< list1.size(); i++) {
	List<MobileElement> wPPPoE = list1.get(i).findElements(By.xpath("//*[@class = 'android.view.View' and (@text = 'PPPoE' or . = 'PPPoE') and @resource-id = '']"))
	if (wPPPoE.size() != 0) {
		countPage = 1
		wanIndex = list1.get(i).getAttribute('contentDescription').substring(11,12)
		list1.get(i).click()
		break
	}
}
if (!countPage) {
	Mobile.swipe(width50, height90, width50, height20)
	List<MobileElement> list2 = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
	for (int i=0; i< list2.size(); i++) {
		List<MobileElement> wPPPoE = list2.get(i).findElements(By.xpath("//*[@class = 'android.view.View' and (@text = 'PPPoE' or . = 'PPPoE') and @resource-id = '']"))
		if (wPPPoE.size() != 0) {
			countPage = 2
			wanIndex = list2.get(i).getAttribute('contentDescription').substring(11,12)
			list2.get(i).click()
			break
		}
	}
}
println(countPage) 


// Sửa vlanID
MobileElement vlanID = ez.find('WAN Index', 5)
def oldVlan = vlanID.getText() as int
println(oldVlan)
def newVlan = (Math.floor(oldVlan / 10) % 2 == 0) ? oldVlan - 10 : oldVlan + 10
println(newVlan) 
ez.setTextFriendByText('WAN Index', newVlan.toString(), 5)    
ez.tapElementByText('Lưu') 


// Verify msg 
Mobile.verifyElementExist(findTestObject('Object Repository/ONT/Network config/Message/msg_configSuccess_local'), 120)
ez.tapElementByText('Xác nhận')
Mobile.verifyElementExist(findTestObject('Object Repository/Login/img_setting'), 30) 

Mobile.delay(60)



