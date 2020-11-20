import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.anthem.com/')

WebUI.click(findTestObject('Object Repository/Page_Anthem Blue Cross Blue Shield Health I_7d719a/span_Log In'))

WebUI.click(findTestObject('Object Repository/Page_Log In to Your Anthem Account  Anthem.com/a_Employers'))

WebUI.click(findTestObject('Object Repository/Page_Log In to Your Anthem Account  Anthem.com/a_Producers'))

WebUI.click(findTestObject('Object Repository/Page_Log In to Your Anthem Account  Anthem.com/a_COVID-19 Info'))

WebUI.click(findTestObject('Object Repository/Page_Log In to Your Anthem Account  Anthem.com/a_COVID-19 Resource Center_ant-close-btn fa_a75262'))

WebUI.closeBrowser()

