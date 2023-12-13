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
 
//Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_20 Verify connect Local success'), [('newSessionConnect') : 'false'], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Connect device/Connected remote/title_wifi_infor'), 0)
Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Dashboard/btn_editWiFi'), 0)

//Mở popup chỉnh sửa wifi
Mobile.tap(findTestObject('Object Repository/Mesh/Dashboard/btn_editWiFi'), 0)
Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Dashboard/titlePopup_update_Wifi_infor'), 0)
Mobile.tap(findTestObject('Object Repository/Mesh/Dashboard/popup_hide_password'), 0)

TestObject wifiNameObj = findTestObject('Object Repository/Mesh/Dashboard/wifiName')
TestObject wifiPassObj = findTestObject('Object Repository/Mesh/Dashboard/wifiPws')

Mobile.tap(wifiNameObj, 0)
String wifi_name = Mobile.getText(wifiNameObj, 0)
GlobalVariable.wifiName_Mesh = wifi_name

Mobile.tap(wifiPassObj, 0)
String wifi_pass = Mobile.getText(wifiPassObj, 0)
GlobalVariable.wifiPass_Mesh = wifi_pass

println('Wi-Fi name: ' + wifi_name + ' -- Wi-Fi password: ' + wifi_pass)

Mobile.hideKeyboard()
Mobile.tapAtPosition(334, 194)
