package services;

import static com.codeborne.selenide.Selenide.open;

import logger.ProjectLog;
import org.junit.Assert;

import com.google.inject.Inject;

import auxiliar.UsefullMethods;

public class HomePageServices {

    @Inject
    protected ProjectLog projectLog;

    @Inject
    protected UsefullMethods usefullMethods;

    private static final String URL = "https://best.aliexpress.com";

    public void clickOnElement(String number, String element) {
        projectLog.info("clickOnElement started");
        usefullMethods.getElementByName(number,element).click();
        projectLog.info("clickOnElement finished");
    }

    public void setValueInField(String text, String field) {
        usefullMethods.getElementByName(field).sendKeys(text);
    }

    public void openHomePage() {
        open(URL);
    }

    public void verifyThatElementContainsText(String number, String field, String expectedText) {
        String actualText = usefullMethods.getElementByName(number,field).getText();
        Assert.assertTrue("Mismatch text, expected: " + expectedText + ", but actual: " + actualText,actualText.contains(expectedText));
    }
}
