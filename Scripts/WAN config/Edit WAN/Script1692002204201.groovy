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
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

Mobile.startApplication(GlobalVariable.apk, false)
Commonv.checkLogin()
EzAction ez = new EzAction()

TestObject serialInputObj = findTestObject('Object Repository/Home/EditText_search_serial')
TestObject searchBtn = findTestObject('Object Repository/Home/ImageView_search_btn')

//Tìm kiếm thiết bị
Mobile.tap(serialInputObj, 0)
Mobile.setText(serialInputObj, GlobalVariable.meshSerial, 0)
Mobile.tap(searchBtn, 0)

//Vào màn cấu hình thiết bị
Mobile.waitForElementPresent(findTestObject('Object Repository/Home/Label_Ket qua tim kiem'), 20)
Mobile.tap(findTestObject('Object Repository/Home/Panel_device'), 0)

//Vào màn cấu hình WAN
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Button_Cai Dat'), 0)
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device setting/Button_Cau hinh Mang'), 0)
Mobile.tap(findTestObject('Object Repository/Mesh/Device/Device setting/Cau hinh Mang/Button_Cau hinh WAN'), 0)

TestObject wan0 = ez.createTestObject('//*[contains(@content-desc, "WAN Index: 0")]')
Mobile.waitForElementPresent(wan0, 5)

List<WebElement> wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
if(wans.size() == 1) {
	WebUI.callTestCase(findTestCase('WAN config/Add WAN'), [:], FailureHandling.STOP_ON_FAILURE)
	wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
}

//Chọn Wan cuối để sửa vlanID
wans.last().click()
MobileElement vlanObj = ez.find('VLAN ID', 1)
def oldVlan = vlanObj.text as int
def newVlan = (Math.floor(oldVlan / 10) % 2 == 0) ? oldVlan - 10 : oldVlan + 10

ez.setTextFriendByText('VLAN ID', newVlan.toString(), 1)
ez.tapElementByText('Lưu')

Mobile.delay(20)
Mobile.waitForElementPresent(ez.createTestObjectFromText('Xác nhận'), 120)
Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 5)

ez.tapElementByText('Xác nhận')
Mobile.delay(5)
wans = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "WAN Index:")]'))
wans.last().click()

vlanObj = ez.find('VLAN ID', 1)
assert vlanObj.text == newVlan.toString()










