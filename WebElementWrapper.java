package Helpers;

import org.openqa.selenium.WebElement;

public class WebElementWrapper {
    private WebElement element;

    public WebElementWrapper(WebElement element) {
        this.element = element;
    }

    public String getText() {
        return element.getText().trim();
    }

    public String getValue() {
        return element.getAttribute("value");
    }

}
