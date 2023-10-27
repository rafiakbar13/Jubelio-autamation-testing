package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Stock {
    WebDriver driver;
    String baseUrl = "https://app.jubelio.com/login";
    public Stock(){
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();

        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    }

    @Given("the user is logged in and redirected to the dashboard page")
    public void userLogin() {
        this.driver.get(baseUrl);
        this.driver.findElement(By.name("email")).sendKeys("qa.rakamin.jubelio@gmail.com");
        this.driver.findElement(By.name("password")).sendKeys("Jubelio123!");
        this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/div/form/button")).click();
        String pageTitle = this.driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/div/div[1]/h1")).getText();
        String pageUrl = this.driver.getCurrentUrl();

        //Assert
        Assert.assertEquals(pageTitle,"Dashboard");
        Assert.assertEquals(pageUrl,"https://app.jubelio.com/home");
    }
    @When("the user clicks Barang menu in the sidebar")
    public void theUserClicksBarangMenuInTheSidebar() {
        this.driver.findElement(By.xpath("//*[@id=\"wrapper\"]/nav/div/div/ul/li[2]/a")).click();
    }

    @And("the user click Persediaan menu")
    public void theUserClicksThePersediaanMenuInTheSidebar() {
        this.driver.findElement(By.xpath("//*[@id=\"wrapper\"]/nav/div/div/ul/li[2]/ul/li[2]/a")).click();
    }

    @And("the user selects a product")
    public void theUserSelectsAProduct() {
        this.driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[3]/div/div/div[2]/div/div/div[2]/div[1]/div/div[6]/div/div/span/div/label")).click();
    }

    @And("then the user clicks the Penyesuaian Persediaan button on the top right side")
    public void thenTheUserClicksThePenyesuaianPersediaanButtonOnTheTopRightSide() {
        this.driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div/div/div/div[2]/div/div/div/div/div/div[1]/div[2]/div/button[2]")).click();
    }


    @And("user double click on the input field")
    public void userDoubleClickOnTheInputField() {
        Actions actions = new Actions(driver);
        WebElement container = this.driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div[1]/div/div[2]/div"));
        actions.moveToElement(container).doubleClick(container).build().perform();
        WebElement inputElement = this.driver.findElement(By.xpath("//*[@id=\"page-top\"]/div[6]/div/input"));

        inputElement.sendKeys("1");
        inputElement.sendKeys(Keys.RETURN);
    }

    @And("the user click simpan button")
    public void theUserClickSimpanButton() {
       WebElement saveButton =  this.driver.findElement(By.cssSelector("#page-wrapper > div.wrapper.wrapper-content > div > div > div > div:nth-child(2) > div > div > div > div > div:nth-child(6) > div > button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
        saveButton.click();
    }

    @Then("the user successfully adds stock")
    public void theUserSuccessfullyAddsStock() {
       String successAddedStock =  this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/li")).getText();
        Assert.assertEquals(successAddedStock,"Data berhasil disimpan.");
    }


}
