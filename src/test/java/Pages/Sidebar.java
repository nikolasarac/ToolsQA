package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Sidebar extends BaseTest {

    public Sidebar() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".btn.btn-light")
    public List<WebElement> sidebarButtons;

    //========= METODE ==========
    public void clickOnSidebarButton(String buttonName) {
        for (WebElement card : sidebarButtons) {
            if(card.getText().equals(buttonName)) {
                card.click();
                break;
            }
        }
    }
}
