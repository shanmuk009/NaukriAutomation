package com.baseclass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();

	String browser = readconfig.getPropertyValue("browser");

	public static WebDriver driver;

	protected static final Logger logger = LogManager.getLogger(BaseClass.class);

	public void initializeBrowser() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disbale-images");

		if (browser.equals("chrome")) {
			// set up webdriver manager
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver(options);
		} else if (browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "D:/Eclipse WorkPlace/BDD/Drivers/edgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void closeBrowser() {
		driver.quit();
	}

	public static void retrieveCookies() throws IOException {

		// Retrieve cookies
		Set<Cookie> cookies = driver.manage().getCookies();

		// Save cookies to a file
		File file = new File("cookies.data");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for (Cookie cookie : cookies) {
				writer.write(cookie.getName() + ";" + cookie.getValue() + ";" + cookie.getDomain() + ";"
						+ cookie.getPath() + ";" + cookie.getExpiry() + ";" + cookie.isSecure());
				writer.newLine();
			}
		}
	}

	public static void setCookies() throws FileNotFoundException, IOException {

		// Load cookies from the saved file
		File file = new File("cookies.data");
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] cookieDetails = line.split(";");
				String name = cookieDetails[0];
				String value = cookieDetails[1];
				String domain = cookieDetails[2];
				String path = cookieDetails[3];
				Cookie cookie = new Cookie.Builder(name, value).domain(domain).path(path).build();
				driver.manage().addCookie(cookie);
			}
		}

		// Refresh the page to load the session with the cookies
		driver.navigate().refresh();

		// Now you should be logged in without the need for login steps

	}

}
