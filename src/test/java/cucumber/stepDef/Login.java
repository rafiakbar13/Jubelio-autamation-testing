package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.concurrent.TimeUnit;

public class Login {
    WebDriver driver;
    String baseUrl = "https://app.jubelio.com/login";

    public Login() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();

        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Given("the user is on the login page")
    public void userLoginOnLoginPage() {
        this.driver.get(baseUrl);
    }

    @When("the user enters valid email")
    public void theUserEntersTheValidEmailAsEmail() {
        this.driver.findElement(By.name("email")).sendKeys("qa.rakamin.jubelio@gmail.com");
    }

    @And("the user enters valid password")
    public void theUserEntersTheValidPasswordAsPassword() {
        this.driver.findElement(By.name("password")).sendKeys("Jubelio123!");
    }

    @And("the user clicks the Login button")
    public void theUserClicksTheLoginButton() {
        this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/div/form/button")).click();
    }

    @Then("user redirected to dashboard page")
    public void userRedirectedToDashboardPage() {
        //Variable Aseertion
       String pageTitle = this.driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/div/div[1]/h1")).getText();
       String pageUrl = this.driver.getCurrentUrl();

       //Assert
        Assert.assertEquals(pageTitle,"Dashboard");
        Assert.assertEquals(pageUrl,"https://app.jubelio.com/home");
        this.driver.quit();
    }

    @And("the user enters invalid password")
    public void theUserEntersInvalidPassword() {
        this.driver.findElement(By.name("password")).sendKeys("12345678!");
    }

    @Then("the system displays an error message")
    public void theSystemDisplaysAnErrorMessage() {
        String errorMessage = this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/li")).getText();
        Assert.assertEquals(errorMessage,"Password atau email anda salah.");
        this.driver.quit();

    }
}
