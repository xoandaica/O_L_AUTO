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




// Vào màn hình cấu hình Interface Group
Mobile.callTestCase(findTestCase('Test Cases/ONT/Interface Group Config/Remote/Move to Interface Group Config'), null, FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()

//check danh sách có 1 interface thì call testcase add
List<MobileElement> interfaces = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "Index:")]'))
 if(interfaces.size() == 1) {
	 WebUI.callTestCase(findTestCase('Test Cases/ONT/Interface Group Config/Remote/Interface Group_Creat'), [:], FailureHandling.STOP_ON_FAILURE)
	 interfaces = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "Index:")]'))
 }

//Chọn interface cuối và xoá (không được phải chuyển thành vuốt do interface không có màn hình chi tiết)
interfaces.last().click()
ez.tapElementByText('Xóa Interface Group')
ez.tapElementByText('Đồng ý') 

//check kết quả
Mobile.verifyElementExist(ez.createTestObjectFromText('Xóa thành công'), 120)
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Cấu hình Interface Group'), 5)
