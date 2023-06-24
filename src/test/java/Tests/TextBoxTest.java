package Tests;

import Base.BaseTest;
import Pages.ElementsPages.TextBoxPage;
import Pages.HomepagePage;
import Pages.Sidebar;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TextBoxTest extends BaseTest {
    public HomepagePage homepagePage;
    public Sidebar sidebar;
    public TextBoxPage textBoxPage;

    @BeforeMethod
    public void pageSetUp() {
        homepagePage = new HomepagePage();
        sidebar = new Sidebar();
        textBoxPage = new TextBoxPage();
        driver.manage().window().maximize();
        driver.get(homeURL);
    }

    @Test
    public void userCanSubmitDataWithForm() {
        homepagePage.clickOnCard("Elements");
        sidebar.clickOnSidebarButton("Text Box");

        String fullName = excelReader.getStringData("TextBox", 1, 0);
        String email = excelReader.getStringData("TextBox", 1, 1);
        String currentAddress = excelReader.getStringData("TextBox", 1, 2);
        String permanentAddress = excelReader.getStringData("TextBox", 1, 3);

        textBoxPage.insertFullName(fullName);
        textBoxPage.insertEmail(email);
        textBoxPage.insertCurrentAddress(currentAddress);
        textBoxPage.insertPermanentAddress(permanentAddress);

        textBoxPage.clickOnSubmitButton();

        Assert.assertTrue(textBoxPage.nameOutput.getText().contains(fullName));
        Assert.assertTrue(textBoxPage.emailOutput.getText().contains(email));
        Assert.assertTrue(textBoxPage.currentAddressOutput.getText().contains(currentAddress));
        Assert.assertTrue(textBoxPage.permanentAddressOutput.getText().contains(permanentAddress));
    }
}
