package Pages.BookStorePages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BaseTest {

    public ProfilePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[1]/div[3]/button")
    public WebElement logOutButton;

    @FindBy(id = "userName-value")
    public WebElement usernameText;

    //========= METODE ==========
    public void clickOnLogOutButton() {
        logOutButton.click();
    }
}
