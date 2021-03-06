package scrappers;

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
import org.testng.annotations.BeforeTest;
//import validationofdifferenttest;
import org.testng.annotations.Test;

import com.test.utility.Xls_Reader;

import validationofdifferenttestcases.frameworktest;

//gps -0001 to make the code adaptable to take from the last vacant row 
// gps - 0002 to make the error handling incase the browser stops or fails in any of the iterations.
// gps - 0003 to make another sheet available get the entries of all the searched places..

public class FPL_Top30_Lge_Scrapper {
	

	public  String  fplExcel(String place,int gw,String shitt,int crete)throws InterruptedException
	
	{
		frameworktest fwt = new frameworktest();	
		System.out.println("inside FPL Excle method");	
		Xls_Reader r= new Xls_Reader("H:\\vsos\\TenForBen.github.io\\EdisonLogs\\weather.xlsx");
		String snj =shitt;
		int i = crete;
		int  LR =  r.getLastRwoNum(snj);
		//System.out.println("The last row by method  " + LR);
		int LRs=LR+1;
		//System.out.println("The last row count is  " + LRs);
		System.setProperty("webdriver.chrome.driver","D:\\Selenium\\chromedriver.exe"); // declaring the chrome driver locatoion
		WebDriver driver= new ChromeDriver();// initializing chrome driver
		//driver.manage().deleteAllCookies(); // deleting all cookies
		driver.manage().window().maximize();		// maximizing the window
		String searchParam=place +" coordinates";		// earlier param
		int  gameweek =gw ;
		String  ticker = place;
		String uri= "https://fantasy.premierleague.com/entry/" + ticker + "/event/"+ gameweek ;
		System.out.println("URL formed -" +uri);
		driver.get(uri);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);		
		String searchReq =place;
		String fp= driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div[1]/div[3]/div/div[1]/div[1]/div/div ")).getText() ;
		// xpath extraction is leading to error in fp- reload points and reload texts are also coming up.. 
		//best way IMO is using conventional className operator..
		// teamNameXpath - //*[@id="root"]/div[2]/div[2]/div[2]/div/div[1]/div[1]/div[1]/h4
		String[] latestPoints = fp.split("\n");
		fp=latestPoints[0];
		//System.out.println("after split  " +latestPoints[0]);
		
