import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizationFactoryTest {

    private String firstName;
    private String lastName;
    private String gender;
    private String mobileNumber;
    SoftAssert softAssert;

    public ParametrizationFactoryTest(String firstName, String lastName, String gender, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.softAssert = new SoftAssert();
    }

    @Test
    public void parametres() {
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

    @Factory
    public static Object[] factoryMethod() {
        return new Object[]{
                new ParametrizationFactoryTest("Lasha", "Khumara", "Male", "12345567"),
                new ParametrizationFactoryTest("Mariam", "Mirianashvili", "Female", "13211565"),
                new ParametrizationFactoryTest("Giorga", "Giorganashvili", "Other", "239789547")
        };
    }
}







