import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import base.EzAction

EzAction ez = new EzAction()

ez.findContains('Cài đặt', 0).click()
ez.tapElementByText('Điều khiển thiết bị')
ez.tapElementByText('Reset')

//Xác nhận đã vào màn Reset
Mobile.waitForElementPresent(ez.createTestObjectFromText('Factory Reset'), 30)
Mobile.waitForElementPresent(ez.createTestObjectFromText('Khôi phục cài đặt gốc của thiết bị'), 30)

//Kiểm tra số lượng thiết bị
List<WebElement> devices = MobileDriverFactory.getDriver().findElements(By.xpath('//*[contains(@content-desc, "Serial Number")]'))
System.out.println('Đếm trong list: ' + devices.size())
String deviceNum = devices.size().toString()

String numOfDevice = Mobile.getAttribute(findTestObject('Object Repository/Mesh/Device/Device control/Reset/txt_list_device'), 'contentDescription', 5)
System.out.println('Số thiết bị khách: ' + numOfDevice)

if(numOfDevice.contains(deviceNum)) {
	assert true
} else assert false
