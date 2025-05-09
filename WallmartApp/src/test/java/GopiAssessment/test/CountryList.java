package GopiAssessment.test;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;


public class CountryList extends AppiumSetup {
	
	@Test(priority=1)
    public void verifyCountryListTitle() 
	{
		try {
		WebElement titleElement = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Countries List\")"));
		String screenTitle = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Countries List\")")).getText();
		Assert.assertTrue(titleElement.isDisplayed(), "Title is not displayed");
        System.out.println(screenTitle);
		}catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        }
       
    }
	
	@Test(priority=2)
	public void verifySearchIcon()
	{
		WebElement searchIcon = driver.findElement(AppiumBy.accessibilityId("Search"));
		Assert.assertTrue(searchIcon.isDisplayed(), "Search icon is not displayed");
	}
	
@SuppressWarnings("deprecation")
	@Test(priority = 3)
   public void verifyCountryListItems() {
	try {
            // Wait for the RecyclerView to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement recyclerView = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.id("com.example.wallmartexample:id/country_recycler_view")));

            // Verify RecyclerView is displayed
            Assert.assertTrue(recyclerView.isDisplayed(), "RecyclerView is not displayed");

            // Find all ViewGroup elements within the RecyclerView
            List<WebElement> countryItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='com.example.wallmartexample:id/nav_host_fragment']//androidx.recyclerview.widget.RecyclerView[@resource-id='com.example.wallmartexample:id/country_recycler_view']/android.view.ViewGroup")));

            // Get the count of country items
            int countryCount = countryItems.size();

            // Debug: Print details of found items
            System.out.println("RecyclerView found: " + recyclerView.getAttribute("resourceId"));
            System.out.println("Number of country items found: " + countryCount);
            for (int i = 0; i < countryCount; i++) {
                System.out.println("Country item " + (i + 1) + ": " +
                        countryItems.get(i).findElement(AppiumBy.id("com.example.wallmartexample:id/name_with_region_view")).getText());
            }

            // Assert that the number of countries is greater than 0
            Assert.assertTrue(countryCount > 0, "Expected more than 0 countries, but found " + countryCount);
            System.out.println("Total countries displayed: " + countryCount);
            
            

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

   @Test(priority = 4)
	public void clickOnCountry() {
		try {
            // Wait for the RecyclerView to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            
            WebElement recyclerViewDetail = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.id("com.example.wallmartexample:id/country_recycler_view")));

            // Verify RecyclerView is displayed
           // Assert.assertTrue(recyclerViewDetail.isDisplayed(), "RecyclerView is not displayed");

            // Find all ViewGroup elements within the RecyclerView
            List<WebElement> CountryItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='com.example.wallmartexample:id/nav_host_fragment']//androidx.recyclerview.widget.RecyclerView[@resource-id='com.example.wallmartexample:id/country_recycler_view']/android.view.ViewGroup")));

            // Get the count of country items
            int countryCount = CountryItems.size();
            
            // CLick on any country from the list
            CountryItems.get(5).click();
            
            // Get country Detail screen title
            String countryDetailTitle=   driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Country Details\")")).getText();
            
           //Verify Country details screen title
            Assert.assertEquals(countryDetailTitle, "Country Details");
            
            WebElement countryTextElement = new WebDriverWait(driver, Duration.ofSeconds(10))
            	    .until(ExpectedConditions.visibilityOfElementLocated(
            	        AppiumBy.id("com.example.wallmartexample:id/name_with_region_view")
            	    ));
            	String countryText = countryTextElement.getText();
            	System.out.println("Country text: " + countryText);
            	
            // Navigate back to country list screen
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageButton\")")).click();
    
              
		} catch(Exception e) {
		System.out.println("Test failed: " + e.getMessage());
        Assert.fail("Test failed due to exception: " + e.getMessage());
		}


	}
    }
	
		
	
	
	
	


