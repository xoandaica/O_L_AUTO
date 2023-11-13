import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.awt.Color

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
import base.EzImage
import internal.GlobalVariable
import io.appium.java_client.MobileElement

import org.openqa.selenium.Keys as Keys


Mobile.callTestCase(findTestCase('Test Cases/ONT/Connect device/OL_21 Connect ONT Remote'), null, FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()

ez.findContains('Cài đặt', 0).click()
ez.tapElementByText('Cấu hình Mạng')
ez.tapElementByText('Cấu hình Mesh')

Mobile.delay(5)
MobileElement toggle = ez.find('Enable', 1) 

//Kiểm tra nút đang Enable
assert EzImage.checkElementByColor(toggle, Color.GREEN, Color.GRAY)





























