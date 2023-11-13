import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject


if("$connectMode" == 'local') {
	Mobile.callTestCase(findTestCase('Test Cases/ONT/Connect device/OL_20 Connect ONT Local'), null, FailureHandling.STOP_ON_FAILURE)
}

if("$connectMode" == 'remote') {
	Mobile.callTestCase(findTestCase('Test Cases/ONT/Connect device/OL_21 Connect ONT Remote'), null, FailureHandling.STOP_ON_FAILURE)
}















