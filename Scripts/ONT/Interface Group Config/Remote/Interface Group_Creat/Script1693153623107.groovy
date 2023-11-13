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

// Vào màn hình danh sách cấu hình Interface Group
Mobile.callTestCase(findTestCase('Test Cases/ONT/Interface Group Config/Remote/Move to Interface Group Config'), null, FailureHandling.STOP_ON_FAILURE)

EzAction ez = new EzAction()

//check danh sách có nhiều hơn 4 thì xóa 1
List<MobileElement> interfaces = ez.driver.findElements(By.xpath('//*[contains(@content-desc, "Index:")]'))
 if(interfaces.size() == 4) {
	 WebUI.callTestCase(findTestCase('Test Cases/ONT/Interface Group Config/Remote/Interface Group_Delete'), [:], FailureHandling.STOP_ON_FAILURE)
 }

//chọn tạo mới
Mobile.tap(findTestObject('Object Repository/ONT/Interface/add_interface'), 0)

//Nhập thông tin
def groupName = findTestObject('Object Repository/ONT/Interface/textbox_group_name')
Mobile.tap(groupName, 0)
Mobile.setText(groupName, 'test', 0)
Mobile.tap(findTestObject('Object Repository/ONT/Interface/wan_list'), 0)
Mobile.tap(findTestObject('Object Repository/ONT/Interface/wan_popup'), 0)
Mobile.tap(findTestObject('Object Repository/ONT/Interface/lan_list'), 0)
Mobile.tap(findTestObject('Object Repository/ONT/Interface/lan_popup'), 0)
Mobile.tap(findTestObject('Object Repository/ONT/Interface/btn_save_lan'), 0)
Mobile.tap(findTestObject('Object Repository/ONT/Interface/btn_save'), 0)

//check kq
Mobile.verifyElementExist(ez.createTestObjectFromText('Lưu thành công!'), 120)  
ez.tapElementByText('Xác nhận')
Mobile.waitForElementPresent(ez.createTestObjectFromText('Cấu hình Interface Group'), 5)





