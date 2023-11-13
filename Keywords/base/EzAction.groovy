package base
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement

import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

/**
 * 
 * @author tienpx
 *
 *	EzAction là class với mục đích giảm thiểu việc bắt Object trên điện thoại (bắt ở đây đa phần phải dùng xpath theo tag, rất là tù).
 *	Nên EzAction hướng tới tận dụng content-desc, text dot (.) để bắt những object có các thuộc tính này
 *	Lưu ý: Dùng Spy để chắc chắn là element có chữ để bắt. Không phải mọi chữ trên màn hình đều bắt được
 */
class EzAction {

	AppiumDriver driver

	public EzAction() {
		driver = MobileDriverFactory.getDriver()
	}

	/**
	 * Tìm element B liền kề với element A
	 * position 0 thì B chính là A
	 * position có thể âm, như ở trường hợp sau, phần tử liền kề A có position là -3: (A -1) (A -2) (A -3) (A) (A 1) (A 2) (A 3)
	 * @param text chữ của element A
	 * @param position vị trí của B so với A
	 * @return element B
	 */
	def MobileElement find(String text, int position = 0) {
		String positionPath = ''
		if(position > 0) {
			positionPath += '/following-sibling::*[' + position + ']'
		}
		if(position < 0) {
			positionPath += '/preceding-sibling::*[' + (-position) + ']'
		}

		return driver.findElement(By.xpath('//*[@text = "' + text + '" or @content-desc = "' + text + '" or . = "' + text + '"]' + positionPath))
	}

	/**
	 * Tương tự hàm find, nhưng tìm element A có nội dung chứa text
	 * @param text 1 phần nội dung cần tìm
	 * @param position vị trí của element B so với A
	 * @return element B
	 */
	def MobileElement findContains(String text, int position = 0) {
		String positionPath = ''
		if(position > 0) {
			positionPath += '/following-sibling::*[' + position + ']'
		}
		if(position < 0) {
			positionPath += '/preceding-sibling::*[' + (-position) + ']'
		}

		try {
			return driver.findElement(By.xpath('//*[contains(@text, "' + text + '") or contains(@content-desc, "' + text + '") or contains(. , "' + text + '")]' + positionPath))
		} catch (NoSuchElementException e) {
			return null
		}
	}

	/**
	 * Tương tự hàm findContains, nhưng trả về nhiều element
	 * @param text
	 * @param position
	 * @return
	 */
	def List<MobileElement> findManyContains(String text, int position = 0) {
		String positionPath = ''
		if(position > 0) {
			positionPath += '/following-sibling::*[' + position + ']'
		}
		if(position < 0) {
			positionPath += '/preceding-sibling::*[' + (-position) + ']'
		}

		return driver.findElements(By.xpath('//*[contains(@text, "' + text + '") or contains(@content-desc, "' + text + '") or contains(. , "' + text + '")]' + positionPath))
	}

	/**
	 * Tham khảo https://stackoverflow.com/a/49967315 để biết tổ chức gia phả trong xpath
	 * Lấy các phần tử con của root (chỉ con thôi, không tìm cháu chắt)
	 * @param root container của các element cần lấy
	 * @return các phần tử con của root 
	 */
	def List<MobileElement> getChild(WebElement root){
		return root.findElements(By.xpath('./*'))
	}

	//	def MobileElement getParent(WebElement root){
	//		return root.findElements(By.xpath('./parent::*'))
	//	}

	def List<MobileElement> getSiblingsAndSelf(WebElement root){
		List<MobileElement> list = new ArrayList()
		list.addAll(root.findElements(By.xpath('./following-sibling::*')))
		list.add(root)
		list.addAll(root.findElements(By.xpath('./following-sibling::*')))
		return list
	}

	/**
	 * Tap element có chữ khớp với text
	 * @param text
	 */
	def tapElementByText(String text) {
		return tapFriendByText(text, 0)
	}

	/**
	 * Tap element B ở cạnh element A. element A có chữ khớp với text
	 * Xem doc hàm find để biết thêm về position
	 * @param text chữ của element A
	 * @param position vị trí của B so với A
	 * @return
	 */
	def tapFriendByText(String text, int position) {
		MobileElement element = find(text, position)
		element.click()
	}

	def setTextFriendByText(String text, String value, int position) {
		MobileElement element = find(text, position)
		element.click()
		element.clear()
		element.sendKeys(value)
		driver.hideKeyboard()
	}


	@Keyword
	def createTestObject(String xpath) {
		TestObject testObject = new TestObject();
		testObject.addProperty("xpath", ConditionType.EQUALS, xpath);
		return testObject;
	}

	@Keyword
	def createTestObjectFromText(String text) {
		String xpath = '//*[@text = "' + text + '" or @content-desc = "' + text + '" or . = "' + text + '"]'
		return createTestObject(xpath)
	}

	/**
	 * Tìm element B theo text của element A và position của B so với A
	 * Sau đó tap B theo vị trí offsetX, offsetY
	 * với offset tính theo % kích thước element, nên nhận giá trị [-50, 50]
	 * offsetX: tâm element là 0, âm là sang trái
	 * offsetY: tâm element là 0, âm là lên trên
	 * 
	 * @param textA text của A
	 * @param offsetX vị trí cần tap theo trục ngang
	 * @param offsetY vị trí cần tao theo trục dọc
	 * @param position vị trí B so với A, mặc định là 0 (B chính là A)
	 * @return
	 */
	@Keyword
	def findAndTapOffset(String textA, int offsetX, int offsetY, int position = 0) {
		MobileElement element = find(textA, position)
		tapOffset(element, offsetX, offsetY)
	}

	@Keyword
	def findContainsAndTapOffset(String textA, int offsetX, int offsetY, int position = 0) {
		MobileElement element = findContains(textA, position)
		tapOffset(element, offsetX, offsetY)
	}

	@Keyword
	def tapOffset(WebElement element, int offsetX, int offsetY) {
		def width = element.size.width
		def height = element.size.height

		int x = width * offsetX / 100
		int y = height * offsetY / 100

		println 'tapOffset: x - y => ' + x + ' - ' + y

		def actions = new Actions(this.driver)
		actions.moveToElement(element, x, y)
				.click()
				.perform();
	}

	//vuốt
	def swipeRightToLeft(WebElement element, int distancePercentage) {
		def width = element.size().width
		def height = element.size().height

		int startOffsetX = 100 - distancePercentage
		int endOffsetX = 0
		int offsetY = height / 2

		int startX = width * startOffsetX / 100
		int endX = width * endOffsetX / 100

		println "swipeRightToLeft: startX - endX => $startX - $endX"

		def actions = new Actions(driver)
		actions.moveToElement(element, startX, offsetY)
				.clickAndHold()
				.moveByOffset(endX - startX, 0)
				.release()
				.perform()
	}

	//	@Keyword
	//	def
}