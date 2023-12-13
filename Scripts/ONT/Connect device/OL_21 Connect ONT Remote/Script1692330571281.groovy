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
import org.openqa.selenium.Keys as Keys
import one.telco.Commonv 


//if("$newSessionConnect" == 'true') {
	Mobile.startApplication(GlobalVariable.apk, false)
	Commonv.checkLogin()
//} 


TestObject serialInputObj = findTestObject('Object Repository/Mesh/Connect device/Connected remote/tbx_input_serialNum') 
TestObject searchBtn = findTestObject('Object Repository/Mesh/Connect device/Connected remote/btn_search_serialNum')


// Tìm kiếm thiết bị
Mobile.tap(serialInputObj, 0)
Mobile.setText(serialInputObj, GlobalVariable.ONTSerial, 0)
Mobile.tap(searchBtn, 0)


// Vào màn cấu hình thiết bị 
Mobile.waitForElementPresent(findTestObject('Object Repository/Home/Panel_device'), 30)
Mobile.tap(findTestObject('Object Repository/Home/Panel_device'), 0)


//Skip tutorial
TestObject skipBtn = findTestObject('Object Repository/ONT/Connect device/btn_Skip')
if(Mobile.verifyElementExist(skipBtn, 5, FailureHandling.OPTIONAL)) {
	Mobile.tap(skipBtn, 0)
}


// Kiểm tra đã vào được màn Dashboard 
EzAction ez = new EzAction()
String model = GlobalVariable.ONTModel
ez.find(model, 0)
//Mobile.verifyElementExist(findTestObject('Object Repository/ONT/Dashboard/wifi2.4G'), 30) 
return GlobalVariable.connectMode = 'remote'