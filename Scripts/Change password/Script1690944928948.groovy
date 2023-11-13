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
import internal.GlobalVariable
import one.telco.Commonv
import one.telco.Database
import base.EzAction

import org.openqa.selenium.Keys as Keys

Mobile.startApplication(GlobalVariable.apk, false)
Commonv.checkLogin()
EzAction ez = new EzAction()

//Di chuyển tới màn Đổi mật khẩu
TestObject btn_setting = findTestObject('Object Repository/Login/img_setting')
Mobile.tap(btn_setting, 30)
ez.tapElementByText('Tài khoản')
ez.tapElementByText('Đổi mật khẩu')

//Chuẩn bị dữ liệu
String queryPassword = 'SELECT password FROM users WHERE username = "' + GlobalVariable.username +'"'
String oldEncryptedPassword = Database.executeQueryFirstRow(queryPassword)
String oldPassword = GlobalVariable.password
String newPassword = GlobalVariable.password + 'UwU'    // :3

//Điền form đổi mật khẩu
TestObject oldPasswordObj = findTestObject('Object Repository/Change password/old_pws')
TestObject newPasswordObj = findTestObject('Object Repository/Change password/new_pws')
TestObject repeatPasswordObj = findTestObject('Object Repository/Change password/new_pws_repeat')

Mobile.tap(oldPasswordObj, 0)
Mobile.setText(oldPasswordObj, GlobalVariable.password, 0)
Mobile.tap(newPasswordObj, 0)
Mobile.setText(newPasswordObj, newPassword, 0)
Mobile.tap(repeatPasswordObj, 0)
Mobile.setText(repeatPasswordObj, newPassword, 0)
Mobile.hideKeyboard()

ez.tapElementByText('Lưu lại')

//Đăng nhập với mật khẩu mới để kiểm tra
String change_pws_success = 'Đổi mật khẩu thành công. Vui lòng đăng nhập lại với mật khẩu vừa đổi'
Mobile.waitForElementPresent(ez.createTestObjectFromText(change_pws_success), 30)

ez.tapElementByText('Đăng nhập')
WebUI.callTestCase(findTestCase('Login success'), [('password'): newPassword, ('newSession'): 'false'], FailureHandling.CONTINUE_ON_FAILURE)

//Khôi phục mật khẩu trước khi đổi
String restorePasswordSql = 'UPDATE users SET `password` = "' + oldEncryptedPassword + '" WHERE username = "' + GlobalVariable.username + '"'
Database.executeSql(restorePasswordSql)

//Xác nhận đang ở màn Home
String txt_hello = 'Xin chào\n' + GlobalVariable.username
TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
Mobile.verifyElementExist(hello, 30)
