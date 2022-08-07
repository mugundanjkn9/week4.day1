package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForce {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		
		ChromeDriver chrome = new ChromeDriver();
		chrome.get("https://login.salesforce.com/");
		chrome.manage().window().maximize();
		chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable_notifications");

		chrome.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		chrome.findElement(By.id("password")).sendKeys("Password$123");
		chrome.findElement(By.id("Login")).click();
		String mainwindow = chrome.getWindowHandle();
		
		chrome.findElement(By.xpath("(//span[text()='Mobile Publisher'])/following::span[2]")).click();
		
		Set<String> windowHandlers = chrome.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandlers);
		chrome.switchTo().window(list.get(1));
		
		System.out.println(chrome.getTitle());
		chrome.findElement(By.xpath("//button[text()='Confirm']")).click();
		System.out.println(chrome.getTitle());
		chrome.switchTo().window(mainwindow);
		System.out.println(chrome.getTitle());
		System.out.println(Thread.activeCount());
		chrome.quit();
		
		File Source = chrome.getScreenshotAs(OutputType.FILE);
		File dest = new File("SalesForce1.png");
		try {
			FileUtils.copyFile(Source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(FileUtils.getFile(Source, args));
		
	}

}

/*
Salesforce Customer service:
1.Launch the browser
2.Load the url as " https://login.salesforce.com/ "
3.Enter the username as " ramkumar.ramaiah@testleaf.com "
4. Enter the password as " Password$123 "
5.click on the login button
6.click on the learn more option in the Mobile publisher
7.Switch to the next window using Windowhandles.
8.click on the confirm button in the redirecting page
9.Get the title
10.Get back to the parent window
11.close the browser

*/