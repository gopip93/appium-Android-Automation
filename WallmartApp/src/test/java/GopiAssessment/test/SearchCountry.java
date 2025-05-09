package GopiAssessment.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class SearchCountry extends AppiumSetup{
	
	@Test
	public void clickOnSearchIcon()
	{
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
         WebElement recyclerView = wait.until(ExpectedConditions.presenceOfElementLocated(
                 AppiumBy.id("com.example.wallmartexample:id/country_recycler_view")));
		driver.findElement(AppiumBy.id("com.example.wallmartexample:id/action_search")).click();
		
	}
	
	
	@Test
	public void searchCountries() throws InterruptedException
	{
	
		driver.findElement(AppiumBy.id("com.example.wallmartexample:id/search_src_text")).sendKeys("can");
		List<WebElement> searchList=driver.findElements(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.example.wallmartexample:id/country_recycler_view\"]/android.view.ViewGroup[1]"));
		
		Thread.sleep(5000);
		
		searchList.stream().filter(a->a.getText().equalsIgnoreCase("American Samoa, OC")).findFirst().ifPresent(a->a.click());
	}
	
	@Test
	public void clearSearch() throws InterruptedException
	{
		driver.findElement(AppiumBy.accessibilityId("Clear query")).click();
		
		
	}

}
