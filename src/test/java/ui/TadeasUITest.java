package ui;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import tadeas.Application;

import static junit.framework.TestCase.assertEquals;

//@WebMvcTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TadeasUITest {

    private String baseUrl;

    @LocalServerPort
    private int port;

    WebDriver driver;


    @Before
    public void setUp() {
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
        driver.get(baseUrl + "login");
        assertEquals(baseUrl + "login", driver.getCurrentUrl());
        driver.findElement(By.cssSelector("#username")).sendKeys("student");
        driver.findElement(By.cssSelector("#password")).sendKeys("student");
        driver.findElement(By.cssSelector("button")).click();
        assertEquals(baseUrl, driver.getCurrentUrl());
        assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Seznam úloh");
        driver.quit();
    }

    @Test
    public void verifiesLoginTeacher() throws Exception {
        driver.get(baseUrl + "login");
        assertEquals(baseUrl + "login", driver.getCurrentUrl());
        driver.findElement(By.cssSelector("#username")).sendKeys("teacher");
        driver.findElement(By.cssSelector("#password")).sendKeys("teacher");
        driver.findElement(By.cssSelector("button")).click();
        assertEquals(baseUrl, driver.getCurrentUrl());
        assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Seznam úloh");
        driver.quit();
    }
}
