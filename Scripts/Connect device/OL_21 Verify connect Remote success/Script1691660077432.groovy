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
import one.telco.Commonv


if("$newSessionConnect" == 'true') {
	Mobile.startApplication(GlobalVariable.apk, false)
}

Commonv.checkLogin()

//Tìm kiếm serial number
Mobile.tap(findTestObject('Object Repository/Mesh/Connect device/Connected remote/tbx_input_serialNum'), 0)

String serialNum = "$serialNum"

Mobile.setText(findTestObject('Object Repository/Mesh/Connect device/Connected remote/tbx_input_serialNum'), serialNum, 0)

Mobile.tap(findTestObject('Object Repository/Mesh/Connect device/Connected remote/btn_search_serialNum'), 0)

Mobile.delay(5) //Chờ kết quả trả về

//Tap vào thiết bị tìm kiếm được
Mobile.tap(findTestObject('Object Repository/Mesh/Connect device/Connected remote/search_result_device'), 0)

Mobile.delay(5) //Chờ di chuyển tới màn Dashboard

//Skip tutorial
TestObject btn_skip = findTestObject('Object Repository/Mesh/Connect device/Connected remote/btn_remote_skip')
if(Mobile.verifyElementExist(btn_skip, 10, FailureHandling.OPTIONAL)) {
	Mobile.tap(btn_skip, 0)
}

//Kiểm tra đã vào được màn Dashboard
Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Connect device/Connected remote/bottomNav_Dashboard'), 0)
Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Connect device/Connected remote/bottomNav_Setting'), 0)
Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Connect device/Connected remote/bottomNav_speedMeasurement'), 0)

Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Connect device/Connected remote/internet_status'), 0)

Mobile.delay(5)
