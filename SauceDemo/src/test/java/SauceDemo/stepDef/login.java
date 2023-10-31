package SauceDemo.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("user at login page")
    public void userAtLoginPage() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);

        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        Assert.assertEquals(true, loginButton.isDisplayed());
    }

    @When("user input registered (.*) and (.*)$")
    public void userInputRegisteredUsernameAndPassword(String username, String password) throws InterruptedException {
        Thread.sleep(200);
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(username);
        Thread.sleep(200);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
    }

    @And("user click login button")
    public void userClickLoginButton() throws InterruptedException {
        Thread.sleep(200);
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
    }

    @Then("User login is (.*)$")
    public void userLoginIsStatus(String status) throws InterruptedException {
        if(status.equals("success")) {
            WebElement producttext = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
            Assert.assertEquals(true, producttext.isDisplayed());
        } else {
            WebElement errortext = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"));
            Assert.assertEquals(true, errortext.isDisplayed());
        }
        Thread.sleep(200);
        driver.close();
    }
}
