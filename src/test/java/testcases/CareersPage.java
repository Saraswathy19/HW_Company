package testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import testbase.BaseClass;

public class CareersPage extends BaseClass {
  @Test
  public void searchJobs() throws InterruptedException {
	  WebElement careers=driver.findElement(By.xpath("//a[text()='Careers']"));
	 WebElement portal= driver.findElement(By.xpath("//h5[text()='Hexaware Jobs Portal']/following-sibling::img"));
	 Actions act=new Actions(driver);
	 act.moveToElement(careers).moveToElement(portal).click().perform();
	 
	 JavascriptExecutor js=(JavascriptExecutor)driver;
	 js.executeScript("window.scrollBy(0,700)", "");
	 
	 driver.findElement(By.id("select2-key-skills-first-container")).click();
	 List<WebElement> skills=driver.findElements(By.xpath("//ul[@id='select2-key-skills-first-results']//li"));
	 for(WebElement skill: skills)
	 {
		 if(skill.getText().equals("Automation Testing"))
		 {
			 skill.click();
			 break;
		 }
	 }
	 
	 Thread.sleep(2000);
	 driver.findElement(By.id("select2-job-country-first-container")).click();
	 List<WebElement> countries=driver.findElements(By.xpath("//ul[@id='select2-job-country-first-results']//li"));
	 for(WebElement country: countries)
	 {
		 if(country.getText().equals("India"))
		 {
			 country.click();
			 break;
		 }
	 }
	 
	 driver.findElement(By.id("select2-exp-years-first-container")).click();
	 List<WebElement> exps=driver.findElements(By.xpath("//ul[@id='select2-exp-years-first-results']//li"));
	 for(WebElement exp: exps)
	 {
		 if(exp.getText().equals("4"))
		 {
			 exp.click();
			 break;
		 }
	 }
	 
	 driver.findElement(By.id("joblisting-searchbutton")).click();
	 Thread.sleep(5000);
	 String res=driver.findElement(By.xpath("//h4[@class='count-disp']")).getText();
	 System.out.println("Total: "+res);
	 
	 Assert.assertEquals(res, "Found 17 Jobs");
  }
}
