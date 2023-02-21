package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "logout")
    private WebElement logout;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialTab;

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void logout(){
        logout.click();
    }

    public void navToNotes(){
        navNotesTab.click();
    }

    public void navToCredential(){
        navCredentialTab.click();
    }

}
