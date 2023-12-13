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
import io.appium.java_client.MobileElement
import base.EzAction

if("$newSessionDetail" == 'true') {
	Mobile.startApplication(GlobalVariable.apk, false)
}

Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_20 Verify connect Local success'), [('newSessionConnect') : 'false'], FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()

MobileElement MRE = ez.findContains('MRE', 0)

if(MRE == null) {
	println('Không có thiết bị MRE!')
} else {
	MRE.click()
	
	//Kiểm tra đã vào màn Chi tiết thiết bị MRE
	Mobile.verifyElementExist(ez.createTestObjectFromText('Chi tiết thiết bị'), 30)
	Mobile.waitForElementPresent(ez.createTestObjectFromText('MRE'), 30)
	Mobile.waitForElementPresent(ez.createTestObjectFromText('Reboot'), 30)
	Mobile.waitForElementPresent(ez.createTestObjectFromText('Reset'), 30)
}
