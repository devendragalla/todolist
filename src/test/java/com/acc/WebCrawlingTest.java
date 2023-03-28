package com.acc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class WebCrawlingTest {
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.edge.driver",
				"/Users/devendra/Downloads/edgedriver_mac64/msedgedriver");
		WebDriver driver = new EdgeDriver();
		driver.get("file:////Volumes/Deva/Windsor/WINTER_2023/ACC_COMP8547/Project/todolist/src/main/resources/index.html");
		WebElement button = driver.findElement(By.xpath("/html/body/button[1]"));
		Thread.sleep(10000);

		// Click the button
		button.click();

		WebElement button1 = driver.findElement(By.xpath("/html/body/button[3]"));
		button1.click();

		Thread.sleep(10000);

		// Click the button
		button.click();
		Thread.sleep(10000);
		System.out.println(driver.getTitle());
		driver.quit();
	}
}
