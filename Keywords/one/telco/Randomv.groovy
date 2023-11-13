package one.telco
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
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

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class Randomv {
	/**
	 * Tạo string theo timestamp
	 * @return string dưới dạng Hex của timestamp
	 */
	@Keyword
	static def String getHexStringFromTimestamp(Long timestamp) {
		timestamp = timestamp ?: new Date().getTime()
		return Long.toHexString(timestamp)
	}
	@Keyword
	static def String getHexStringFromTimestamp() {
		return getHexStringFromTimestamp(null)
	}

	/**
	 * Tạo số điện thoại 10 ký tự từ timestamp
	 * @return
	 */
	@Keyword
	static def String getPhoneNumberFromTimestamp(Long timestamp) {
		timestamp = timestamp ?: new Date().getTime()
		//Kiểm duyệt né số 0 đứng đầu khi substring ở dòng return
		if(Math.floor(timestamp / 100000000) % 10 == 0) {
			timestamp -= 100000000
		}
		return '0' + timestamp.toString().substring(4)
	}
	@Keyword
	static def String getPhoneNumberFromTimestamp() {
		return getPhoneNumberFromTimestamp(null)
	}

	/**
	 * Lấy một phần tử ngẫu nhiên từ list. Trả null nếu list null hoặc rỗng
	 */
	@Keyword
	static def Object getRandom(List<Object> list) {
		if(list == null || list.size()== 0) {
			return null
		}

		int size = list.size()
		if(size == 1) {
			return list.get(0)
		}

		Random random = new Random()
		return list.get(random.nextInt(size))
	}

	/**
	 * Lấy resultSize phần tử ngẫu nhiên (không trùng) từ list.
	 * Trả list rỗng nếu list null hoặc rỗng
	 */
	@Keyword
	static def List<Integer> getUniqueRandomIndexs(List<Object> list, int resultSize) {
		if(list == null || list.size()== 0) {
			return Collections.emptyList()
		}

		Collections.shuffle(list)

		List<Integer> result = new ArrayList<>()
		resultSize = Math.min(list.size(), resultSize)
		for (int i = 0; i < resultSize; i++) {
			result.add(i);
		}

		return result
	}
}