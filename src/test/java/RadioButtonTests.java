import com.codeborne.selenide.Screenshots;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.reportsFolder;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class RadioButtonTests extends ConfigTests {

    @BeforeMethod
    public void specialSetup() {

        reportsFolder = "src/main/RadioButtonFailedTests";
        baseUrl = "https://demoqa.com/radio-button";
    }

    @Test(priority = 1)
    public void selectYes() {
        open("");
        actions().moveToElement($(byId("yesRadio"))).click().perform();

        String actualText = $(byClassName("text-success")).getText();
        String expectedText = "Impressive";

        softAssert.assertEquals(actualText, expectedText);
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void checkNo() {
        open("");
        Boolean noButton = $(byId("noRadio")).isEnabled();
        softAssert.assertTrue(noButton);
        softAssert.assertAll();
    }
}