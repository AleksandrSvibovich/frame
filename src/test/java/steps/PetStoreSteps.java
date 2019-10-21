package steps;

import com.google.inject.Inject;
import io.cucumber.java.en.Given;
import services.PetStoreServices;

public class PetStoreSteps {
    @Inject
    PetStoreServices petStoreServices;

    @Given("Verify that for \"(\\w*)\" method response code is (\\d{3})$")
    public void verifyThatResponseCode(String method, Integer code) {
        petStoreServices.verifyStatusCode(code, method);
    }

    @Given("Add pet with id (\\d+){1,7} and name \"(\\w+)\" and status \"(\\w+)\"$")
    public void addNewPetToStore(int id, String name, String status) {
        petStoreServices.addNewPetToStore(id, name, status);
    }

    @Given("Get pet with id (\\d+){1,7} and verify that name is \"(\\w+)\"$")
    public void getPetWithIdAndVerifyThatNameIs(Integer id, String name) {
        petStoreServices.getPetById(id, name);
    }

    @Given("Delete pet by id (\\d+){1,7}$")
    public void deletePetById(int id){
        petStoreServices.deletePetById(id);
    }
}
