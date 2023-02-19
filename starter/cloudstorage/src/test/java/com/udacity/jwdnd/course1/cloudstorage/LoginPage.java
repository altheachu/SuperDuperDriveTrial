package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id="inputUsername")
    private WebElement inputUsername;

    @FindBy(id="inputPassword")
    private WebElement inputPassword;

    @FindBy(id="login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void login(User user){
        inputUsername.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        loginButton.click();
    }

}
