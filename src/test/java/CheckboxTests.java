import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.reportsFolder;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class CheckboxTests extends ConfigTests {

    @BeforeMethod
    public void specialSetup() {
        reportsFolder = "src/main/CheckboxFailedTests";
    }

    @Test(priority = 1, groups = "FrontEnd")
    public void uncheckCheckbox() {
        open("http://the-internet.herokuapp.com/checkboxes");
        ElementsCollection checkboxes = $$(By.xpath("//form[@id='checkboxes']//input"));

        checkboxes.stream().forEach(el -> {
            if (el.isSelected()) {
                el.setSelected(false);
                softAssert.assertTrue(el.isSelected());
            }
        });
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = "BackEnd")
    public void checkCheckbox() {
        open("http://the-internet.herokuapp.com/checkboxes");
        ElementsCollection checkboxes = $$(By.xpath("//form[@id='checkboxes']//input"));
        checkboxes.stream().forEach(el -> {
            if (!el.isSelected()) {
                el.setSelected(true);
                softAssert.assertFalse(el.isSelected());
            }
        });
        softAssert.assertAll();
    }
}