		String teamName= driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[1]/div[1]/h4")).getText() ;	
		String playerName= driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/h2")).getText() ;
		////*[@id="root"]/div[2]/div[2]/div[2]/div/div[1]/div[1]/div[2]/ul/li[2]/div
		String transferXpath = "//*[@id=\"root\"]/div[2]/div[2]/div[1]/div[3]/div/div[2]/div[2]/div[2]/div";
		String overallPoints= driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[1]/div[2]/ul/li[1]/div")).getText() ;
		String overallRank= driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[1]/div[2]/ul/li[2]/div")).getText() ;
		String gwTransfer= driver.findElement(By.xpath(transferXpath)).getText() ;
		String spielerNama =   "PitchElementData__ElementName-sc-1u4y6pr-0 iAoMMY";
		String spielerPunkte = "PitchElementData__ElementValue-sc-1u4y6pr-1 eNGpzK";
		String xPathClasser = "//*[contains(@class, 'Pitch__PitchElementWrap-sc-1mctasb-4 bWWBeR notranslate')]";
		String xPathTooltip = "(//*[contains(@class, 'Pitch__PitchElementWrap-sc-1mctasb-4 bWWBeR notranslate')])[10]/*";
		List<WebElement> allInputElementsV   = driver.findElements(By.cssSelector(".PitchElementData__ElementValue-sc-1u4y6pr-1"));
		List<WebElement> allInputElementsN   = driver.findElements(By.cssSelector(".PitchElementData__ElementName-sc-1u4y6pr-0"));
		List<WebElement> xpathFinder   = driver.findElements(By.xpath(xPathClasser));
		if(allInputElementsN.size() != 0) 
		   {
			   //System.out.println(allInputElementsN.size() + " Elements found by css selector \n");
				
			   for(int sp=0;sp<allInputElementsN.size();sp++) 
			   {
				   //System.out.println("   " +allInputElementsN .get(sp).getAttribute("innerText") +"   " +allInputElementsV .get(sp).getAttribute("innerText"));
				   //Player_1
				   //System.out.println(" exploration  player " +(sp+1) +" " +xpathFinder .get(sp).getAttribute("innerText") );
				   String Prossy = xpathFinder .get(sp).getAttribute("innerText") ;
				   String[] result = Prossy.split("\n");
					String PlyName =result[0];
					String PlyPoints=result[1];
				   //String impStuff ="" +allInputElementsN .get(sp).getAttribute("innerText") +" " +allInputElementsV .get(sp).getAttribute("innerText");
					String impStuff ="" +PlyName+" " +PlyPoints;
					//(//*[contains(@class, 'Pitch__PitchElementWrap-sc-1mctasb-4 bWWBeR notranslate')])[10]/*/
					String customxPath ="(//*[contains(@class, 'Pitch__PitchElementWrap-sc-1mctasb-4 bWWBeR notranslate')])["+(sp+1) +"]/*/*";
					List<WebElement> rama   = driver.findElements(By.xpath(customxPath));
					int yana = rama.size();
					//System.out.println("ramayana katte  " +yana);
					if(yana>3)
					{
						System.out.println("Captain or Vice Captain @");
						String xPath4SVG ="((//*[contains(@class, 'Pitch__PitchElementWrap-sc-1mctasb-4 bWWBeR notranslate')])["+(sp+1) +"]/*/*)[3]";
						//((//*[contains(@class, 'Pitch__PitchElementWrap-sc-1mctasb-4 bWWBeR notranslate')])[10]/*/*)[3]
						//List<WebElement> xpathxPath4SVG   = driver.findElements(By.xpath(xPath4SVG));
						 //String svg = xpathxPath4SVG .get(1).getAttribute("innerHTML") ;
						 //System.out.println("svg katte  " +svg);
					 WebElement elementPath = driver.findElement(By.xpath(xPath4SVG));
					 String svgClassName = elementPath.getAttribute("class");
					 //System.out.println("svg classNama --" +svgClassName);
					 String[] varra = svgClassName.split("StyledCaptain");
						if(varra.length>1)
						{
							impStuff = "" + impStuff +"$ captain";
						}
						else {
							 System.out.println("player is viceCaptain  -----" +impStuff);
						}
					 
						
					}
					
					//System.out.println(" impStuff  2238 " +(sp+1) +" " +impStuff );
				   String ColumbName ="Player_" +(sp+1);
				   //System.out.println("colm Name " +ColumbName);
				   r.setCellData(snj, ColumbName, i, impStuff);

				   //System.out.println(allInputElements .get(sp).getAttribute("innerHTML"));
			   }
		   }
		
		/*System.out.println("final points -  " +fp);	
		System.out.println("Teams Name is  -  " +teamName);	
		System.out.println("Player Name is  -  " +playerName);	
		System.out.println("overall points -  " +overallPoints);	
		System.out.println("gw transfer -  " +gwTransfer);*/
		String searchResult= fp +"~" + teamName +"~"+ playerName+"~" + overallPoints  +"~" + overallRank;
	    System.out.println("Final ORPoints "+" are " + fp +" points ");
		r.setCellData(snj, "Latest Score", i, fp);
		r.setCellData(snj, "SXL", i, fp);
		r.setCellData(snj, "Teams", i, teamName);
		r.setCellData(snj, "manager_Name", i, playerName);
		r.setCellData(snj, "Trainer_name", i, playerName);
		r.setCellData(snj, "overallRank", i, overallRank);
		r.setCellData(snj, "overallPoints", i, overallPoints);
		r.setCellData(snj, "gwXfr", i, gwTransfer);
		
