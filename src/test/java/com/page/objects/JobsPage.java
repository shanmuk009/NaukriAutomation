package com.page.objects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utilities.ReadConfig;

public class JobsPage {
	private WebDriver driver;

	ReadConfig config = new ReadConfig();

	public JobsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='tab-list']/div/div[1]")
	private List<WebElement> jobTabsList;

	@FindBy(css = ".list p")
	private List<WebElement> jobTitleList;

	@FindBy(css = ".list ul li:first-child span")
	private List<WebElement> expYearsList;

	@FindBy(xpath = "//button[text()='Save']/following-sibling::button")
	private WebElement applyBtn;

	@FindBy(xpath = "//span[@class='apply-message']")
	private WebElement applyMessage;

	@FindBy(css = ".chatbot_MessageContainer")
	private WebElement chatbot_messagecontainer;
	// chatbot_MessageContainer

	public void clickOnDesiredJobTab(String tabName) {
		for (WebElement tab : jobTabsList) {
			if (tab.getText().toLowerCase().contains(tabName.toLowerCase())) {
				tab.click();
			}
		}

	}

	public void applyForJobOnNewWindowTab() {

		// Store the current window handle
		String currentWindow = driver.getWindowHandle();

		// Switch to the new tab
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(currentWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		try {
			applyBtn.click();

			try {
				chatbot_messagecontainer.isDisplayed();
				driver.close();

			} catch (Exception e) {
				// wait for Apply Message to be Visible
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
				wait.until(ExpectedConditions.visibilityOf(applyMessage));
				// close the Tab
				driver.close();

			}
		} catch (Exception e) {
			driver.close();

		}

		// switch to parent Tab
		driver.switchTo().window(currentWindow);
	}

	public void applyForJobs() {
		// Extract keywords and years from configuration
		String[] keywordsArray = config.getPropertyValue("jobTitleKeywords").split(",");
		String[] yearsArray = config.getPropertyValue("expRangeYears").split(",");

		// Iterate through job titles
		for (WebElement jobTitle : jobTitleList) {
			String titleText = jobTitle.getText();

			// Check if the job title contains any keyword
			boolean keywordMatch = false;
			for (String keyword : keywordsArray) {
				if (titleText.contains(keyword)) {
					keywordMatch = true;
					break; // Exit the loop if a keyword is found
				}
			}

			// Skip the rest if no keyword match is found
			if (!keywordMatch) {
				continue;
			}

			// Iterate through experience years
			for (WebElement expYearElement : expYearsList) {
				String expYearText = expYearElement.getText();

				// Check if the experience year starts with any of the configured years
				boolean yearMatch = false;
				for (String year : yearsArray) {
					if (expYearText.startsWith(year)) {
						yearMatch = true;
						break; // Exit the loop if a matching year is found
					}
				}

				// Apply for the job if a matching year is found
				if (yearMatch) {
					jobTitle.click(); // Click on the job title to apply
					applyForJobOnNewWindowTab(); // Open new window/tab to apply
					break; // Move to the next job title after applying
				}
			}
		}
	}

}
