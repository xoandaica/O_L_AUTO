import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.testobject.TestObject as TestObject

Mobile.tap(findTestObject('Object Repository/Mesh/Network test/btn_trace_route'),30)

TestObject ping_result = findTestObject('Object Repository/Mesh/Network test/ping_result')
Mobile.verifyElementNotExist(ping_result, 0)

Mobile.tap(findTestObject('Object Repository/Mesh/Network test/button_network diagnostic'), 30)

//Kiểm tra trace thành công
Mobile.delay(30)

TestObject list_result = findTestObject('Object Repository/Mesh/Network test/list_result_trace')
TestObject txt_result = findTestObject('Object Repository/Mesh/Network test/txt_result')
TestObject txt_complete = findTestObject('Object Repository/Mesh/Network test/txt_complete')

Mobile.verifyElementExist(list_result, 30)
Mobile.verifyElementExist(txt_result, 30)
Mobile.verifyElementExist(txt_complete, 30)

List<WebElement> list_trace_result = MobileDriverFactory.getDriver().findElements(By.xpath('//*[contains(@content-desc, "IP Address")]'))

if(list_trace_result.size() != 0) {
	println('Trace success')
} else println('Failed')

//Mobile.closeApplication()