		fwt.quitbrowser(driver);
		
		
		return searchResult; // stores the value of searchResult in SR string  in teh iterator method
		// for multiple return we can condense the the two varriable temp and coords in one and then split in the main mehtod.
		
		
	}
	
	@Test
	public void iteraetor() throws InterruptedException
	{
		
		Xls_Reader r= new Xls_Reader("H:\\vsos\\TenForBen.github.io\\EdisonLogs\\weather.xlsx");
		String snj ="Sheet9";
		System.out.println("League Scrapper - " +snj);	
		int  LR =  r.getLastRwoNum(snj);
		//System.out.println("The last row by method  " + LR);
		int LRs=LR+1;
		System.out.println("The last row count is LRs " + LRs);
		int numVar = 2;
		int gw=11;		
		for( numVar =1;numVar<=1;numVar++)
		{
				for( int i =2;i<=LRs;i++)
				{
							String place =r.getCellData(snj, "Manager_iD", i);	
							System.out.println("Places  at position------------------------------                     "+ i +"  -----------------is                   " + place);
							String receivedValue=fplExcel(place,gw,snj,i);
							String[] result = receivedValue.split("~");
				}
			Thread.sleep(100);
		}
		String s1="Sheet1";
		String s2="Sheet2";
		 //swicherr(s1,s2);
		
		
		
		
	}

	
	
	public void FirstFetcher() throws InterruptedException
	{
		System.out.println("League Fetcher - " );
		Xls_Reader r= new Xls_Reader("H:\\vsos\\TenForBen.github.io\\EdisonLogs\\weather.xlsx");
		String snj ="FPL6";
		String historyChips = "(//*[contains(@class, 'Table-ziussd-1 fHBHIK')])[2]/tbody/tr";
		int gw=8;		
		String leagueID ="217319";	// leagueID -217319 -9 losers
		String laGarbage=fetcher(leagueID,gw,snj,3);
		System.out.println("inside FirstFetcher - " +snj);	
		int  LR =  r.getLastRwoNum(snj);
		System.out.println("The last row by method  " + LR);
		int LRs=LR+2 ;
		System.out.println("The last row count is  " + LRs);
		for( int numVar =1;numVar<=1;numVar++)
		{
			for( int i =2;i<=LRs;i++)
			{
				System.out.println("iinside loop");	
						String place =r.getCellData(snj, "Manager_iD", i);	
						System.out.println("mgrId  at position "+ i +" is " + place);
						//String receivedValue=fplExcel(place,gw,snj,i);
						//String[] result = receivedValue.split("~");
			}
			Thread.sleep(100);
		}
		String s1="Sheet1";
		String s2="Sheet2";
		 //swicherr(s1,s2);
		
		
		
		
	}
	
	
	public String fetcher(String place,int gw,String shitt,int crete) throws InterruptedException
	{
		frameworktest fwt = new frameworktest();	
		System.out.println("inside iterator method");	
		Xls_Reader r= new Xls_Reader("H:\\vsos\\TenForBen.github.io\\EdisonLogs\\weather.xlsx");
		String snj =shitt;
		int i = crete;
		int  LR =  r.getLastRwoNum(snj);
		System.out.println("The last row by method  " + LR);
		int LRs=LR+1;
		System.out.println("The last row count is  " + LRs);
		System.setProperty("webdriver.chrome.driver","D:\\Selenium\\chromedriver.exe"); // declaring the chrome driver locatoion
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		String searchParam=place +" coordinates";
		int  gameweek =gw ;
		String  ticker = place;
		String uri= "https://fantasy.premierleague.com/leagues/" + ticker + "/standings/c" ;
		//https://fantasy.premierleague.com/leagues/56210/standings/c
		System.out.println("URL formed -" +uri);
		driver.get(uri);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);		
		String trClassName ="StandingsRow-fwk48s-0";
		List<WebElement> rampoola   = driver.findElements(By.className(trClassName));
		System.out.println("number of members in the league page is " +rampoola.size());
		int rator = rampoola.size();
		System.out.println("total items on league page  " + rator);
		for( int ii =0;ii<rator;ii++)
		{
					String xPath4href ="(//*[contains(@class, 'StandingsRow-fwk48s-0')])["+(ii+1) +"]/td[2]/a";	
					String xPath4TeamName ="(//*[contains(@class, 'StandingsRow-fwk48s-0')])["+(ii+1) +"]/td[2]/a/strong";
					WebElement exHref = driver.findElement(By.xpath(xPath4href));
					WebElement exTeamNaef = driver.findElement(By.xpath(xPath4TeamName));
					//String extractedTeamNama =exTeamNaef.getAttribute("text");
					String extractedTeamNama =exTeamNaef.getText();
					String exMgrId = exHref.getAttribute("href");
					//System.out.println("each href extracted for rank  "+ (ii+1) +" is " + exMgrId);
					//String receivedValue=fplExcel(place,gw,snj,i);
					String[] result = exMgrId.split("entry/");
					String PlyPoints=result[1];
					String[] idVal = PlyPoints.split("/");
					String extractedMgrId =idVal[0];
					System.out.println("for Team "+extractedTeamNama +" at  "  + (ii+1) +" manager id is " + extractedMgrId);
					r.setCellData(snj, "Trainer_name", (ii+2), extractedTeamNama);
					r.setCellData(snj, "Manager_iD", (ii+2), extractedMgrId);
					
					
		}
		
		
		fwt.quitbrowser(driver);
		return "Parc Guell";
		
	}
	
}
