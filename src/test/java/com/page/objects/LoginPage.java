package com.page.objects;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;

import com.baseclass.BaseClass;
import com.utilities.ReadConfig;

public class LoginPage extends BaseClass {

	ReadConfig readconfig = new ReadConfig();
	String url = readconfig.getPropertyValue("loginpageurl");
	String email = readconfig.getPropertyValue("username");
	String password = readconfig.getPropertyValue("password");

	private WebDriver driver;

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "login_Layer")
	private WebElement login_link_btn;
	@FindBy(xpath = "//input[@placeholder='Enter your active Email ID / Username']")
	private WebElement username_selector;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement password_selector;
	@FindBy(css = ".loginButton")
	private WebElement login_btn;

	public void navigateToLoginPageUrl() {
		if (!url.isEmpty() && !url.isBlank()) {
			driver.get(url);
		} else {
			throw new IllegalArgumentException("URL cannot be empty or blank");
		}
	}

	public void clickOnLoginLinkButton() {
		login_link_btn.click();
	}

	public void enterUsername() {
		username_selector.sendKeys(email);
	}

	public void enterPassword() {
		password_selector.sendKeys(password);
	}

	public void clickLoginButton() {
		login_btn.click();
	}

	public void login() {

		navigateToLoginPageUrl();
		clickOnLoginLinkButton();
		enterUsername();
		enterPassword();
		clickLoginButton();

	}

}
