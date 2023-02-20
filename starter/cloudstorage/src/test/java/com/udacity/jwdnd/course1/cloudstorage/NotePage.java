package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
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

    @FindBy(xpath = "//*[@id=\"userTable\"]/tbody/tr[1]/th")
    private WebElement tableNoteTitle;

    @FindBy(xpath = "//*[@id=\"userTable\"]/tbody/tr[1]/td[2]")
    private WebElement tableNoteDescription;
    public NotePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void openModal(){
        openNoteModal.click();
    }

    public void createNote(Note note){
        noteTitle.sendKeys(note.getNoteTitle());
        noteDescription.sendKeys(note.getNoteDescription());
        noteSubmit.submit();
    }

    public String getNoteTitleDisplay(){
        return tableNoteTitle.getText();
    }

    public String getNoteDescriptionDisplay(){
        return tableNoteDescription.getText();
    }
}
