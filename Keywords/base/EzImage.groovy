package base

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.awt.Color
import java.awt.image.BufferedImage
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Paths

import javax.imageio.ImageIO

import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.WebElement

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
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver

public class EzImage {
	AppiumDriver driver = MobileDriverFactory.getDriver()

	public class Colorv {
		static Color toggleEnable = new Color(33 ,196 ,137)
		static Color toggleDisable = new Color(128 ,128 ,128)
	}

	/**
	 * Có thể dùng hàm này để xem 
	 * @param element
	 * @param positive
	 * @param negative
	 * @return
	 */
	@Keyword
	public static boolean checkElementByColor(WebElement element, Color positive, Color negative, boolean excludeWHITE = true) {
		Color target = getAverageColor(element, excludeWHITE)

		def posDistance = calculateDistance(target, positive)
		def negDistance = calculateDistance(target, negative)

		println 'posDistance: ' + posDistance
		println 'negDistance: ' + negDistance
		return posDistance < negDistance
	}

	/**
	 * Hàm lấy màu trung bình theo RGB của element (đã loại bỏ màu trắng)
	 * @param element mục tiêu cần lấy màu
	 * @return Color màu trung bình của element
	 */
	@Keyword
	public static Color getAverageColor(WebElement element, boolean excludeWHITE = true) {
		File imageFile = element.getScreenshotAs(OutputType.FILE)
		FileUtils.copyFile(imageFile, new File('./' + 'file.png'))

		def redSum = 0
		def greenSum = 0
		def blueSum = 0
		def count = 0

		BufferedImage img = ImageIO.read(imageFile);
		for (int y = 0; y < img.getHeight(); y = y + 3) {
			for (int x = 0; x < img.getWidth(); x = x + 3) {
				//Retrieving contents of a pixel
				int pixel = img.getRGB(x,y);
				if(excludeWHITE && pixel == -1) { //bỏ qua màu trắng 255,255,255
					continue
				}
				//Creating a Color object from pixel value
				Color color = new Color(pixel, true);
				//Retrieving the R G B values
				redSum += (color.getRed());
				greenSum += (color.getGreen());
				blueSum +=(color.getBlue());
				count++;
			}
		}

		int avg_red = redSum / count
		int avg_green = greenSum / count
		int avg_blue = blueSum / count

		println 'avg RGB: ' + avg_red + ' ' + avg_green + ' ' + avg_blue
		return new Color(avg_red, avg_green, avg_blue)
	}

	static def calculateDistance(Color colorA, Color colorB) {
		def distanceR = Math.pow(colorB.red - colorA.red, 2)
		def distanceG = Math.pow(colorB.green - colorA.green, 2)
		def distanceB = Math.pow(colorB.blue - colorA.blue, 2)

		return Math.sqrt(distanceR + distanceG + distanceB)
	}


	public static String getToggleImageB64() throws URISyntaxException, IOException {
		String imgPath = FileSystems.getDefault().getPath("./Images/Toggle OFF.png").normalize().toAbsolutePath().toString();
		File refImgFile = new File(imgPath);
		return Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
	}
}





