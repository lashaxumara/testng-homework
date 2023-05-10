import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.reportsFolder;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class CheckboxTests extends ConfigTests {
    ElementsCollection checkboxes;

    @BeforeMethod
    public void specialSetup() {
        baseUrl = "http://the-internet.herokuapp.com/checkboxes";
        checkboxes = $$(By.xpath("//form[@id='checkboxes']//input"));
        reportsFolder = "src/main/CheckboxFailedTests";
    }

    @Test(priority = 1)
    public void uncheckCheckbox() {
        open("");
        SelenideElement lastEl = checkboxes.last();
        lastEl.setSelected(false);
        softAssert.assertTrue(lastEl.isSelected());
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void checkCheckbox() {
        open("");
        SelenideElement firstEl = checkboxes.first();
        firstEl.setSelected(true);
        softAssert.assertFalse(firstEl.isSelected());
        softAssert.assertAll();
    }
}