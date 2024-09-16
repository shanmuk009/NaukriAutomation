package com.page.objects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import junit.framework.Assert;

public class HomePage {

	private WebDriver driver;

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".nI-gNb-icon-img")
	private WebElement img_icon;
	@FindBy(css = ".nI-gNb-list-item:nth-child(4) a")
	private WebElement logout_btn;
	//@FindBy(xpath = "//a[normalize-space()='View profile']")
	@FindBy(xpath = "//div[@class='view-profile-wrapper']/a")
	private WebElement viewprofile_btn;
	@FindBy(css = ".name-wrapper>div")
	private WebElement profile_name;
	
	@FindBy(xpath="//a[@title='Recommended Jobs']")
	private WebElement jobsPageLink;

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

}
