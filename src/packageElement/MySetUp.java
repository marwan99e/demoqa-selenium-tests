package packageElement;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MySetUp {
	WebDriver driver = new ChromeDriver();
	String theURL = "https://demoqa.com/profile";
	Random rand = new Random();

	@BeforeTest
	public void TestCase() {

		driver.get(theURL);
		driver.manage().window().maximize();

	}

	@Test(priority = 1, enabled = false)
	public void Element() {

		driver.navigate().to("https://demoqa.com/text-box");
		WebElement FullNameInput = driver.findElement(By.xpath("//input[@id='userName']"));
		WebElement EmailInput = driver.findElement(By.xpath("//input[@id='userEmail']"));
		WebElement CurrentAddress = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
		WebElement PermanentAddress = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
		WebElement submitInput = driver.findElement(By.id("submit"));

		FullNameInput.sendKeys("Marwan Alrawashdeh");
		EmailInput.sendKeys("marwan234@gmail.com");
		CurrentAddress.sendKeys("Amman");
		PermanentAddress.sendKeys("Amman");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,700)");

		submitInput.click();

	}

	@Test(priority = 2, enabled = false)
	public void CheckBox() {
		driver.navigate().to("https://demoqa.com/checkbox");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,300)");

		WebElement CheckBoxInput = driver.findElement(By.xpath("//span[@class='rct-checkbox']//*[name()='svg']"));
		CheckBoxInput.click();

	}

	@Test(priority = 3, enabled = false)
	public void RadioButton() {
	    driver.navigate().to("https://demoqa.com/radio-button");
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollTo(0,300)");

	    String[] options = {"Yes", "Impressive"};
	    Random rand = new Random();
	    String selectedOption = options[rand.nextInt(options.length)];

	    WebElement radioButton = driver.findElement(
	            By.xpath("//label[normalize-space()='" + selectedOption + "']"));
	    radioButton.click();

	    WebElement resultText = driver.findElement(By.cssSelector("span.text-success"));
	    String actualText = resultText.getText();

	    Assert.assertEquals(actualText, selectedOption,
	            "Displayed text does not match the selected option!");
	}
	@Test(priority = 4,enabled = false)
	public void EditTableNamesAndEmail() {
	    driver.navigate().to("https://demoqa.com/webtables");
	    

	    WebElement editButton = driver.findElement(By.xpath("//span[@title='Edit'][1]"));
	    editButton.click();

	    String newFirstName = "marwan";
	    String newLastName = "omar";
	    String newEmail = newFirstName.toLowerCase() + "." + newLastName.toLowerCase() + "@gmail.com";

	    WebElement firstNameField = driver.findElement(By.id("firstName"));
	    firstNameField.clear();
	    firstNameField.sendKeys(newFirstName);

	    WebElement lastNameField = driver.findElement(By.id("lastName"));
	    lastNameField.clear();
	    lastNameField.sendKeys(newLastName);

	    WebElement emailField = driver.findElement(By.id("userEmail"));
	    emailField.clear();
	    emailField.sendKeys(newEmail);

	    driver.findElement(By.id("submit")).click();
	}
	@Test(priority = 5,enabled = false)
	public void TestAllClickTypes() {
	    driver.navigate().to("https://demoqa.com/buttons");
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollTo(0,300)");
	    
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	    Actions actions = new Actions(driver);

	    // Double Click
	    WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
	    actions.doubleClick(doubleClickBtn).perform();
	    String doubleClickMsg = driver.findElement(By.id("doubleClickMessage")).getText();
	    Assert.assertEquals(doubleClickMsg, "You have done a double click");
	    System.out.println("Double Click verified");

	    // Right Click
	    WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
	    actions.contextClick(rightClickBtn).perform();
	    String rightClickMsg = driver.findElement(By.id("rightClickMessage")).getText();
	    Assert.assertEquals(rightClickMsg, "You have done a right click");
	    System.out.println("Right Click verified");

	    // Single Click
	    WebElement singleClickBtn = driver.findElement(By.xpath("//button[text()='Click Me']"));
	    singleClickBtn.click();
	    String singleClickMsg = driver.findElement(By.id("dynamicClickMessage")).getText();
	    Assert.assertEquals(singleClickMsg, "You have done a dynamic click");
	    System.out.println("Single Click verified");
	}
	
	 @Test(priority = 6)
	    public void checkHomeLink() {
	        driver.navigate().to("https://demoqa.com/links");
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        String originalWindow = driver.getWindowHandle();
	        Assert.assertEquals(driver.getWindowHandles().size(), 1, "Should start with one window.");

	        WebElement homeLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("simpleLink")));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", homeLink);
	        homeLink.click();

	        wait.until(d -> d.getWindowHandles().size() == 2);
	        Set<String> windows = driver.getWindowHandles();
	        windows.remove(originalWindow);
	        String newWindow = windows.iterator().next();
	        driver.switchTo().window(newWindow);

	        String currentUrl = driver.getCurrentUrl();
	        Assert.assertTrue(currentUrl.contains("demoqa.com"),
	                "New tab should navigate to demoqa home. Current URL: " + currentUrl);

	        driver.close();
	        driver.switchTo().window(originalWindow);
	    }

	        
	    
	    

		




}
