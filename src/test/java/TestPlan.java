import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestPlan {

    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public void before() {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Successfull login with username and password")
    public void loginMizu() throws InterruptedException  {
        driver.get(Utils.BASE_URL);
        WebForm webForm = new WebForm(driver);
        driver.manage().window().maximize();
        Thread.sleep(3000);
        webForm.enterMail();
        Thread.sleep(2000);
        webForm.enterPassword();
        Thread.sleep(2000);
        WebElement signInButtonElement = driver.findElement(By.xpath("//*[@id=\"userLogin\"]/div[6]/button"));
        if (signInButtonElement.isDisplayed() && signInButtonElement.isEnabled()) {
            webForm.clickOnSignInButton();
        }
        else{
            System.out.println("Giriş butonuna tıklanılamadı.");
            assert(false);
        }
        Thread.sleep(2000);
        String actualUrl="https://www.mizu.com/en-mx/";
        String expectedUrl= driver.getCurrentUrl();
        Thread.sleep(2000);
        //Giriş yapılması ve ana sayfa yönlendirilmesi gerekir.
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test(testName = "Forgot my Password")
    public void forgotMyPassowrd() throws InterruptedException  {
        driver.get(Utils.BASE_URL);
        WebForm webForm = new WebForm(driver);
        driver.manage().window().maximize();
        Thread.sleep(3000);
        webForm.clickOnForgotPasswordButton();
        Thread.sleep(3000);
        webForm.enterEmailForResetPass();
        Thread.sleep(3000);
        WebElement sentButtonElement = driver.findElement(By.xpath("/html/body/main/div/div[1]/div/form[2]/div[3]/button"));
        if (sentButtonElement.isDisplayed() && sentButtonElement.isEnabled()) {
            webForm.clickOnSendButton();
        }
        else{
            System.out.println("Sıfırlama butonuna tıklanılamadı.");
            assert(false);
        }
        Thread.sleep(3000);
        WebElement resetSuccess = driver.findElement(By.xpath("/html/body/main/div/div[1]/div/div"));

        if(resetSuccess.isDisplayed())
        {
            System.out.println("Şifre sıfırlama için mail başarıyla gönderildi.");

        }
        else{
            System.out.println("Şifre sıfırlama için mail gönderilemedi.");
            assert(false);
        }
    }

    @Test(testName = "Email and password control test")
    public void emailAndPassControl() throws InterruptedException  {
        driver.get(Utils.BASE_URL);
        WebForm webForm = new WebForm(driver);
        driver.manage().window().maximize();
        Thread.sleep(3000);
        webForm.enterWrongMail();
        Thread.sleep(2000);
        webForm.clickOnLoginTitle();
        WebElement mailErrorElement = driver.findElement(By.xpath("//*[@id=\"EmailLogin-error\"]"));
        if (mailErrorElement.isDisplayed()) {
            System.out.println("Email uyarısı başarılı bir şekilde verildi");
        }
        else
        {
            System.out.println("Email uyarısı verilmedi!");
            assert(false);
        }
        Thread.sleep(2000);
        webForm.enterWrongPassword();
        Thread.sleep(2000);
        webForm.clickOnLoginTitle();
        WebElement passErrorElement = driver.findElement(By.xpath("//*[@id=\"Password-error\"]"));
        if (passErrorElement.isDisplayed()) {
            System.out.println("2 karakter girildiği için şifre uyarısı başarılı bir şekilde verildi");
            webForm.clearPassword();
        }
        else
        {
            System.out.println("Şifre uyarısı verilmedi!");
            assert(false);
        }
        Thread.sleep(2000);
        webForm.enterLongWrongPassword();
        Thread.sleep(2000);
        webForm.clickOnLoginTitle();
        Thread.sleep(2000);
        WebElement passError2Element = driver.findElement(By.xpath("//*[@id=\"userLogin\"]/div[4]/span[2]"));
        if (passError2Element.isDisplayed()) {
            System.out.println("30'dan fazla karakter girildiği için şifre uyarısı başarılı bir şekilde verildi");
        }
        else
        {
            System.out.println("Şifre uyarısı verilmedi!");
            assert(false);
        }
        Thread.sleep(2000);
        webForm.clickOnSignInButton();
        String actualUrl="https://www.mizu.com/en-mx/login";
        String expectedUrl= driver.getCurrentUrl();
        Thread.sleep(2000);
        //Giriş yapılamaması ve hala login sayfasına kalması gerekir.
        Assert.assertEquals(actualUrl, expectedUrl);
    }
    

    @AfterSuite
    public void cleanUp(){
        driver.manage().deleteAllCookies();
    }
}
