package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "buttonSignUp")
    private WebElement buttonSignUp;

    public SignupPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void signup(User user){
        inputFirstName.sendKeys(user.getFirstname());
        inputLastName.sendKeys(user.getLastname());
        inputUsername.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        buttonSignUp.click();
    }
}
