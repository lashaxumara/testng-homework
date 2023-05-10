import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import com.codeborne.selenide.testng.ScreenShooter;
import static com.codeborne.selenide.Configuration.savePageSource;
import static com.codeborne.selenide.Configuration.screenshots;
import static com.codeborne.selenide.Selenide.closeWebDriver;
@Listeners({ ScreenShooter.class})
public class ConfigTests {
    SoftAssert softAssert;

    @BeforeClass
    public void configSetup() {
        Configuration.browserSize = "1366x768";
        Configuration.timeout = 10000;
        softAssert = new SoftAssert();
        screenshots = true;
        savePageSource = false;
    }

    @AfterMethod
    public void closeDriver() {
        closeWebDriver();
    }
}
