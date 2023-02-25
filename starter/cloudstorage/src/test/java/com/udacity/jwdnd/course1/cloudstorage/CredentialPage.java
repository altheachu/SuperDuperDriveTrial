package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.Driver;

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

    @FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr[1]/th")
    private WebElement urlHomePage;

    @FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr[1]/td[2]")
    private WebElement usernameHomePage;

    @FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr[1]/td[3]")
    private WebElement passwordHomePage;

    @FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr[1]/td[1]/button")
    private WebElement editButton;

    @FindBy(id = "credentialClose")
    private WebElement closeButton;

    @FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr[1]/td[1]/a")
    private WebElement deleteButton;

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

    public Credential getCredentialAtHomePage(){
        Credential credential = new Credential();
        credential.setUrl(urlHomePage.getText());
        credential.setUsername(usernameHomePage.getText());
        credential.setPassword(passwordHomePage.getText());
        return credential;
    }

    public void startEdit(){
        editButton.click();
    }

    public void clearPreviousInput(){
        urlInput.clear();
        usernameInput.clear();
        passwordInput.clear();
    }

    public Credential getExistingInfoInModal(){
        Credential credential = new Credential();
        credential.setUrl(urlInput.getAttribute("value"));
        credential.setUsername(usernameInput.getAttribute("value"));
        credential.setPassword(passwordInput.getAttribute("value"));
        return credential;
    }

    public void cancelEdit(){
        closeButton.click();
    }

    public void deleteCredential(){deleteButton.click();}

}
