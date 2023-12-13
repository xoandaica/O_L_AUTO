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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import base.EzAction
import internal.GlobalVariable
import io.appium.java_client.MobileElement
import one.telco.Commonv

Mobile.startApplication(GlobalVariable.apk, false)

Mobile.callTestCase(findTestCase('Test Cases/Mesh/Device control/Reboot/Local/OL_74 Access Reboot screen success'), null, FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()

MobileElement checkbox_MRE = ez.findContains('MRE', 0)

if(checkbox_MRE == null) {
	println('Không có thiết bị MRE!')
} else {
	checkbox_MRE.click()
	
	ez.tapElementByText('Khởi động lại thiết bị')
	Mobile.delay(3)
	
	//Check hiển thị popup xác nhận
	Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Reboot/title_popup_reboot'), 30)
	Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Reboot/txt_popup_reboot'), 30)
	
	//Chọn Đồng ý và check hiển thị thông báo reboot thành công
	ez.tapElementByText('Đồng ý')
	Mobile.waitForElementPresent(findTestObject('Object Repository/Mesh/Device/Device control/Reboot/txt_reboot_success'), 30)
	
	ez.tapElementByText('Tiếp tục')
	
	//Check vẫn ở màn reboot
	Mobile.waitForElementPresent(ez.createTestObjectFromText('Khởi động lại thiết bị'), 30)
	Mobile.waitForElementPresent(ez.createTestObjectFromText('Reboot'), 30)
	Mobile.delay(300)
}

