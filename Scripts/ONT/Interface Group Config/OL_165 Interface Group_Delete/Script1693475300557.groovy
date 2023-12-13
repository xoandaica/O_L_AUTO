import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory

import base.EzAction as EzAction
import org.openqa.selenium.By
import base.EzAction
import io.appium.java_client.MobileElement
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import io.appium.java_client.android.AndroidElement

 
EzAction ez = new EzAction()

//check danh sách có 1 interface thì call testcase add
List<MobileElement> interfaces = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "Index:")]'))
if(interfaces.size() == 1) {
	WebUI.callTestCase(findTestCase('Test Cases/ONT/Interface Group Config/OL_164 Interface Group_Create'), [:], FailureHandling.STOP_ON_FAILURE)
	interfaces = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "Index:")]'))
}
	
// Vuốt interface thứ 2 và xoá 
ez.swipeRightToLeft(interfaces.get(1), 60) 
ez.tapElementByText('Xóa')
ez.tapElementByText('Đồng ý') 

//check kết quả
Mobile.verifyElementExist(ez.createTestObjectFromText('Xóa thành công'), 120)
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Cấu hình Interface Group'), 30)
