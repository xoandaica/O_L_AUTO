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

import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys


EzAction ez = new EzAction()
Mobile.delay(5)
ez.findContains('Cài đặt', 0).click()
ez.tapElementByText('Cấu hình Mạng')
ez.tapElementByText('Cấu hình Port Forwarding')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách WAN'), 30)


// Kiểm tra có button Cấu hình Port Forwarding không
int countPort = 0
String wanName 
List<MobileElement> wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
for (int i=0; i< wans.size(); i++) {
	String buttonPort = wans.get(i).getAttribute('contentDescription')
	if (buttonPort.contains("Cấu hình Port Forwarding")) {
		countPort++
		wanName = wans.get(i).getAttribute('contentDescription').substring(0,12)
		break
	}
} 
if (countPort == 0) {
	Mobile.swipe(380, 2200, 380, 220)
	wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
	for (int i=0; i< wans.size(); i++) {
		String buttonPort = wans.get(i).getAttribute('contentDescription')
		if (buttonPort.contains("Cấu hình Port Forwarding")) {
			countPort++
			wanName = wans.get(i).getAttribute('contentDescription').substring(0,12)
			break
		}
	}
}
if (countPort == 0) {
	Mobile.swipe(380, 2200, 380, 220)
	wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
	for (int i=0; i< wans.size(); i++) {
		String buttonPort = wans.get(i).getAttribute('contentDescription')
		if (buttonPort.contains("Cấu hình Port Forwarding")) {
			countPort++
			wanName = wans.get(i).getAttribute('contentDescription').substring(0,12)
			break
		}
	}
}


// Nếu không có button Cấu hình Port Forwarding nào thì tạo WAN mới
if (countPort == 0) {
	// Back lại màn hình Cấu hình WAN để tạo mới WAN 
	Mobile.callTestCase(findTestCase('Test Cases/ONT/Connect device/OL_21 Connect ONT Remote'), [newSessionConnect: false], FailureHandling.STOP_ON_FAILURE)
	Mobile.callTestCase(findTestCase('Test Cases/ONT/WAN Config/WAN config - Remote/OL_125 View WAN list'), null, FailureHandling.STOP_ON_FAILURE)
	Mobile.callTestCase(findTestCase('Test Cases/ONT/WAN Config/WAN config - Remote/OL_126_2 Add WAN IPoE'), null, FailureHandling.STOP_ON_FAILURE)
	// Tạo WAN thành công hiện đang ở màn Danh sách WAN của Cấu hình WAN -> back to màn Danh sách WAN của Cấu hình Port Forwarding
	ez.tapFriendByText('Danh sách WAN', -1)
	ez.tapElementByText('Cấu hình Port Forwarding')

	wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
	for (int i=0; i< wans.size(); i++) {
		String buttonPort = wans.get(i).getAttribute('contentDescription')
		if (buttonPort.contains("Cấu hình Port Forwarding")) {
			countPort++
			wanName = wans.get(i).getAttribute('contentDescription').substring(0,12)
			break
		}
	}
	if (countPort == 0) {
		Mobile.swipe(380, 2200, 380, 220)
		wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
		for (int i=0; i< wans.size(); i++) {
			String buttonPort = wans.get(i).getAttribute('contentDescription')
			if (buttonPort.contains("Cấu hình Port Forwarding")) {
				countPort++
				wanName = wans.get(i).getAttribute('contentDescription').substring(0,12)
				break
			}
		}
	}
	if (countPort == 0) {
		Mobile.swipe(380, 2200, 380, 220)
		wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
		for (int i=0; i< wans.size(); i++) {
			String buttonPort = wans.get(i).getAttribute('contentDescription')
			if (buttonPort.contains("Cấu hình Port Forwarding")) {
				countPort++
				wanName = wans.get(i).getAttribute('contentDescription').substring(0,12)
				break
			}
		}
	}
}


// Tap vào button Cấu hình Port Forwarding
ez.findContainsAndTapOffset(wanName, 40, 40, 0)
Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách Port Forwarding'), 30)


