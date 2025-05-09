package GopiAssessment.test;

	import java.io.File;
	import java.net.MalformedURLException;
	import java.net.URI;
	import java.net.URISyntaxException;


import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


	import io.appium.java_client.android.AndroidDriver;
	//import io.appium.java_client.MobileElement;
	import io.appium.java_client.android.options.UiAutomator2Options;
	import io.appium.java_client.service.local.AppiumDriverLocalService;
	import io.appium.java_client.service.local.AppiumServiceBuilder;

	public class AppiumSetup {
		public AppiumDriverLocalService service ;
		protected AndroidDriver driver;
		public WebDriverWait wait;
		
		@BeforeClass
		public void appiumSetup() throws MalformedURLException, URISyntaxException
		{
			
			//Start Appium Server
			service = new AppiumServiceBuilder()
					.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
	                .withIPAddress("127.0.0.1")
	                .usingPort(4723)
	                .build();
	        service.start();
	        
	        //Set up AndroidDriver options
			UiAutomator2Options options= new UiAutomator2Options();
			options.setDeviceName("GopiPhone");
			options.setApp("/Users/akshesh/Downloads/androidAppiumAssessment/WallmartApp/src/test/java/resources/app-debug.apk");
			options.setPlatformName("Android");
			
			//Initialize AndroidDriver
			driver= new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);		
		}
		
		
		@AfterClass
	    public void tearDown() {
	        // Quit the driver and stop the Appium server
	        if (driver != null) {
	            driver.quit();
	        }
	        if (service != null) {
	            service.stop();
	        }
	        System.out.println("TearDown completed.");
		}
	}

