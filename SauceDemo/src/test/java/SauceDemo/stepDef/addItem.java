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

public class addItem {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("user login using valid (.*) and (.*)$")
    public void userLoginUsingValidUsernameAndPassword(String username, String password) throws InterruptedException{
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

    @When("user already at homepage")
    public void userAlreadyAtHomepage() {
        WebElement producttext = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
        Assert.assertEquals(true, producttext.isDisplayed());
    }

    @And("user click add to cart button")
    public void userClickAddToCartButton() throws InterruptedException{
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
    }

    @And("add button change to remove button")
    public void addButtonChangeToRemoveButton() {
        WebElement remove = driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
        Assert.assertEquals(true, remove.isDisplayed());
    }

    @And("count number show on cart button")
    public void countNumberShowOnCartButton() {
        WebElement count = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
        Assert.assertEquals(true, count.isDisplayed());
    }

    @Then("add item is success")
    public void addItemIsSuccess() throws InterruptedException{
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        WebElement itemAdd = driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]"));
        Assert.assertEquals(true, itemAdd.isDisplayed());
        driver.close();
    }

    @And("user click any item name")
    public void userClickAnyItemName() throws InterruptedException{
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).click();
    }

    @And("user directed to item page")
    public void userDirectedToItemPage() {
        WebElement itemPage = driver.findElement(By.xpath("//*[@id=\"back-to-products\"]"));
        Assert.assertEquals(true, itemPage.isDisplayed());
    }
}
