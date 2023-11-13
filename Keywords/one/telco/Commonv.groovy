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
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import base.EzAction

class Commonv {
	
	/**
	 * Gọi hàm này để tự động login khi mở app
	 */
	@Keyword
	public static void checkLogin() {
		//
		EzAction ez = new EzAction()
		String txt_hello = 'Xin chào\n' + GlobalVariable.username
		TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
		
		if (!Mobile.verifyElementVisible(hello, 5, FailureHandling.OPTIONAL)) {
			WebUI.callTestCase(findTestCase('Login success'), [('newSession') : 'false'], FailureHandling.STOP_ON_FAILURE)
		}
	}


	/**
	 * Gọi hàm này để tự động logout khi mở app
	 */
	@Keyword
	public static void checkNotLogin() {
		EzAction ez = new EzAction()
		String txt_hello = 'Xin chào\n' + GlobalVariable.username
		TestObject hello = ez.createTestObject('//*[contains(@content-desc, "' + txt_hello + '")]')
		
		if (Mobile.verifyElementVisible(hello, 5, FailureHandling.OPTIONAL)) {
			WebUI.callTestCase(findTestCase('Logout success'), null, FailureHandling.STOP_ON_FAILURE)
		}
	}
}