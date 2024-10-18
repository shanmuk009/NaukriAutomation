package com.page.objects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import com.utilities.ReadConfig;

import junit.framework.Assert;

public class HomePage {

	private WebDriver driver;

	ReadConfig config = new ReadConfig();

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".nI-gNb-icon-img")
	private WebElement img_icon;
	@FindBy(css = ".nI-gNb-list-item:nth-child(4) a")
	private WebElement logout_btn;
	// @FindBy(xpath = "//a[normalize-space()='View profile']")
	@FindBy(xpath = "//div[@class='view-profile-wrapper']/a")
	private WebElement viewprofile_btn;
	@FindBy(css = ".name-wrapper>div")
	private WebElement profile_name;

	@FindBy(xpath = "//a[@title='Recommended Jobs']")
	private WebElement jobsPageLink;

	@FindBy(xpath = "//div[contains(@class,'search-bar')]/div")
	private WebElement searchJobsBar;

	@FindBy(xpath = "//input[contains(@placeholder,'keyword')]")
	private WebElement inputKeyWord;

	@FindBy(xpath = "//input[@id='experienceDD']")
	private WebElement inputExpDropDown;

	@FindBy(xpath = "//div[@class='dropdownPrimary']/div/div/div/ul/li/div/span")
	private List<WebElement> expDropDownList;

	@FindBy(xpath = "//input[@placeholder='Enter location']")
	private WebElement searchLocation;

	@FindBy(xpath = "//button/span[(text()='Search')]")
	private WebElement searchIcon;

	public String getProfileName() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement element = wait.until(ExpectedConditions.visibilityOf(profile_name));

		String actualProfileName = element.getText();
		return actualProfileName;
	}

	public void clickOnViewProfileBtn() {
		viewprofile_btn.click();
	}

	public void logout() {
		img_icon.click();
		logout_btn.click();
	}

	public void naviagateToJobsPage() {
		jobsPageLink.click();
	}

	public void scrollToPageEnd() {
		driver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
	}

	public void selectExpFromDropDown() {
		inputExpDropDown.click();
		for (WebElement exp : expDropDownList) {

			if (exp.getText().equalsIgnoreCase(config.getPropertyValue("experience"))) {
				exp.click();
				break;
			}

		}

	}

	public void searchJobs() {
		searchJobsBar.click();
		inputKeyWord.sendKeys(config.getPropertyValue("jobSearchKeyWords"));
		selectExpFromDropDown();
		searchLocation.sendKeys(config.getPropertyValue("location"));
		searchIcon.click();

	}

}
