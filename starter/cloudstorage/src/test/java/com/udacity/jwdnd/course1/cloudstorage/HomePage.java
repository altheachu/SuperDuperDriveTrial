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

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void logout(){
        logout.click();
    }

    public void navToNotes(){
        navNotesTab.click();
    }

}
