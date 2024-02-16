package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import testbase.BaseClass;

public class AboutPage extends BaseClass{
	
	@Test
	public void findCEO() throws InterruptedException {
		WebElement aboutus=driver.findElement(By.xpath("//a[text()='About']"));
		  WebElement abouthw=driver.findElement(By.xpath("//div[@class='dropdownMenu']//a[text()='About Hexaware']"));
		  Actions act=new Actions(driver);
		  act.moveToElement(aboutus).moveToElement(abouthw).click().perform();
		  Assert.assertEquals(driver.getTitle(), "About us - Hexaware");
		  
		  JavascriptExecutor js=(JavascriptExecutor)driver;
		  js.executeScript("window.scrollBy(0,2500)", "");
		  
		  Thread.sleep(3000);
		  
		  String ceoName=driver.findElement(By.xpath("//div[@class='ceo_card_body']/h6")).getText();
		  System.out.println("Hexaware CEO "+ ceoName);
		  Assert.assertEquals(ceoName, "R Srikrishna");		
	}
  @Test
  public void totalEmployees() throws InterruptedException {
 
	  JavascriptExecutor js=(JavascriptExecutor)driver;
	  js.executeScript("window.scrollBy(0,700)", "");
	  
	  Thread.sleep(3000);
	  
	  String employees=driver.findElement(By.xpath("//div[@class='key_facts_slick']//div[2]/h6")).getText();
	  System.out.println("Total no of employees in Hexaware:"+ employees);
	  Assert.assertEquals(employees, "28K");		
	  
  }
}
