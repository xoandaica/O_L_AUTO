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
import base.EzAction
import org.openqa.selenium.Keys as Keys

Mobile.startApplication(GlobalVariable.apk, false)
EzAction ez = new EzAction()

Mobile.verifyElementExist(findTestObject('Force Update/No tick/title_text_update_version_no_choose'), 30, FailureHandling.STOP_ON_FAILURE)

Mobile.waitForElementPresent(ez.createTestObjectFromText('Ứng dụng đã có phiên bản'), 5)

Mobile.waitForElementPresent(ez.createTestObjectFromText(' trên Google Play. Quý khách hãy truy cập vào kho ứng dụng và tải phiên bản mới nhất để có trải nghiệm tốt hơn cùng ONE Link'), 5)

Mobile.verifyElementExist(findTestObject('Force Update/No tick/btn_last_update_nochoose'), 30, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Force Update/No tick/btn_update_nochoose'), 30, FailureHandling.STOP_ON_FAILURE)