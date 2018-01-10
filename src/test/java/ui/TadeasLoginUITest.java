package ui;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
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
        String errMsg = messageSource.getMessage("login.emptyPassword", null, localeCS);
        String errMsg2 = messageSource.getMessage("login.tooShortPassword", null, localeCS);
        assertLoginFail("teacher", "", errMsg, errMsg2);
    }

    @Test
    public void verifiesLoginMinLengthPassword() throws Exception {
        String errMsg = messageSource.getMessage("login.tooShortPassword", null, localeCS);
        assertLoginFail("teacher", "asd", errMsg);
    }

    @Test
    public void verifiesLoginMinLengthUsername() throws Exception {
        String errMsg = messageSource.getMessage("login.tooShortLogin", null, localeCS);
        assertLoginFail("abc", "abcd", errMsg);
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

        assertNoFEValidationError();
        driver.findElement(By.cssSelector("button")).click();
        assertEquals(baseUrl, driver.getCurrentUrl());

        String taskListTitle = messageSource.getMessage("tasklist.title", null, localeCS);
        assertEquals(driver.findElement(By.cssSelector("h2")).getText(), taskListTitle);
        driver.quit();
    }

    private void assertLoginFail(String username, String password, String... expectedErrorMsgs) {
        driver.get(baseUrl + "login");
        assertEquals(baseUrl + "login", driver.getCurrentUrl());

        final String usernameCssSelector = "#username";
        WebElement usernameInput = driver.findElement(By.cssSelector(usernameCssSelector));
        usernameInput.sendKeys(username);
        blur(usernameCssSelector);

        final String passCssSelector = "#password";
        WebElement passwordInput = driver.findElement(By.cssSelector(passCssSelector));
        passwordInput.sendKeys(password);
        blur(passCssSelector);

        assertFEValidationErrorIsShown(expectedErrorMsgs);

        //SUBMIT FORM
        driver.findElement(By.cssSelector("button")).click();

        String loginTitle = messageSource.getMessage("login.title", null, localeCS);
        assertEquals(driver.findElement(By.cssSelector("h2")).getText(), loginTitle);

        if (expectedErrorMsgs.length == 0) {
            //Form was submitted and login failed
            assertEquals(baseUrl + "login?error", driver.getCurrentUrl());

            String loginAlert = messageSource.getMessage("login.badCredentials", null, localeCS);
            assertEquals(driver.findElement(By.className("alert-danger")).getText(), loginAlert);
        } else {
            //Form was not submitted because of validation error
            assertEquals(baseUrl + "login", driver.getCurrentUrl());
        }

        driver.quit();
    }

    private void assertFEValidationErrorIsShown(String... expectedErrorMessages) {
        if (expectedErrorMessages.length == 0) {
            return;
        }

        List<WebElement> errorBlocks = driver.findElements(By.xpath("//div[@class='help-block with-errors']"));

        List<String> shownErrorMsgs = new ArrayList<>();
        for (WebElement errorBlock : errorBlocks) {
            List<WebElement> temp = errorBlock.findElements(By.tagName("li"));
            temp.stream().map(WebElement::getText).forEach(shownErrorMsgs::add);
        }

        assertFalse("No errors found, expected: " + expectedErrorMessages.length, shownErrorMsgs.isEmpty());
        assertTrue(shownErrorMsgs.containsAll(Arrays.asList(expectedErrorMessages)));
    }

    private void assertNoFEValidationError() {
        List<WebElement> errorBlocks = driver.findElements(By.xpath("//div[@class='help-block with-errors']"));
        for (WebElement errorBlock : errorBlocks) {
            List<WebElement> li = errorBlock.findElements(By.tagName("li"));
            assertTrue("Some error found by validations, expected: none.", li.isEmpty());
        }
    }

    private void blur(String cssSelector) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String blurInJs = String.format("var x = $(\'%s\');x.blur();", cssSelector);
        js.executeScript(blurInJs);
    }
}
