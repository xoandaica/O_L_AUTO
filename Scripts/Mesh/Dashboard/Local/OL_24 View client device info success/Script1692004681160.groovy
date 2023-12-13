import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling

import base.EzAction
import internal.GlobalVariable
 
Mobile.callTestCase(findTestCase('Test Cases/Mesh/Connect device/OL_20 Verify connect Local success'), [('newSessionConnect') : 'false'], FailureHandling.STOP_ON_FAILURE)
//Di chuyển tới màn Thiết bị khách
Mobile.tap(findTestObject('Object Repository/Mesh/Dashboard/View list client device/btn_client_device'), 0)

Mobile.verifyElementExist(findTestObject('Object Repository/Mesh/Dashboard/View list client device/title_client_device'), 0)

//Check có bao nhiêu client device
if(Mobile.verifyElementVisible(findTestObject('Object Repository/Mesh/Dashboard/View list client device/img_not_client_device'), 0, FailureHandling.OPTIONAL)) {
	Mobile.verifyElementVisible(findTestObject('Object Repository/Mesh/Dashboard/View list client device/not_have_any_client_device'), 0)
	System.out.println('Không có thiết bị khách!')
} else {
	Mobile.verifyElementVisible(findTestObject('Object Repository/Mesh/Dashboard/View list client device/numberOfClientDevice'), 0)
	String numOfClientDevice = Mobile.getAttribute(findTestObject('Object Repository/Mesh/Dashboard/View list client device/numberOfClientDevice'), 'contentDescription', 5)
	System.out.println('Số thiết bị khách: ' + numOfClientDevice)
	
	List<WebElement> client_devices = MobileDriverFactory.getDriver().findElements(By.xpath('//*[contains(@content-desc, "MAC")]'))
	System.out.println('Đếm trong list: ' + client_devices.size())
	assert client_devices.size() == numOfClientDevice.toInteger()
}

EzAction ez = new EzAction()
ez.tapFriendByText('Thiết bị khách', -1)