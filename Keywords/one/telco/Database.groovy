package one.telco

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.sql.Sql
import internal.GlobalVariable

public class Database {

	//TODO: tái sử dụng Connection đối với Test Suite

	/**
	 * Dùng hàm này để thực thi sql bất kỳ
	 * @param sqlString
	 * @return
	 */
	@Keyword
	public static boolean executeSql(String sqlString) {
		Sql.withInstance(GlobalVariable.sql_url, GlobalVariable.sql_username, GlobalVariable.sql_password, GlobalVariable.sql_driver) { sql ->
			return sql.execute(sqlString)
		}
	}

	/**
	 * Hàm thực thi 1 câu truy vấn, chỉ trả lại row đầu tiên, dữ liệu được nối lại thành string
	 * @param queryString
	 * @return
	 */
	@Keyword
	public static def executeQueryFirstRow(String queryString) {
		def sql = Sql.newInstance(GlobalVariable.sql_url, GlobalVariable.sql_username, GlobalVariable.sql_password, GlobalVariable.sql_driver)
		def firstRow = sql.firstRow(queryString)
		sql.close()

		return firstRow.values().join(',')
	}
}
