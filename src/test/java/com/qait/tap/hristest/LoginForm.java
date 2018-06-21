package com.qait.tap.hristest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginForm {
    
    WebElement userNameEntry;
            
    WebDriver driver;
    
    private WebElement getUserNameEntry(){
        return this.driver.findElement(By.id("txtUserName"));
    }
    
    private WebElement getPasswordEntry(){
        return this.driver.findElement(By.id("txtPassword"));
    }
    
    private WebElement getErrorMessageElement(){
        return this.driver.findElement(By.cssSelector(".alert-error"));
    }
    
    public LoginForm(WebDriver driver){
        this.driver = driver;
    }
    
    public String loginWithIncorrectCredentials(String username, String password){
        login(username, password);
        return getErrorMessageElement().getText();
    }
    
    public TimeSheet loginWithCorrectCredentials(String username, String password){
        login(username, password);
        return new TimeSheet(driver);
    }
    
    public void login(String username, String password){
        getUserNameEntry().clear();
        getUserNameEntry().sendKeys(username);
        getPasswordEntry().clear();
        getPasswordEntry().sendKeys(password);
        getPasswordEntry().submit();
    }
    
    public Boolean isErrorMessage(String expectedMessage){
        return getErrorMessageElement().getText()
                .contains(expectedMessage);
    }
    
    public Boolean isPasswordEntryAnnotated(){
        return getPasswordEntry().getAttribute("style").contains("red");
    }
    
    public Boolean loginWithCorrectCredentialsDoNotRenderLoginPage() {
    	return getPasswordEntry().isDisplayed();
    }
}
