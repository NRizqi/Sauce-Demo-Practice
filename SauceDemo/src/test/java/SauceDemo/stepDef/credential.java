package SauceDemo.stepDef;

import io.cucumber.java.Status;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class credential {

    WebDriver driver;

    String baseUrl = "https://www.saucedemo.com/";

    @Given("user login with registered username and password")
    public void userLoginWithRegisteredUsernameAndPassword() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);

        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
    }

    @When("user finish login")
    public void userFinishLogin() {
        WebElement producttext = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
        Assert.assertEquals(true, producttext.isDisplayed());
    }

    @And("user add item")
    public void userAddItem() throws InterruptedException {
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();

        WebElement remove = driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
        Assert.assertEquals(true, remove.isDisplayed());

        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bike-light\"]")).click();

        WebElement remove2 = driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-bike-light\"]"));
        Assert.assertEquals(true, remove2.isDisplayed());

        WebElement count = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        Assert.assertEquals(true, count.isDisplayed());
    }

    @And("user click cart icon")
    public void userClickCartIcon() throws InterruptedException {
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
    }

    @And("user click checkout")
    public void userClickCheckout() {
        WebElement cartList = driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]"));
        Assert.assertEquals(true, cartList.isDisplayed());

        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
    }

    @And("user at information fill page")
    public void userAtInformationFillPage() {
        WebElement information = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
        Assert.assertEquals(true, information.isDisplayed());
    }

    @And("user fill (.*), (.*), and (.*)$")
    public void userFillFirstNameLastNameAndPostalCode(String firstName, String lastName, String postalCode) throws InterruptedException {
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys(firstName);
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys(lastName);
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).sendKeys(postalCode);

    }

    @And("user continue")
    public void userContinue() throws InterruptedException {
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
    }

    @Then("user credential fill is (.*)$")
    public void userCredentialFillIsStatus(String status) throws InterruptedException {
        if(status.equals("success")) {
            WebElement overview = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
            Assert.assertEquals(true, overview.isDisplayed());

            driver.findElement(By.xpath("//*[@id=\"finish\"]")).click();

            WebElement complete = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2"));
            Assert.assertEquals(true, complete.isDisplayed());
            Thread.sleep(300);
            driver.close();
        } else {
            WebElement error= driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]"));
            Assert.assertEquals(true, error.isDisplayed());
            driver.close();
        }
    }
}
