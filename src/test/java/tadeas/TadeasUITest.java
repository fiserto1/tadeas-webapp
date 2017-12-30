package tadeas;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebMvcTest
@AutoConfigureMockMvc
public class TadeasUITest {

	private String baseUrl;

	@LocalServerPort
	private int port;

	WebDriver driver;


	@Before
	public void setUp() {
		driver =  new ChromeDriver();
		baseUrl = "http://localhost:"+Integer.toString(port)+"/";
	}

	@Test
	public void verifiesLoginStudent() throws Exception {
		driver.get(baseUrl+"login");
		assertEquals(baseUrl+"login", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("#username")).sendKeys("student");
		driver.findElement(By.cssSelector("#password")).sendKeys("student");
		driver.findElement(By.cssSelector("button")).click();
		assertEquals(baseUrl, driver.getCurrentUrl());
		assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Seznam úloh");
		driver.quit();
	}

	@Test
	public void verifiesLoginTeacher() throws Exception {
		driver.get(baseUrl+"login");
		assertEquals(baseUrl+"login", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("#username")).sendKeys("teacher");
		driver.findElement(By.cssSelector("#password")).sendKeys("teacher");
		driver.findElement(By.cssSelector("button")).click();
		assertEquals(baseUrl, driver.getCurrentUrl());
		assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Seznam úloh");
		driver.quit();
	}
}
