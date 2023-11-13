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


EzAction ez = new EzAction()


// Call case add Bridge mới để sửa
Mobile.callTestCase(findTestCase('Test Cases/ONT/WAN Config/WAN config - Remote/OL_126_3 Add WAN Bridge'), [:], FailureHandling.STOP_ON_FAILURE)


// Tìm WAN Bridge đầu tiên để sửa
int countPage = 0
Mobile.swipe(380, 220, 380, 2200)
List<MobileElement> list1 = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
for (int i=0; i< list1.size(); i++) {
	List<MobileElement> wBridge = list1.get(i).findElements(By.xpath("//*[@class = 'android.view.View' and (@text = 'Bridge' or . = 'Bridge') and @resource-id = '']"))
	if (wBridge.size() != 0) {
		countPage = 1
		list1.get(i).click()
		break
	}
} 
if (!countPage) {
	Mobile.swipe(380, 2200, 380, 220)
	List<MobileElement> list2 = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
	for (int i=0; i< list2.size(); i++) {
		List<MobileElement> wBridge = list2.get(i).findElements(By.xpath("//*[@class = 'android.view.View' and (@text = 'Bridge' or . = 'Bridge') and @resource-id = '']"))
		if (wBridge.size() != 0) {
			countPage = 2
			list2.get(i).click()
			break
		}
	} 
} 
println(countPage)


// Sửa vlanID  
MobileElement vlanID = ez.find('WAN Index', 4)
def oldVlan = vlanID.getText() as int
println(oldVlan)
def newVlan = (Math.floor(oldVlan / 10) % 2 == 0) ? oldVlan - 10 : oldVlan + 10
println(newVlan)
ez.setTextFriendByText('WAN Index', newVlan.toString(), 4) 
ez.tapElementByText('Lưu') 


// Verify msg 
Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 120)  
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách WAN'), 10)


// Kiểm tra lại giá trị sau chỉnh sửa
switch(countPage) {
	case 1:
	Mobile.swipe(380, 220, 380, 2200)
	List<MobileElement> list1Edit = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]')) 
	 for (int i=0; i< list1Edit.size(); i++) {
		 List<MobileElement> wBridge = list1Edit.get(i).findElements(By.xpath("//*[@class = 'android.view.View' and (@text = 'Bridge' or . = 'Bridge') and @resource-id = '']"))
		 if (wBridge.size() != 0) { 
			 list1Edit.get(i).click()
			 break
		 }
	 }
	 break
	 case 2:
	 Mobile.swipe(380, 2200, 380, 220)
	 List<MobileElement> list2Edit = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
	 for (int i=0; i< list2Edit.size(); i++) {
		 List<MobileElement> wBridge = list2Edit.get(i).findElements(By.xpath("//*[@class = 'android.view.View' and (@text = 'Bridge' or . = 'Bridge') and @resource-id = '']"))
		 if (wBridge.size() != 0) { 
			 list2Edit.get(i).click()
			 break
		 }
	 }
	 break
}  
vlanID = ez.find('WAN Index', 4)
def editVlan = vlanID.getText() as int 
assert editVlan == newVlan



