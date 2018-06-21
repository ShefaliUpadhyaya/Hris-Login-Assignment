package com.qait.tap.hristest;

import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class NewEmptyTestNGTest {
    
    WebDriver driver;
    
    LoginForm loginForm;
    @Test
    public void attempt_Login_With_Incorrect_Password_Should_Render_Error_Message(){
        Assert.assertTrue(loginForm
                .loginWithIncorrectCredentials("INVALID_USERNAME", "INVALID_PASSWORD").contains("Invalid Login"));
    }
    
    @Test(dependsOnMethods = {"attempt_Login_With_Incorrect_Password_Should_Render_Error_Message"})
    public void attempt_Login_With_No_Password_Should_Annotate_Black_Password_Field(){
        loginForm.login("raman", "");
        // red border in password entry
        Assert.assertTrue(loginForm.isPasswordEntryAnnotated());  
    }
    
    @Test(expectedExceptions = UnhandledAlertException.class ,dependsOnMethods = {"attempt_Login_With_No_Password_Should_Annotate_Black_Password_Field"})
    public void attempt_Login_With_Correct_Credentials_Should_Not_Render_PasswordField() {
    	loginForm.loginWithCorrectCredentials("", "");  //use correct credentials
    	Assert.assertFalse(loginForm.loginWithCorrectCredentialsDoNotRenderLoginPage());
    }
    
    @Test(expectedExceptions = UnhandledAlertException.class ,dependsOnMethods = {"attempt_Login_With_Correct_Credentials_Should_Not_Render_PasswordField"})
    public void attempt_Login_With_Correct_Credentials_Should_Open_TimeSheet() {
    	loginForm.loginWithCorrectCredentials("", ""); //use correct credentials
    	Assert.assertTrue(driver.getCurrentUrl().contains("employee"));
    }
    
    @BeforeClass
    public void launchBrowser(){
        driver = new ChromeDriver();
        driver.get("https://s-hris.qainfotech.com");
        loginForm = new LoginForm(driver);
    }
    
    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
    
}
