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
import base.EzAction
import org.openqa.selenium.Keys as Keys


if("$newSessionConnect" == 'true') {
	Mobile.startApplication(GlobalVariable.apk, false)
}
Commonv.checkLogin()
EzAction ez = new EzAction()

TestObject btn_setting = findTestObject('Object Repository/Login/img_setting')

//Thao tác đăng xuất
Mobile.tap(btn_setting, 0)
ez.tapElementByText('Tài khoản')
ez.tapElementByText('Đăng xuất')
ez.tapElementByText('Đồng ý')

//Xác nhận đang ở màn login
Mobile.verifyElementVisible(findTestObject('Object Repository/Login/Button_Login'), 10, FailureHandling.STOP_ON_FAILURE)

