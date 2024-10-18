package com.page.objects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utilities.ReadConfig;

public class JobSearchPage {

	private WebDriver driver;

	ReadConfig config = new ReadConfig();

	public JobSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[@id='filter-freshness']")
	private WebElement selectDropDowBtn;

	@FindBy(xpath = "//ul[@data-filter-id='freshness']/li/a/span")
	private List<WebElement> freshnessDropDownEleList;

	@FindBy(xpath = "//div[contains(@class,'cust-job-tuple')]/div/a")
	private List<WebElement> jobTitleList;

	@FindBy(css = ".exp-wrap span span")
	private List<WebElement> jobExpRangeList;

	@FindBy(xpath = "//button[text()='Save']/following-sibling::button")
	private WebElement applyBtn;

	@FindBy(xpath = "//span[@class='apply-message']")
	private WebElement applyMessage;

	@FindBy(css = ".chatbot_MessageContainer")
	private WebElement chatbot_messagecontainer;

	@FindBy(xpath = "//div[@class='styles_pages__v1rAK']//a[not(contains(@class,'styles_selected'))]")
	private List<WebElement> pagesNotSelectedList;

	@FindBy(xpath = "//a[@class='styles_btn-secondary__2AsIP']")
	private WebElement nextPageBtn;

	public void selectFreshnessFromDropDown() {

		selectDropDowBtn.click();
		for (WebElement fresh : freshnessDropDownEleList) {

			if (fresh.getText().equalsIgnoreCase(config.getPropertyValue("searchResultFreshness"))) {
				fresh.click();
				break;
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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(jobTitleList));
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
			for (WebElement expYearElement : jobExpRangeList) {
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

	public void applyforJobsInAllPages() {

		for (WebElement page : pagesNotSelectedList) {

			applyForJobs();

			page.click();

		}
	}

}
