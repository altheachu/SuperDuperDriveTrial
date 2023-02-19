package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotePage {

    @FindBy(id="openNoteModal")
    private WebElement openNoteModal;

    @FindBy(id="note-title")
    private WebElement noteTitle;

    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id="noteSubmit")
    private WebElement noteSubmit;

    public NotePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void openModal(){
        openNoteModal.click();
    }

    public void createNote(){
        noteTitle.sendKeys("testNoteTitle");
        noteDescription.sendKeys("testNoteDescription");
        noteSubmit.submit();
    }

}
