package Tests;

import Base.BaseTest;
import Pages.HomepagePage;
import Pages.BookStorePages.LoginPage;
import Pages.BookStorePages.ProfilePage;
import Pages.Sidebar;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    String validUsername;
    String validPassword;
    String invalidUsername;
    String invalidPassword;
    String expectedURL;
    public HomepagePage homepagePage;
    public Sidebar sidebar;
    public LoginPage loginPage;
    public ProfilePage profilePage;
    @BeforeMethod
    public void pageSetUp() {
        homepagePage = new HomepagePage();
        sidebar = new Sidebar();
        loginPage = new LoginPage();
        profilePage = new ProfilePage();

        driver.manage().window().maximize();
        driver.get(homeURL);
    }

    @Test (priority = 10)
    public void userCanLoginWithValidCredentials() {
        validUsername = excelReader.getStringData("Login", 1,0);
        validPassword = excelReader.getStringData("Login", 1,1);
        expectedURL = homeURL + "profile";

        homepagePage.clickOnCard("Book Store Application");
        sidebar.clickOnSidebarButton("Login");
        loginPage.insertUsername(validUsername);
        loginPage.insertPassword(validPassword);
        loginPage.clickOnLoginButton();

        waitForVisibility(profilePage.logOutButton);
        Assert.assertTrue(elementIsPresent(profilePage.logOutButton));
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        Assert.assertEquals(profilePage.usernameText.getText(), validUsername);
    }

    @Test (priority = 20)
    public void userCannotLoginWithInvalidUsername() {
        invalidUsername = excelReader.getStringData("Login", 1,2);
        validPassword = excelReader.getStringData("Login", 1,1);
        expectedURL = homeURL + "login";
        String errorMessage = "Invalid username or password!";

        driver.manage().deleteAllCookies(); // for Log out
        homepagePage.clickOnCard("Book Store Application");
        sidebar.clickOnSidebarButton("Login");
        loginPage.insertUsername(invalidUsername);
        loginPage.insertPassword(validPassword);
        loginPage.clickOnLoginButton();

        waitForVisibility(loginPage.invalidCredentialsMessage);
        Assert.assertEquals(loginPage.invalidCredentialsMessage.getText(), errorMessage);
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }

    @Test (priority = 30)
    public void userCannotLoginWithInvalidPassword() {
        validUsername = excelReader.getStringData("Login", 1,0);
        invalidPassword = excelReader.getStringData("Login", 1,3);
        expectedURL = homeURL + "login";
        String errorMessage = "Invalid username or password!";

        driver.manage().deleteAllCookies(); // for Log out
        homepagePage.clickOnCard("Book Store Application");
        sidebar.clickOnSidebarButton("Login");
        loginPage.insertUsername(validUsername);
        loginPage.insertPassword(invalidPassword);
        loginPage.clickOnLoginButton();

        waitForVisibility(loginPage.invalidCredentialsMessage);
        Assert.assertEquals(loginPage.invalidCredentialsMessage.getText(), errorMessage);
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }
}
