package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialPage {

    @FindBy(id="openCredentialModal")
    private WebElement openModalButton;

    @FindBy(id="credential-url")
    private WebElement urlInput;

    @FindBy(id="credential-username")
    private WebElement usernameInput;

    @FindBy(id="credential-password")
    private WebElement passwordInput;

    @FindBy(id="credentialSave")
    private WebElement save;

    public CredentialPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void openModal(){
        openModalButton.click();
    }

    public void createOrUpdate(Credential credential){
        urlInput.sendKeys(credential.getUrl());
        usernameInput.sendKeys(credential.getUsername());
        passwordInput.sendKeys(credential.getPassword());
        save.click();
    }
}
