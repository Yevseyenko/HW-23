package epam.businessObjects;

import epam.Pob.GmailEmailPage;
import epam.Pob.GmailLoginPage;
import epam.Pob.GmailPasswordPage;
import epam.driverConfig.DriverConfiguration;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class EmailBusinessObject {
    public GmailLoginPage gmailLoginPage;
    public GmailPasswordPage gmailPasswordPage;
    public GmailEmailPage gmailEmailPage;
    public WebDriver driver;

    public EmailBusinessObject() {
        DriverConfiguration config = new DriverConfiguration();
        this.driver = config.configureDriver();
        this.gmailEmailPage = new GmailEmailPage(driver);
        this.gmailLoginPage = new GmailLoginPage(driver);
        this.gmailPasswordPage = new GmailPasswordPage(driver);
    }

    public void authorisationEmail(String login, String password) {
        gmailLoginPage.clickingingEmail();
        gmailLoginPage.enteringLogin(login);
        gmailLoginPage.clickingLoginNextBtn();
        gmailPasswordPage.enterPassword(password, driver);
        gmailPasswordPage.clickingPasswordNextBtn();
    }
    public void writingLetterAndSubmit(String reciver, String subject, String text) {
        gmailEmailPage.clickingMailBtn();
        gmailEmailPage.clickingWriteBtn();
        gmailEmailPage.enteringEmail(reciver, subject, text);
        gmailEmailPage.clickingEmailSendBtn();
    }

    public void selectingSentLetter(String subject, String text) {
        gmailEmailPage.gettingSent();
        gmailEmailPage.enteringFirstLetter(driver);
        gmailEmailPage.verifyingSentLetter(subject, text);
        Assert.assertEquals(subject, gmailEmailPage.gettingSubject());
        }
        public void deletingSentLetter(){
            gmailEmailPage.deletingDeliveredMessage(driver);
            driver.close();
            driver.quit();
        }
        public String getSubject(){
      return  gmailEmailPage.gettingSubject();
        }
        public String getTextEmail(){
        return gmailEmailPage.gettingText();
        }
}
