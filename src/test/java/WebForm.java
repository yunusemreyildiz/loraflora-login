import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebForm extends PageObject {

    @FindBy(id = "EmailLogin")
    private WebElement email;

    @FindBy(id = "Password")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"userLogin\"]/div[6]/button")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id=\"userLogin\"]/div[6]/a")
    private WebElement forgotPasswordButton;

    @FindBy(id = "Mail")
    private WebElement emailForResetPass;

    @FindBy(xpath = "/html/body/main/div/div[1]/div/form[2]/div[3]/button")
    private WebElement sendButton;

    @FindBy(xpath = "/html/body/main/div/div[1]/div/div")
    private WebElement passResetEmailSent;

    @FindBy(xpath = "/html/body/main/div/div[2]/div/a[1]")
    private WebElement facebookButton;

    @FindBy(xpath = "//*[@id=\"EmailLogin-error\"]")
    private WebElement emailError;

    @FindBy(xpath = "//*[@id=\"Password-error\"]")
    private WebElement passwordError;

    @FindBy(xpath = "/html/body/main/div/div[1]/div/h2")
    private WebElement loginTitle;



    public WebForm(WebDriver driver) {
        super(driver);
    }

    public void enterMail(){
        this.email.click();
        this.email.sendKeys(Utils.MAIL);
    }

    public void clearMail(){
        this.email.clear();
    }

    public void clearPassword(){
        this.password.clear();
    }

    public void enterWrongMail(){
        this.email.click();
        this.email.sendKeys(Utils.WRONGMAIL);

    }

    public void enterLongWrongPassword(){
        this.password.click();
        this.password.sendKeys(Utils.WRONGLONGPASS);
    }

    public void enterPassword(){
        this.password.click();
        this.password.sendKeys(Utils.PASSWORD);
    }

    public void enterWrongPassword(){
        this.password.click();
        this.password.sendKeys(Utils.WRONGPASSWORD);
    }

    public void clickOnSignInButton(){
        this.signInButton.click();
    }

    public void clickOnForgotPasswordButton(){
        this.forgotPasswordButton.click();
    }

    public void clickOnLoginTitle(){
        this.loginTitle.click();
    }

    public void enterEmailForResetPass(){
        this.emailForResetPass.click();
        this.emailForResetPass.sendKeys(Utils.MAIL);
    }

    public void clickOnSendButton(){
        this.sendButton.click();
    }


}