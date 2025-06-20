package Steps;

import Helpers.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class BDDSauceDemo {
    LoginPage loginPage = new LoginPage();

    @Given("user launches sauce demo url")
    public void launchUrl() {
        loginPage.getSauceUrl();
    }

    @Given("user closes existing browser")
    public void closeBrowsers(){
        loginPage.quitBrowser();
    }

    @When("user provides the credentials:")
    public void provideCredentials(DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps()) {
            String username = row.get("username");
            String password = row.get("password");
            if ("[empty]".equalsIgnoreCase(username)) {
                username = "";
            }
            if ("[empty]".equalsIgnoreCase(password)) {
                password = "";
            }
            loginPage.setUsername(username);
            loginPage.setPassword(password);
        }
    }

    @When("user clicks on login button")
    public void clickOnLoginButton() {
        loginPage.clickOnLoginButton();
    }

    @Then("verify that user is successfully logged in to home page")
    public void verifySuccessfulLogin() {
        boolean isProductTitleDisplayed = loginPage.isProductTitleDisplayed();
        Assert.assertTrue("Home page not displayed", isProductTitleDisplayed);
    }

    @Then("verify that user login is failed")
    public void verifyLoinFailure() {
        boolean isLoginFailed = loginPage.isLoginFailed();
        Assert.assertTrue("Login not failed", isLoginFailed);
    }

    @Then("local storage should contain a valid session token")
    public void verifyLocalStorageToken() {
        Object token = loginPage.getLocalStorageToken();

        Assert.assertNotNull("Session token should not be null", token);
        System.out.println("Session token: " + token);
    }

    @Then("the cookie-session-username in local storage should match the logged in user")
    public void verifyCookieSessionUsername(DataTable dataTable) {
        String expectedSessionUsername= dataTable.asMaps().get(0).get("username");
        String actualCookieSessionUsername=loginPage.getCookieSessionUserName();
        Assert.assertEquals(expectedSessionUsername,actualCookieSessionUsername);
    }

    @Then("verify the header using overridden get text method is {string}")
    public void verifyHeader(String expectedHeader){
        String actualHeaderText = loginPage.overriddenGetText();
        System.out.println("actualHeaderText: "+actualHeaderText);
        Assert.assertEquals(expectedHeader, actualHeaderText);
    }

}
