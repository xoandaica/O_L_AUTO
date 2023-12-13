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
import io.appium.java_client.MobileElement

Mobile.startApplication(GlobalVariable.apk, false)

Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_21 Verify connect Remote success'), [('newSessionConnect') : 'false'], FailureHandling.STOP_ON_FAILURE)

//----------------------------------------Lấy thông tin wifi hiện tại----------------------------------------------------------------------
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Dashboard/Remote/OL_23 View wifi info success'), null, FailureHandling.STOP_ON_FAILURE)

String wifiName_init = GlobalVariable.wifiName_Mesh
String wifiPW_init = GlobalVariable.wifiPass_Mesh
println('Wi-Fi name init: ' + wifiName_init + ' -- Wi-Fi password init: ' + wifiPW_init)

//----------------------------------------Tạo file backup config----------------------------------------
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Device control/Backup Config/Remote/OL_103 Create Backup file success'), [('newSessionBkCreate') : 'false'], FailureHandling.STOP_ON_FAILURE)
String fileBkup_name = GlobalVariable.fileBkup_name
println('Tên file backup: ' + fileBkup_name)

//----------------------------------------Chỉnh sửa wifi----------------------------------------
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Dashboard/Remote/OL_28 Update wifi infor success'), [('newSessionEditWifi') : 'false'], FailureHandling.STOP_ON_FAILURE)
String wifiName_after = GlobalVariable.wifiName_Mesh
String wifiPW_after = GlobalVariable.wifiPass_Mesh
println('Wi-Fi name after: ' + wifiName_after + ' -- Wi-Fi password after: ' + wifiPW_after)

if(wifiName_init != wifiName_after && wifiPW_init != wifiPW_after) {
	assert true
} else {
	println('Thông tin wifi chưa đúng!')
	assert false
}

Mobile.delay(200)

Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/Connect wifi'), [('wifi_nameLocal') : wifiName_after, ('wifi_passLocal') : wifiPW_after], FailureHandling.STOP_ON_FAILURE)

//----------------------------------------Restore config----------------------------------------
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Device control/Restore/Remote/OL_96 Access Restore screen success'), [('newSessionRestore') : 'false'], FailureHandling.STOP_ON_FAILURE)

//TestObject btn_select_file = findTestObject('Object Repository/Mesh/Device/Device control/Restore config/selectFile')
//Mobile.tap(btn_select_file, 30)
EzAction ez = new EzAction()
ez.tapFriendByText('File config', 1)
Mobile.delay(5)


TestObject fileBkup = ez.createTestObject('//*[contains(@content-desc, "' + fileBkup_name + '")]')
TestObject btn_RestoreConfig = findTestObject('Object Repository/Mesh/Device/Device control/Restore config/btn_RestoreConfig')

Mobile.tap(fileBkup, 30)
Mobile.tap(btn_RestoreConfig, 30)

ez.tapElementByText('Đồng ý')
Mobile.delay(5)

TestObject txt_restore_success = ez.createTestObjectFromText('Gửi yêu cầu thành công, thiết bị đang thực hiện Restore Config, vui lòng chờ khoảng 5 phút trước khi thực hiện thao tác tiếp theo với thiết bị')
Mobile.verifyElementExist(txt_restore_success, 30)
ez.tapElementByText('Xác nhận')

//----------------------------------------Check sau khi restore tự động quay lại màn Home----------------------------------------
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)

Mobile.delay(300)

Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_21 Verify connect Remote success'), [('newSessionConnect') : 'false'], FailureHandling.STOP_ON_FAILURE)

//----------------------------------------Kiểm tra thông tin wifi được restore----------------------------------------
Mobile.delay(60)
ez.tapFriendByText(GlobalVariable.modelName, 1)
Mobile.delay(120)
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Dashboard/Remote/OL_23 View wifi info success'), null, FailureHandling.STOP_ON_FAILURE)

String wifiName_restore = GlobalVariable.wifiName_Mesh
String wifiPW_restore = GlobalVariable.wifiPass_Mesh
println('Wi-Fi name init: ' + wifiName_init + ' -- Wi-Fi password init: ' + wifiPW_init)
println('Wi-Fi name after: ' + wifiName_after + ' -- Wi-Fi password after: ' + wifiPW_after)
println('Wi-Fi name restore: ' + wifiName_restore + ' -- Wi-Fi password restore: ' + wifiPW_restore)

if(wifiName_restore == wifiName_init && wifiPW_restore == wifiPW_init) {
	println('Restore thành công!')
	assert true
} else {
	println('Restore thất bại!')
	assert false
}

//Quay về màn Home
ez.tapFriendByText(GlobalVariable.modelName, -1)
Mobile.delay(300)
