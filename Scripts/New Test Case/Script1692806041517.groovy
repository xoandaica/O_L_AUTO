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

import base.Snapshot
import internal.GlobalVariable
import one.telco.Commonv

import org.openqa.selenium.Keys as Keys


Mobile.startApplication(GlobalVariable.apk, true)
//Commonv.checkNotLogin()

TestObject usernameObj = findTestObject('Object Repository/Login/EditText_username')
TestObject passwordObj = findTestObject('Object Repository/Login/EditText_password')
TestObject loginBtn = findTestObject('Object Repository/Login/Button_Login')

//Thao tác điền form login
Mobile.tap(usernameObj, 0)
Mobile.setText(usernameObj, "tienpx", 0)
Mobile.tap(passwordObj, 0)
Mobile.setText(passwordObj, "zxc!23", 0)
Mobile.tap(loginBtn, 0)

Snapshot.tapIfPresentAndContains('Bỏ qua',5)

Snapshot.tapIfPresentAndContains('Quét thiết bị')











