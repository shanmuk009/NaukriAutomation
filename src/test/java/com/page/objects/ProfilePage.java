package com.page.objects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utilities.ReadConfig;

public class ProfilePage {

	
	private WebDriver driver;
	ReadConfig config = new ReadConfig();

	// Constructor
	public ProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".crossIcon.chatBot")
	private WebElement chatbot_cross;
	@FindBy(xpath = "//input[@type='file'][@id='attachCV']")
	private WebElement updateResumeBtn;
	@FindBy(css = "span[data-title='delete-resume']>.icon")
	private WebElement deleteResumeIcon;
	@FindBy(css = ".resume-name-inline>div")
	private WebElement updatedResumeHeading;
	@FindBy(css = ".resumeHeadline>div>div>div>span:nth-child(2)")
	private WebElement resumeHeadlineEditIcon;
	@FindBy(xpath = "//textarea[@id='resumeHeadlineTxt']")
	private WebElement resumeHeadlineTextArea;
	@FindBy(css = ".action.s12>button")
	private WebElement saveResumeHeadlineBtn;
	@FindBy(xpath = "//p[contains(text(),'delete the resume?')]/following-sibling::div/button")
	private WebElement deleteResumeConfirmBtn;

	
	public void closeChatBot() {
		try {
			if (chatbot_cross.isDisplayed()) {
				chatbot_cross.click();
			}
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
		}
	}

	
	public void deleteResume() {
		deleteResumeIcon.click();
		deleteResumeConfirmBtn.click();
	}

	public void updateResume(String resumeName) {
		String filePath = "D:\\automation\\naukri_Automation\\Documents\\" + resumeName;

		try {
			// Try to delete the existing resume
			deleteResume();

			// If deletion is successful, upload the new resume
			updateResumeBtn.sendKeys(filePath);
			System.out.println("Resume updated successfully after deletion.");

		} catch (Exception e) {
			// If there is an exception during deletion, upload the new resume directly
			System.out.println("Resume could not be deleted. Uploading new resume instead. Error: " + e.getMessage());
			updateResumeBtn.sendKeys(filePath);

		}
	}



	public String getUpdatedResumeName() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(updatedResumeHeading)).getText();
	}

	public void updateResumeHeadLine() {
		resumeHeadlineEditIcon.click();
		resumeHeadlineTextArea.clear();
		resumeHeadlineTextArea.sendKeys(config.getPropertyValue("resumeHeadLine"));
		saveResumeHeadlineBtn.click();

	}


}