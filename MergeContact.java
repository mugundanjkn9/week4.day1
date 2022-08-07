package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver chrome = new ChromeDriver();
		
		chrome.get("http://leaftaps.com/opentaps/control/login");
		chrome.manage().window().maximize();
		chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		chrome.findElement(By.id("username")).sendKeys("demosalesmanager");
		chrome.findElement(By.id("password")).sendKeys("crmsfa");
		chrome.findElement(By.className("decorativeSubmit")).click();
		
		chrome.findElement(By.linkText("CRM/SFA")).click();
		chrome.findElement(By.linkText("Contacts")).click();
		chrome.findElement(By.linkText("Merge Contacts")).click();
		String mainwindow = chrome.getWindowHandle();
		
		chrome.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdFrom']/following::a")).click();
		Set<String> windowHandlers = chrome.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandlers);
		chrome.switchTo().window(list.get(1));
		chrome.findElement(By.xpath("//a[@class='linktext']")).click();
		chrome.switchTo().window(mainwindow);
		
		chrome.findElement(By.xpath("(//table[@id='widget_ComboBox_partyIdTo'])/following::a")).click();
		Set<String> windowHandlers1 = chrome.getWindowHandles();
		List<String> list1 = new ArrayList<String>(windowHandlers1);
		chrome.switchTo().window(list1.get(1));
		chrome.findElement(By.xpath("(//table[@class='x-grid3-row-table'])[2]//a")).click();
		chrome.switchTo().window(mainwindow);
		
		chrome.findElement(By.className("buttonDangerous")).click();
		Alert alert = chrome.switchTo().alert();
		alert.accept();
		
		Thread.sleep(5000);
		System.out.println(chrome.getTitle());
		String title = chrome.getTitle();
		
		if (title.equals("View Leads | opentaps CRM")) {
			System.out.println("Successfully Completed: "+title);
		}else
		{
			System.out.println("Title is not match");
		}

	}

}

/*
 * 1. Launch URL "http://leaftaps.com/opentaps/control/login"
 * 2. Enter UserName and Password Using Id Locator
 * 3. Click on Login Button using Class Locator
 * 4. Click on CRM/SFA Link
 * 5. Click on contacts Button
 * 6. Click on Merge Contacts using Xpath Locator
 * 7. Click on Widget of From Contact
 * 8. Click on First Resulting Contact
 * 9. Click on Widget of To Contact
 * 10. Click on Second Resulting Contact
 * 11. Click on Merge button using Xpath Locator
 * 12. Accept the Alert
 * 13. Verify the title of the page
 */