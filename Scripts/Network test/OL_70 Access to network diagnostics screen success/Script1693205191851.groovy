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

//Di chuyển từ Dashboard tới màn chẩn đoán mạng
Mobile.tap(findTestObject('Object Repository/Mesh/Connect device/Connected remote/bottomNav_Setting'),0)
Mobile.tap(findTestObject('Object Repository/Mesh/Network test/btn_network_test'),0)

Mobile.tap(findTestObject('Object Repository/Mesh/Network test/btn_network_diagnostic'),0)

//Kiểm tra đã truy cập vào màn Chẩn đoán mạng
TestObject btn_ping = findTestObject('Object Repository/Mesh/Network test/btn_ping')
TestObject btn_trace_route = findTestObject('Object Repository/Mesh/Network test/btn_trace_route')
TestObject tbx_host = findTestObject('Object Repository/Mesh/Network test/tbx_host')
TestObject btn_network_diagnostic = findTestObject('Object Repository/Mesh/Network test/button_network diagnostic')

Mobile.verifyElementExist(btn_ping, 0)
Mobile.verifyElementExist(btn_trace_route, 0)
Mobile.verifyElementExist(tbx_host, 0)
Mobile.verifyElementExist(btn_network_diagnostic, 0)

//Mobile.closeApplication()