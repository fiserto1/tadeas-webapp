package ui;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import tadeas.Application;

import java.util.Locale;

import static junit.framework.TestCase.assertEquals;

//@WebMvcTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TadeasLoginUITest {

    @Autowired
    private MessageSource messageSource;

    private String baseUrl;

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private Locale localeCS;

    @Before
    public void setUp() {
        localeCS = Locale.forLanguageTag("cs");

        DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setJavascriptEnabled(true);
//		caps.setCapability("takesScreenshot", true);

        String osName = System.getProperty("os.name");
        String phantomBinaryPath;
        if (osName.startsWith("Windows")) {
            phantomBinaryPath = "phantomjs.exe";
        } else {
            phantomBinaryPath = "phantomjs";
        }

        caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                phantomBinaryPath
        );
//		driver =  new ChromeDriver(chromeOptions);
        driver = new PhantomJSDriver(caps);
        baseUrl = "http://localhost:" + Integer.toString(port) + "/";
    }

    @Test
    public void verifiesLoginStudent() throws Exception {
        assertLoginSuccess("student", "student");
    }

    @Test
    public void verifiesLoginTeacher() throws Exception {
        assertLoginSuccess("teacher", "teacher");
    }

    @Test
    public void verifiesLoginWrongPassword() throws Exception {
        assertLoginFail("teacher", "student");
    }

    @Test
    public void verifiesLoginWrongUsername() throws Exception {
        assertLoginFail("dean", "student");
    }

    @Test
    public void verifiesLoginEmptyPassword() throws Exception {
        assertLoginFail("teacher", "");
    }

    @Test
    public void verifiesLoginMinLengthPassword() throws Exception {
        assertLoginFail("teacher", "asd");
    }

    @Test
    public void verifiesLoginMinLengthUsername() throws Exception {
        assertLoginFail("abc", "abcd");
    }

    @Test
    public void verifiesLoginMaxLengthPassword() throws Exception {
        String longString = generateLongString(101);
        assertLoginFail("teacher", longString);
    }

    @Test
    public void verifiesLoginMaxLengthUsername() throws Exception {
        String longString = generateLongString(101);
        assertLoginFail(longString, "abcd");
    }

    private String generateLongString(int strLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strLength; i++) {
            sb.append("a");
        }

        return sb.toString();
    }


    private void assertLoginSuccess(String username, String password) {
        driver.get(baseUrl + "login");
        assertEquals(baseUrl + "login", driver.getCurrentUrl());
        driver.findElement(By.cssSelector("#username")).sendKeys(username);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button")).click();
        assertEquals(baseUrl, driver.getCurrentUrl());

        String taskListTitle = messageSource.getMessage("tasklist.title", null, localeCS);
        assertEquals(driver.findElement(By.cssSelector("h2")).getText(), taskListTitle);
        driver.quit();
    }

    private void assertLoginFail(String username, String password) {
        driver.get(baseUrl + "login");
        assertEquals(baseUrl + "login", driver.getCurrentUrl());
        driver.findElement(By.cssSelector("#username")).sendKeys(username);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button")).click();
        assertEquals(baseUrl + "login?error", driver.getCurrentUrl());

        String loginTitle = messageSource.getMessage("login.title", null, localeCS);
        assertEquals(driver.findElement(By.cssSelector("h2")).getText(), loginTitle);

        String loginAlert = messageSource.getMessage("login.badCredentials", null, localeCS);
        assertEquals(driver.findElement(By.className("alert-danger")).getText(), loginAlert);
        driver.quit();
    }
}
