package steps;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import services.HomePageServices;

public class HomePageSteps {

    @Inject
    protected HomePageServices homePageServices;

    @When("User clicks on (\\w*)\\s*\"([^\"]*)\" element")
    public void userClicksOnButton(String number, String element){
        homePageServices.clickOnElement(number,element);
    }

    @Given("^User is on Aliexpress home page$")
    public void userIsOnAliexpressHomePage() {
        homePageServices.openHomePage();
    }

    @And("^User enter \"([^\"]*)\" value in to \"([^\"]*)\" field$")
    public void userEnterValueInToField(String value, String field) throws Throwable {
        homePageServices.setValueInField(value,field);
    }

    @Then("^Verify that (\\w*)\\s*\"([^\"]*)\" contains \"([^\"]*)\" text$")
    public void verifyThatElementContainsText(String number, String field, String expectedText) {
        homePageServices.verifyThatElementContainsText(number,field,expectedText);
    }

}
