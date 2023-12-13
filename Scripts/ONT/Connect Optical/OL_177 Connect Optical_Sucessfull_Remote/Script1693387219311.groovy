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
 

Mobile.tap(findTestObject('Object Repository/Connect Optical/btn_setting'), 30)

Mobile.tap(findTestObject('Object Repository/Connect Optical/btn_checkconnect_view'), 30)

Mobile.tap(findTestObject('Object Repository/Connect Optical/check_connect_optical'), 30)

Mobile.verifyElementExist(findTestObject('Object Repository/Connect Optical/title_connect_optical'), 30, FailureHandling.STOP_ON_FAILURE)

//Click btn Kết nối
TestObject Rx_init = findTestObject('Object Repository/Connect Optical/Rx_init')

String Rx_init_num = Mobile.getAttribute(Rx_init, 'contentDescription', 5)
println('Rx_init: ' + Rx_init_num)

Mobile.tap(findTestObject('Object Repository/Connect Optical/btn_checkconnect'), 30)

TestObject Rx_after = findTestObject('Object Repository/Connect Optical/Rx_after')
String Rx_after_num = Mobile.getAttribute(Rx_after, 'contentDescription', 5)
println('Rx_after: ' + Rx_after_num)

if(Rx_init_num != Rx_after_num) {
	assert true
} else assert false