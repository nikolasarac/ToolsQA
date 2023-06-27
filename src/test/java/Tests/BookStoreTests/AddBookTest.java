package Tests.BookStoreTests;

import Base.BaseTest;
import Pages.BookStorePages.BooksPage;
import Pages.BookStorePages.LoginPage;
import Pages.BookStorePages.ProfilePage;
import Pages.BookStorePages.SelectedBookPage;
import Pages.HomepagePage;
import Pages.Sidebar;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddBookTest extends BaseTest {

    public HomepagePage homepagePage;
    public Sidebar sidebar;
    public BooksPage booksPage;
    public LoginPage loginPage;
    public SelectedBookPage selectedBookPage;
    public ProfilePage profilePage;

    private void login() {
        String expectedURL = homeURL + "profile";
        String validUsername = excelReader.getStringData("Login", 1, 0);
        String validPassword = excelReader.getStringData("Login", 1, 1);

        homepagePage.clickOnCard("Book Store Application");
        sidebar.clickOnSidebarButton("Login");
        loginPage.insertUsername(validUsername);
        loginPage.insertPassword(validPassword);
        loginPage.clickOnLoginButton();
        waitForURL(expectedURL);
    }

    @BeforeMethod
    public void pageSetUp() {
        homepagePage = new HomepagePage();
        sidebar = new Sidebar();
        booksPage = new BooksPage();
        loginPage = new LoginPage();
        selectedBookPage = new SelectedBookPage();
        profilePage = new ProfilePage();

        driver.manage().window().maximize();
        driver.get(homeURL);
    }

    @Test(priority = 10)
    public void userCanAddBookToCollection() throws InterruptedException {
        login();
        sidebar.clickOnSidebarButton("Book Store");

        waitForClickability(booksPage.bookGit);
        booksPage.bookGit.click();
        selectedBookPage.clickOnAddToCollectionButton();
        closeAlert();
        sidebar.clickOnSidebarButton("Profile");

        waitForVisibility(profilePage.bookRow);
        Assert.assertEquals(profilePage.bookGitInCollection.getText(), booksPage.bookGit.getText());
    }

    @Test(priority = 20)
    public void userCanAddTwoBooksToCollection() throws InterruptedException {
        login();
        sidebar.clickOnSidebarButton("Book Store");

        waitForClickability(booksPage.bookGit);
        booksPage.bookGit.click();
        selectedBookPage.clickOnAddToCollectionButton();
        closeAlert();
        selectedBookPage.backToBookStoreButton();

        waitForClickability(booksPage.bookLearningJS);
        booksPage.bookLearningJS.click();
        selectedBookPage.clickOnAddToCollectionButton();
        closeAlert();

        sidebar.clickOnSidebarButton("Profile");

        waitForVisibility(profilePage.bookRow);

        Assert.assertEquals(profilePage.bookGitInCollection.getText(), booksPage.bookGit.getText());
        Assert.assertEquals(profilePage.bookLearningJSInCollection.getText(), booksPage.bookLearningJS.getText());
    }
}
