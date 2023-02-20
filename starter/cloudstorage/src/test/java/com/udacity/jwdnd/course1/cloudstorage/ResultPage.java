package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    @FindBy(id="backLink1")
    private WebElement backToHomeBtn1;

    public ResultPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void goBackToHomeFromSuccessMsg(){
        backToHomeBtn1.click();
    }
}
