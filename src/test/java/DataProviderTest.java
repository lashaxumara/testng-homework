import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.*;

public class DataProviderTest {
    SoftAssert softAssert;

    @BeforeMethod
    public void specialStup() {
        softAssert = new SoftAssert();
    }

    @DataProvider(name = "testData")
    public Object[][] testData() {
        return new Object[][]{
                {"Lasha", "Khumarashvili", "Male", "555888888"},
                {"Mariam", "Mirianashvili", "Female", "123456789"},
                {"Giorgi", "Giorganashvili", "Other", "987654321"}
        };
    }

    @Test(dataProvider = "testData")
    public void parametres(String firstName, String lastName, String gender, String mobileNumber) {
        open("https://demoqa.com/automation-practice-form");

        SelenideElement studentName = $(byId("firstName"));
        SelenideElement studentSurname = $(byId("lastName"));
        SelenideElement genderEl = $(byValue(gender));
        SelenideElement mobileEl = $(byId("userNumber"));

        studentName.setValue(firstName);
        studentSurname.setValue(lastName);
        actions().moveToElement(genderEl).click().perform();
        mobileEl.setValue(mobileNumber);

        String fullName = studentName.getValue() + studentSurname.getValue();
        softAssert.assertEquals(fullName, firstName + lastName);
//        softAssert.assertEquals(studentName.getValue(), firstName);
//        softAssert.assertEquals(studentSurname.getValue(), lastName);
//        softAssert.assertTrue(genderEl.isSelected());
//        softAssert.assertEquals(mobileEl.getValue(), mobileNumber);

        softAssert.assertAll();
    }
}