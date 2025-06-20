package Helpers;

import org.openqa.selenium.*;

public class LoginPage {

    WebDriver driver = DriverFactory.getDriver();


    private final String LOGIN_URL ="https://www.saucedemo.com/";
    private final String LOCAL_STORAGE_SCRIPT ="return window.localStorage.getItem('backtrace-guid');";
    private final By USERNAME =By.id("user-name");
    private final By PASSWORD =By.id("password");
    private final By LOGIN_BUTTON =By.xpath("//input[@type='submit']");
    private final By HOME_PAGE_PRODUCT =By.xpath("//span[@class='title' and text()='Products']");
    private final By LOGIN_ERROR =By.xpath("//h3[@data-test='error']");



    public void getSauceUrl(){
        getUrl(LOGIN_URL);
    }


    public void setUsername(String username){
        sendText(USERNAME, username);
    }

    public void setPassword(String password){
        sendText(PASSWORD, password);
    }

    public void clickOnLoginButton(){
        clickOnElement(LOGIN_BUTTON);
    }

    public boolean isProductTitleDisplayed(){
        return isDisplayed(HOME_PAGE_PRODUCT);
    }

    public boolean isLoginFailed(){
        return isDisplayed(LOGIN_ERROR);
    }

    public Object getLocalStorageToken(){
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        Object token = js.executeScript(LOCAL_STORAGE_SCRIPT);
        return token;
    }

    public String getCookieSessionUserName(){
        String CookieSessionUsername=null;
        Cookie sessionCookie = driver.manage().getCookieNamed("session-username");

        if (sessionCookie != null) {
            CookieSessionUsername = sessionCookie.getValue();
            System.out.println("Session username from cookie: " + CookieSessionUsername);
        } else {
            System.out.println("session-username cookie not found.");
        }
        return CookieSessionUsername;
    }

    private void getUrl(String url){
        driver.get(url);
    }


    private void clickOnElement(By by){
        driver.findElement(by).click();
    }

    private void sendText(By by, String text){
        driver.findElement(by).sendKeys(text);
    }


    public String overriddenGetText(){
        WebElement headerWebElement = driver.findElement(By.xpath("//div[@class='app_logo']"));
        WebElementWrapper webElementWrap = new WebElementWrapper(headerWebElement);
        String text = webElementWrap.getText();
        return text;
    }


    private boolean isDisplayed(By by){
        return driver.findElement(by).isDisplayed();
    }

    public void quitBrowser(){
        driver.close();
    }
}
