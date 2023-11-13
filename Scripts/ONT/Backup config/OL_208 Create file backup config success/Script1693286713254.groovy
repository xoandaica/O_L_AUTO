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

import base.EzAction
import internal.GlobalVariable
import org.apache.commons.lang.RandomStringUtils

EzAction ez = new EzAction()

//Check số lượng file backup hiện có
TestObject list_fileBackup = findTestObject('Object Repository/ONT/Backup Config/list_file_backup')
Mobile.verifyElementExist(list_fileBackup, 30)
String listNum_init = Mobile.getAttribute(list_fileBackup, 'contentDescription', 5)

ez.tapFriendByText('Quản lý File Backup', 1)
Mobile.waitForElementPresent(ez.createTestObjectFromText('Thêm mới File Backup'), 30)

TestObject tbx_fileName = findTestObject('Object Repository/Mesh/Device/Device control/Backup Config/tbx_fileName')
String fileName = 'bkup' + RandomStringUtils.randomNumeric(3)
GlobalVariable.fileBkup_name = fileName

Mobile.tap(tbx_fileName, 30)
Mobile.setText(tbx_fileName, fileName, 30)

ez.tapElementByText('Lưu')

if(listNum_init.contains('03')) {
	String fileReplace = 'File backup mới sẽ thay thế cho file cũ nhất. Bạn có muốn tiếp tục thực hiện'
	Mobile.waitForElementPresent(ez.createTestObjectFromText(fileReplace), 30)
	ez.tapElementByText('Đồng ý')
}

Mobile.delay(20)
Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Device/Device control/Backup Config/txt_save_success'), 30)
ez.tapElementByText('Xác nhận')

//Xác nhận quay về màn Quản lý file Backup
Mobile.waitForElementPresent(ez.createTestObjectFromText('Quản lý File Backup'), 30)

//Kiểm tra số lượng file backup thay đổi
list_fileBackup = findTestObject('Object Repository/Mesh/Device/Device control/Backup Config/txt_list_fileBackup')
String listNum_after = Mobile.getAttribute(list_fileBackup, 'contentDescription', 5)

if(listNum_init.contains('03')) {
	assert listNum_init == listNum_after
} else assert listNum_init != listNum_after

//Quay về màn Dashboard
ez.tapFriendByText('Quản lý File Backup', -1)
ez.tapFriendByText('Điều khiển thiết bị', -1)
ez.findContains('Dashboard', 0).click()

