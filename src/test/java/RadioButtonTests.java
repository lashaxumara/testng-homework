import com.codeborne.selenide.SelenideElement;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.reportsFolder;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class RadioButtonTests extends ConfigTests implements IRetryAnalyzer {
    int retryNumber = 0;
    int maxRetrys = 5;


    @Override
    public boolean retry(ITestResult iTestResult) {
        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            if (retryNumber < maxRetrys) {
                retryNumber++;
                return true;
            }
        }
        return false;
    }

    @BeforeMethod
    public void specialSetup() {
        reportsFolder = "src/main/RadioButtonFailedTests";
    }

    @Test(priority = 2, groups = "FrondEnd", retryAnalyzer = RadioButtonTests.class)
    public void selectYes() {
        open("https://demoqa.com/radio-button");
        SelenideElement yesButton = $(byId("yesRadio"));
        actions().moveToElement(yesButton).click().perform();

        String actualText = $(byClassName("text-success")).getText();
        String expectedText = "Impressive";

        softAssert.assertEquals(actualText, expectedText);
        softAssert.assertAll();
    }

    @Test(priority = 1, groups = "BackEnd")
    public void checkNo() {
        open("https://demoqa.com/radio-button");
        SelenideElement noButton = $(byId("noRadio"));
        softAssert.assertTrue(noButton.isEnabled());
        softAssert.assertAll();
    }


}