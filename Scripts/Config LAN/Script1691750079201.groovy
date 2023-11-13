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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Mobile.startApplication(GlobalVariable.apk, false)

TestObject serialInputObj = findTestObject('Object Repository/Home/EditText_search_serial')
TestObject searchBtn = findTestObject('Object Repository/Home/ImageView_search_btn')

Mobile.tap(serialInputObj, 0)
Mobile.setText(serialInputObj, GlobalVariable.meshSerial, 0)
Mobile.tap(searchBtn, 0)

Mobile.waitForElementPresent(findTestObject('Object Repository/Home/Label_Ket qua tim kiem'), 0)
Mobile.tap(findTestObject('Object Repository/Home/Panel_device'), 0)

Mobile.tap(findTestObject('Object Repository/Mesh/Device/Button_Cai Dat'), 0)
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device setting/Button_Cau hinh Mang'), 0)
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device setting/Cau hinh Mang/Button_Cau hinh LAN'), 0)

TestObject ipAddressObj = findTestObject('Object Repository/Mesh/Device/Device setting/Cau hinh Mang/Cau hinh LAN/EditText_IP Address')
def ip = Mobile.getText(ipAddressObj, 0)
if(ip == '192.168.88.1') {
	ip = '192.168.88.2'
}else {
	ip = '192.168.88.1'
}

Mobile.tap(ipAddressObj, 0)
Mobile.setText(ipAddressObj, ip, 10)

EzAction ez = new EzAction()
ez.tapElementByText('Lưu')
Mobile.verifyElementExist(findTestObject('Object Repository/text_Luu thanh cong'), 120)












