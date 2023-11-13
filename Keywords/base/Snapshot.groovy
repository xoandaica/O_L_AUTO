package base

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.concurrent.TimeUnit

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By.ByXPath
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Wait
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.functions.ExpectedCondition

/**
 * Class Snapshot hiện dùng để thao tác với các element không ổn định, có thể xuất hiện hoặc không (có thì tap, không thì thôi)
 * Với các element cố định thì nên dùng class EzAction
 * 
 * Lưu ý: class có sửa đổi tham số implicitWait về 1s, sau khi thao tác xong sẽ đặt lại về 30s
 * 
 * 
 * @author tienpx
 *
 */
public class Snapshot {
	
	/**
	 * Tìm element với timeout 1s
	 * @param text
	 * @return
	 */
	@Keyword
	def static WebElement peekElementByContains(String text, int timeout = 1) {
		AppiumDriver driver = MobileDriverFactory.getDriver()
		
		try {
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
			
			String xpath = '//*[contains(@text, "' + text + '") or contains(@content-desc, "' + text + '") or contains(. , "' + text + '")]'
			return driver.findElement(By.xpath(xpath))
		}catch(Exception e){
			println "[Snapshot] $e"
			return null
		}finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}
	
	/**
	 * Tap element với timeout 1s
	 * @param text
	 * @param timeout
	 */
	@Keyword
	def static boolean tapIfPresentAndContains(String text, int timeout = 1) {
		WebElement element = peekElementByContains(text, timeout)
		println element
		
		if(element != null) {
			println "[Snapshot] click $text"
			element.click()
			return true
		}else {
			println "[Snapshot] $text not found"
			return false
		}
	}
	
}
