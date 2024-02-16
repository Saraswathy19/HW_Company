package testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testbase.BaseClass;

public class HomePage extends BaseClass{
  @Test
  public void industries() {
	 SoftAssert sa=new SoftAssert();
	  
	 driver.findElement(By.xpath("//a[text()='Industries']")).click();
	 List<WebElement> industries= driver.findElements(By.xpath("//a[text()='Industries']/following-sibling::div//ul//li/a"));
	 int total_act_industries = industries.size();
	 System.out.println("Total no of act industries:" +total_act_industries);
	 
	 List<String> mylist=new ArrayList<String>(Arrays.asList("Banking","Education & Institutions","Financial Services",
			 "Hi-Tech, Products & Platforms", "Insurance", "Life Sciences & Healthcare", "Manufacturing", "Professional Services", 
			 "Retail & Consumer", "Telecom & Utilities", "Transportation & Logistics", "Travel & Hospitality"
	 		));
	 
	 int total_exp_industries=mylist.size();
	 System.out.println("Total no of exp industries:" +total_exp_industries);

		if(total_act_industries==total_exp_industries)
		{
			for(int i=0; i<mylist.size(); i++)
			{
				String expindustry=mylist.get(i);
				String actindustry=industries.get(i).getText();
				//System.out.println("Exp:"+expindustry+ " Actual:"+actindustry);
				sa.assertEquals(actindustry, expindustry);	
			}
		}
		else
		{
			Assert.fail("Industry is greater/lesser than expected");
		}

		sa.assertAll();	 
  }
  
  
  @Test
	public void industriesDomain() throws InterruptedException {
		
		driver.navigate().refresh();
		HashMap <String, String>hm=new HashMap<String, String>();
		hm.put("Hexaware Technologies - IT Consulting and Services", "https://hexaware.com/");
		hm.put("Banking IT Services: Technology Solutions For Transformation | Hexaware", "https://hexaware.com/industries/banking/");
		hm.put("Education-Institutions - Hexaware", "https://hexaware.com/industries/education-institutions/");
		hm.put("Financial Services - Hexaware", "https://hexaware.com/industries/financial-services/");
		hm.put("Hi-Tech, Products & Platforms - Hexaware", "https://hexaware.com/industries/hi-tech-products-platforms/");
		hm.put("Hexaware-enabling Digital Transformation in Insurance", "https://hexaware.com/industries/insurance/");
		hm.put("Hexaware's Life Sciences & Healthcare Expertise", "https://hexaware.com/industries/life-sciences-healthcare/");
		hm.put("Manufacturing - Hexaware", "https://hexaware.com/industries/manufacturing/");
		hm.put("Professional Services - Hexaware", "https://hexaware.com/industries/professional-services/");
		hm.put("Retail and Consumer - Hexaware", "https://hexaware.com/industries/retail-and-consumer/");
		hm.put("Telecom and Utilities - Hexaware", "https://hexaware.com/industries/telecom-and-utilities/");
		hm.put("Hexaware: Transportation and Logistics IT Services", "https://hexaware.com/industries/transportation-and-logistics/");
		hm.put("Hexaware: Pioneering IT Services in Travel and Hospitality", "https://hexaware.com/industries/travel-and-hospitality/");
		
		
		HashMap <String, String>hmacttitle=new HashMap<String, String>();
		driver.findElement(By.xpath("//a[text()='Industries']")).click();
		 List<WebElement> industries_domain= driver.findElements(By.xpath("//a[text()='Industries']/following-sibling::div//ul//li/a"));
		
		
		for(WebElement domain: industries_domain)
		{
			System.out.println(domain.getText());
			String clicklnk = Keys.chord(Keys.CONTROL,Keys.ENTER);
			domain.sendKeys(clicklnk);
		}
		
		Set<String> winids=driver.getWindowHandles();
		for(String id: winids)
		{
			String pageTitle=driver.switchTo().window(id).getTitle();
			Thread.sleep(3000);
			String currentUrl= driver.getCurrentUrl();
			//System.out.println(pageTitle+": "+currentUrl);
			hmacttitle.put(pageTitle, currentUrl);		
		}
		System.out.println(hmacttitle);
		System.out.println(hm);
		
		SoftAssert sa=new SoftAssert();
		
		
		if(hmacttitle.equals(hm)) 
		{
			sa.assertTrue(true);
		}
		else 
		{
			System.out.println("I entered");
			for (Map.Entry<String, String> entry : hm.entrySet()) { 
	            String key = entry.getKey();  
	            String value1 = entry.getValue(); 
	            String value2 = hmacttitle.get(key); 

	            if (value2 == null || !value1.equals(value2)) {
	                //System.out.println("Key: " + key + " | Value in map1: " + value1 + " | Value in map2: " + value2);
	                sa.fail("Key: " + key + " | Value in map1: " + value1 + " | Value in map2: " + value2);
	            }
	         }
			
			for (String extrakey : hmacttitle.keySet()) {
				System.out.println(extrakey);
			    if (!hm.containsKey(extrakey)) {
			        sa.fail("Extra entry in UI - Key: " + extrakey + " | Value: " + hmacttitle.get(extrakey));
			    }
			}
			sa.assertAll();
      }
  }
}
