package com.acc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class WebCrawlingTest {
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.edge.driver",
				"C:\\Users\\HP\\OneDrive\\Documents\\GitHub\\todolist\\src\\main\\resources\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("file:///C:/Users/HP/OneDrive/Documents/GitHub/todolist/src/main/resources/html/index.html");
		driver.findElement(By.xpath("/html/body/form/div/div[1]/input")).sendKeys("abcd@gmail.com");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("/html/body/form/div/div[2]/input")).sendKeys("pwd");
		Thread.sleep(1000);

		// Click the button
		//button.click();

		WebElement button1 = driver.findElement(By.xpath("/html/body/form/div/button"));
		button1.click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/form/div/div[1]/input")).clear();
		driver.findElement(By.xpath("/html/body/form/div/div[1]/input")).sendKeys("jane.doe@example.com");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("/html/body/form/div/div[2]/input")).clear();
		driver.findElement(By.xpath("/html/body/form/div/div[2]/input")).sendKeys("newpassword");
		Thread.sleep(1000);

		// Click the button
		//button.click();

		button1.click();

		// Click the button
		//button.click();
		Thread.sleep(10000);
		System.out.println(driver.getTitle());
		driver.quit();
	}
}
