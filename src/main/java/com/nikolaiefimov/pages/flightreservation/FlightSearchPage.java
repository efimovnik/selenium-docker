package com.nikolaiefimov.pages.flightreservation;

import com.nikolaiefimov.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {

    @FindBy(id = "passengers")
    private WebElement passengersSelect;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(passengersSelect));
        return this.passengersSelect.isDisplayed();
    }

    public void selectPassengers(String noOfPassengers) {
        Select passengers = new Select(this.passengersSelect);
        passengers.selectByValue(noOfPassengers);
    }

    public void SearchFlights() {
        this.searchFlightsButton.click();
    }
}
