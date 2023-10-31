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

public class Checkout {

    WebDriver driver;

    String baseUrl = "https://www.saucedemo.com/";

    @Given("user login using registered (.*) and (.*)$")
    public void userLoginUsingRegisteredUsernameAndPassword(String username, String password) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);

        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(username);
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
    }

    @When("user already login")
    public void userAlreadyLogin() {
        WebElement producttext = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
        Assert.assertEquals(true, producttext.isDisplayed());
    }

    @And("user add item they want")
    public void userAddItemTheyWant() throws InterruptedException{
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();

        WebElement remove = driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
        Assert.assertEquals(true, remove.isDisplayed());

        WebElement count = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
        Assert.assertEquals(true, count.isDisplayed());
    }

    @And("user click cart button")
    public void userClickCartButton() throws InterruptedException{
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
    }

    @And("user see their item")
    public void userSeeTheirItem() {
        driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]"));
    }

    @And("user click continue")
    public void userClickContinue() {
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
    }

    @Then("user at your information page")
    public void userAtYourInformationPage() throws InterruptedException{
        WebElement checkinfo = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
        Assert.assertEquals(true, checkinfo.isDisplayed());
        Thread.sleep(300);
        driver.close();
    }
}
