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
import io.appium.java_client.MobileElement
import one.telco.Randomv

import org.openqa.selenium.Keys as Keys


//Mobile.callTestCase(findTestCase('Test Cases/Mesh/DDNS/OL_48 Move to DDNS'), null, FailureHandling.STOP_ON_FAILURE)
EzAction ez = new EzAction()

List<MobileElement> ddnss = ez.findManyContains('Index:')
if(ddnss.size() == 0) {
	Mobile.callTestCase(findTestCase('Test Cases/Mesh/DDNS/OL_49 Create DDNS'), null, FailureHandling.STOP_ON_FAILURE)
	ddnss = ez.findManyContains('Index:')
}

MobileElement ddns = ddnss.get(ddnss.size() - 1)
ddns.click()

String salt = Randomv.getHexStringFromTimestamp().substring(7)
String newDomain = 'uetube' + salt + '.com.vn'
ez.setTextFriendByText('Host name', newDomain, 1)
ez.tapElementByText('Lưu')

// Verify msg
Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 120)
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Danh sách DDNS'), 10)

ddnss = ez.findManyContains(newDomain)
println 'Find edited ddns with domain ' + newDomain
assert ddnss.size() > 0















