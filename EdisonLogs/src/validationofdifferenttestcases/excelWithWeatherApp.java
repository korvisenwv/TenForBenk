package validationofdifferenttestcases;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.net.ntp.TimeStamp;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import validationofdifferenttest;
import org.testng.annotations.Test;

import com.test.utility.Xls_Reader;

import validationofdifferenttestcases.frameworktest;

//gps -0001 to make the code adaptable to take from the last vacant row 
// gps - 0002 to make the error handling incase the browser stops or fails in any of the iterations.
// gps - 0003 to make another sheet available get the entries of all the searched places..

public class excelWithWeatherApp {
	

	public  String  gpsExcel(String place)throws InterruptedException
	
	{
		frameworktest fwt = new frameworktest();		
		System.setProperty("webdriver.chrome.driver","D:\\Selenium\\chromedriver.exe"); // declaring the chrome driver locatoion
		WebDriver driver= new ChromeDriver();// initializing chrome driver
		//driver.manage().deleteAllCookies(); // deleting all cookies
		driver.manage().window().maximize();		// maximizing the window
		String searchParam=place +" coordinates";		// earlier param
		String uri= "https://tenforben.github.io/FPL/vannilaWeatherApp/index.html";
		System.out.println("URL formed -" +uri);
		driver.get(uri);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.findElement(By.xpath("//*[@id=\"introAgreeButton\"]/span/span")).click(); 
		// in case chorme popUP comes up.. with i agree button	
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);		
		String searchReq =place;
		WebElement searchBarr=driver.findElement(By.id("searchUser"));
		searchBarr.sendKeys(searchReq);		
		WebElement sambi = driver.findElement(By.id("submit"));
		 sambi.click();
		 Thread.sleep(2000);
		//String searchR= driver.findElement(By.xpath("/html/body/div[3]/div/div/p[1]")).getText() ;
		// WebElement  searchResonse= driver.findElement(By.id("xPat"));
		 //document.getElementById("cuwt").innerText
		 WebElement  searchResonse= driver.findElement(By.id("cuwt"));
		 String searchRes = searchResonse.getText(); 		 
		String searchResult= searchRes ;
		System.out.println("CUrrent temperature  updated in excel  ");		
		fwt.quitbrowser(driver);
		return searchResult; // stores the value of searchResult in SR string  in teh iterator method
		
	}
	
	@Test
	public void iteraetor() throws InterruptedException
	{
		System.out.println("inside iterator method");	
		Xls_Reader r= new Xls_Reader("H:\\vsos\\TenForBen.github.io\\EdisonLogs\\gps.xlsx");
		int  LR =  r.getLastRwoNum("Sheet1");
		System.out.println("The last row by method  " + LR);
		int LRs=LR+1;
		System.out.println("The last row count is  " + LRs);
		for( int i =2;i<=LRs;i++)
		{
			String place =r.getCellData("Sheet1", "Places", i);	
			System.out.println("Places  at position "+ i +" is " + place);
			String SR=gpsExcel(place);
			r.setCellData("Sheet1", "Coordinates", i, SR);
			System.out.println("coordinates updated in excel  ");	
			 Date date = new Date();
		       System.out.println(new Timestamp(date.getTime()));
		       System.out.println( TimeStamp.getCurrentTime());
		       //TimeStamp  ts = TimeStamp.getCurrentTime();
		   	//r.setCellDataTS("Sheet1", "timeStamp", i, ts);
			
		}
		String s1="Sheet1";
		String s2="Sheet2";
		 //swicherr(s1,s2);
		
		
		
		
	}
	
	public void starterrr() 
	{
		
		
	}
	
	public void swicherr(String s1, String s2)
	{	
		System.out.println("inside swicherr method");	
		Xls_Reader r= new Xls_Reader("H:\\vsos\\TenForBen.github.io\\EdisonLogs\\gps.xlsx");
		int  LR2 =  r.getLastRwoNum(s2);
		System.out.println("Total items in  sheet 2  " + LR2);
		int LRs2=LR2+1;
		System.out.println("The last row of sheet 2  " + LRs2);
		int newEntrzComing=LRs2+1;
		System.out.println("newEntrzComin for sheet 2  " + newEntrzComing);
		int  LR1 =  r.getLastRwoNum(s1);
		System.out.println("The last row of sheet 1  " + LR1);
		int LRs=LR1+1;
		System.out.println("The last row count 4 loop " + LRs);
		for( int i =2;i<=LRs;i++)
		{
			String place =r.getCellData("Sheet1", "Places", i);	
			System.out.println("Places  at position "+ i +" in sheet1 is   " + place);
			String loc =r.getCellData("Sheet1", "Coordinates", i);	
			System.out.println("Coordinates  at position "+ i +" in sheet1 is  " + loc);
			String SR=place;
			String SR2=loc;
			r.setCellData("Sheet2", "Places", LRs2+1, SR);
			System.out.println("Places column   updated in excel  at  rowNumber " +  (LRs2+1) );	
			r.setCellData("Sheet2", "Coordinates", LRs2+1, SR2);
			System.out.println("Coordinates  column   updated in excel  at  rowNumber " + (LRs2+1) );	
			
			LRs2=LRs2+1;
		       //TimeStamp  ts = TimeStamp.getCurrentTime();
		   	//r.setCellDataTS("Sheet1", "timeStamp", i, ts);
			
		}
		
		
	}


	public void tryere() throws InterruptedException
	{
		Xls_Reader r= new Xls_Reader("H:\\vsos\\TenForBen.github.io\\EdisonLogs\\gps.xlsx");
		//int  LR =  r.getLastRwofaColm("Sheet1",2);
		 r.getLastRwofaColm("Sheet1",2);
		//System.out.println("The last row by method  " + LR);
		 //wat we can do is iterate over the elements in the collumn and then break wen cells starts throwing blank values..
		 
	}
}
