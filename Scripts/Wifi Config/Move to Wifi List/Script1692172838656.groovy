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
import org.openqa.selenium.Keys as Keys

Mobile.startApplication(GlobalVariable.apk, false)
//Commonv.checkLogin()
EzAction ez = new EzAction()

TestObject serialInputObj = findTestObject('Object Repository/Home/EditText_search_serial')
TestObject searchBtn = findTestObject('Object Repository/Home/ImageView_search_btn')

//Tìm kiếm thiết bị
Mobile.tap(serialInputObj, 0)
Mobile.setText(serialInputObj, GlobalVariable.meshSerial, 0)
Mobile.tap(searchBtn, 0)

//Vào màn cấu hình thiết bị
Mobile.waitForElementPresent(findTestObject('Object Repository/Home/Label_Ket qua tim kiem'), 0)
Mobile.tap(findTestObject('Object Repository/Home/Panel_device'), 0)

ez.findContains('Cài đặt', 0).click()
ez.tapElementByText('Cấu hình Mạng')
ez.tapElementByText('Cài đặt Wifi')

Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách Wifi'), 0)





















