package pages;

import static com.codeborne.selenide.Selenide.$$x;

import com.codeborne.selenide.ElementsCollection;

public class HomePage {

    public ElementsCollection getSearchButton() {
        return $$x(".//form[contains(@class,'searchbar-form')]//input[contains(@type,'submit')]");
    }

    public ElementsCollection getSearchField() {
        return $$x(".//div[contains(@class,'search')]//input[@type=\"text\"]");
    }

    public ElementsCollection getProduct() {
        return $$x(".//div[contains(@class,'product-card')]");
    }
}
