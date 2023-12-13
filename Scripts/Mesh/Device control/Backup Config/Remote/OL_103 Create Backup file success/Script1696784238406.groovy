import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.apache.commons.lang.RandomStringUtils

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject

import base.EzAction
import internal.GlobalVariable

Mobile.startApplication(GlobalVariable.apk, false)

Mobile.callTestCase(findTestCase('Test Cases/Mesh/Device control/Backup Config/Remote/OL_101 Access Backup Config screen success'), [('newSessionBackup') : 'false'], FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()

//Check số lượng file backup hiện có
TestObject list_fileBackup = findTestObject('Object Repository/Mesh/Device/Device control/Backup Config/txt_list_fileBackup')
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

////Kiểm tra số lượng file backup thay đổi
//list_fileBackup = findTestObject('Object Repository/Mesh/Device/Device control/Backup Config/txt_list_fileBackup')
//String listNum_after = Mobile.getAttribute(list_fileBackup, 'contentDescription', 5)
//
//if(listNum_init.contains('03')) {
//	assert listNum_init == listNum_after
//} else assert listNum_init != listNum_after
//
////Quay về màn Dashboard
//ez.tapFriendByText('Quản lý File Backup', -1)
//ez.tapFriendByText('Điều khiển thiết bị', -1)
//ez.findContains('Dashboard', 0).click()
//
////Mobile.closeApplication()