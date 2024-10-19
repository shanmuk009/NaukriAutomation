package com.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.baseclass.BaseClass;
import com.page.objects.HomePage;
import com.page.objects.JobSearchPage;
import com.page.objects.JobsPage;
import com.page.objects.LoginPage;
import com.page.objects.ProfilePage;
import com.utilities.ReadConfig;

public class NaukriTests extends BaseClass {

	ReadConfig readconfig;
	LoginPage lp;
	HomePage hp;
	ProfilePage pp;
	JobsPage jp;
	JobSearchPage jsp;
	String resumeName;
	private static boolean isLoggedin = false;

	@BeforeTest
	public void beforeClass() {
		readconfig = new ReadConfig();
		initializeBrowser();
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		pp = new ProfilePage(driver);
		jp = new JobsPage(driver);
		jsp=new JobSearchPage(driver);

	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		logger.info("Test started: " + method.getName());
	}

	@BeforeClass(description = "User Login to Naukri Homepage")
	public void beforeTest() {
		/*
		 * if (!isLoggedin) { lp.login(); isLoggedin = true; }
		 */
		lp.login();
		// validate profileName in HomePage After Login
		Assert.assertEquals(hp.getProfileName(), readconfig.getPropertyValue("profilename"));
		// Click on view Profile Button
		hp.clickOnViewProfileBtn();
	}

	@Test(description = "User Go To ProfilePage and Update the Resume",priority = 0)
	public void updatedResumeInNaukri() {
         //close the chatbot in profilepage if appears
		 pp.closeChatBot();
		// Update resume
		resumeName = readconfig.getPropertyValue("resumeName");
		pp.updateResume(resumeName);
		// validate updated Resume Name
		Assert.assertEquals(pp.getUpdatedResumeName(), resumeName);

	}

	@Test(description = "User Go To ProfilePage and Update Resume HeadLine",priority=1)
	public void updateResumeHeadLine() {

		pp.updateResumeHeadLine();

	}

	@Test(description = "User Go To JobsPage,navigate to required tab and Apply for Jobs",priority = 2)
	public void applyForRecommenedJobs() {

		hp.naviagateToJobsPage();
		
		jp.clickOnDesiredJobTab("you might like");

		jp.applyForJobs();
		
		jp.clickOnDesiredJobTab("profile");
		jp.applyForJobs();
		
		jp.clickOnDesiredJobTab("preferences");

		jp.applyForJobs();
		
		

		hp.scrollToPageEnd();

	}
	

	@Test(description = "user search jobs based on designation, exp , location and apply for desired Jobs",priority = 3)
	public void searchJobsAndApply() {
		hp.searchJobs();
		jsp.selectFreshnessFromDropDown();
		jsp.applyForJobs();
		
		hp.scrollToPageEnd();
	}

	@AfterClass(description = "LogOut from Naukri")
	public void afterTest() {
		hp.logout();

	}

	@AfterTest(description = "close the driver instance")
	public void afterClass() {
		closeBrowser();
	}
}
