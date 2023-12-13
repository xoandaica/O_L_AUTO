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
import org.apache.commons.lang.RandomStringUtils as RandomStringUtils
import base.EzAction
import io.appium.java_client.MobileElement

Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_20 Verify connect Local success'), [('newSessionConnect') : 'false'], FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()
String wifiName_init = GlobalVariable.wifiName_Mesh
String wifiPass_init = GlobalVariable.wifiName_Mesh

String wifiName_after = 'name' + RandomStringUtils.randomNumeric(5)
String wifiPass_after = 'pass' + RandomStringUtils.randomNumeric(5)

TestObject btn_Save_edit = findTestObject('Object Repository/Mesh/Dashboard/btn_save_edit')

//Thao tác điền form update thông tin wifi
Mobile.tap(findTestObject('Object Repository/Mesh/Dashboard/btn_editWiFi'), 0)
TestObject wifiName = findTestObject('Object Repository/Mesh/Dashboard/wifiName')
TestObject wifiPass = findTestObject('Object Repository/Mesh/Dashboard/wifiPws')

Mobile.tap(wifiName, 0)
Mobile.setText(wifiName, wifiName_after, 0)

Mobile.tap(wifiPass, 0)
Mobile.setText(wifiPass, wifiPass_after, 0)

Mobile.tap(btn_Save_edit, 0)

Mobile.waitForElementPresent(findTestObject('Object Repository/Mesh/Dashboard/btn_confirm'), 20)

Mobile.tap(findTestObject('Object Repository/Mesh/Dashboard/btn_confirm'), 0)

GlobalVariable.wifiName_Mesh = wifiName_after
GlobalVariable.wifiPass_Mesh = wifiPass_after

//Kiểm tra back về màn tìm kiếm serial
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)

Mobile.delay(60)
