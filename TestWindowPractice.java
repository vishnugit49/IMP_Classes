package AwesomeJava;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class TestWindowPractice {
	
	public static void main(String arg[]) throws InterruptedException {


	System.out.println("Hello... You are ROCKING NOW!!!");
	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	//automation-practice website ChildWindow Working!!!!!
	driver.get("http://toolsqa.com/automation-practice-switch-windows/");
	driver.findElement(By.xpath("//button[@id='button1']")).click();

	String windowTitle="QA Automation Tools Tutorial";
	String childWindowElementXpath="//div[@class='mini-widgets']/span[3]";
	
	

	
	
	//Calling Methods Switch TO....!!!!
	String x = TestWindowPractice.switchToWindowReturnTextOfTheLinkElementAndClose(driver,windowTitle,childWindowElementXpath);
	System.out.println(x);
	

	driver.close();
	}
	public static String switchToWindowReturnTextOfTheLinkElementAndClose(WebDriver driver, String windowTitle, String childWindowElementXpath) {
			
		String mainWindow=driver.getWindowHandle();
		String childWindowElementText = null;
		// It returns no. of windows opened by WebDriver and will return Set of Strings
		Set<String> set =driver.getWindowHandles();
		// Using Iterator to iterate with in windows
		java.util.Iterator<String> itr= set.iterator();
		while(itr.hasNext()){
			String childWindow=itr.next();
			if(!mainWindow.equals(childWindow)) {
					driver.switchTo().window(childWindow);
					driver.manage().window().maximize();
					String childWindowTitle = driver.getTitle();
					if(childWindowTitle.equals(windowTitle)){	
						childWindowElementText = driver.findElement(By.xpath(childWindowElementXpath)).getText();
						driver.close();
						
					}
			}
		}
		driver.switchTo().window(mainWindow);
		return childWindowElementText;
	}
	
}	

