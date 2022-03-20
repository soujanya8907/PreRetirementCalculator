package main.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class PreRetCalc {	
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public final String URL = "https://www.securian.com/insights-tools/retirement-calculator.html"; 
	
	//Driver path...
	public static String driverPath = "../PreRetirementCalculator//src//main//resources//Drivers//chromedriver.exe";
	
	//Parameters
	public static String Current_Age= "40";
	public static String Retirement_Age= "68";
	public static String Current_annual_income= "100000";
	public static String Spouses_annual_income= "75000";
	public static String Current_retirement_savings= "500000";
	public static String Current_retirement_contribution= "0.1";
	public static String Annual_retirement_contribution_increase= "0.0025";
	public static String Social_Security_Income= "Yes";
	public static String Relationship_status= "Married";
	public static String Social_Security_Override= "4000";
	public static String Additional_other_income= "500";
	public static String Number_of_years_retirement_needs_to_last= "20";
	public static String PostRetirement_income_increase_with_inflation= "Yes";
	public static String Percent_of_final_annual_income_desired= "0.75";
	public static String PreRetirement_investment_return= "0.08";
	public static String PostRretirement_investment_return= "0.05";
	
	//Locators
	public static By input_CurrentAge = By.xpath("//input[@id='current-age']");
	public static By input_RetirementAge = By.xpath("//input[@id='retirement-age']");
	public static By input_CurrentIncome = By.xpath("//input[@id='current-income']");
	public static By input_SpouseIncome = By.xpath("//input[@id='spouse-income']");
	public static By input_CurrentRetSavings = By.xpath("//input[@id='current-total-savings']");
	public static By input_CurrentSavingsPerYear = By.xpath("//input[@id='current-annual-savings']");
	public static By input_SavingsIncrPerYear = By.xpath("//input[@id='savings-increase-rate']");
	public static By radio_SSN = By.xpath("//ul[@aria-labelledby='include-social-label']//li/label[text()='Yes']");
	public static By radio_married = By.xpath("//label[text()='Married']");
	public static By input_SSOverideAmount = By.xpath("//input[@id='social-security-override']");
	public static By link_AdjustDefaltValues = By.xpath("//a[text()='Adjust default values']");
	public static By input_AdditionalIncome = By.xpath("//input[@id='additional-income']");
	public static By input_YearstoDepend = By.xpath("//input[@id='retirement-duration']");
	public static By radio_InflationIncrYes = By.xpath("//ul[@aria-labelledby='inflation-label']//li/label[text()='Yes']");
	public static By input_FinalAnnlIncome = By.xpath("//input[@id='retirement-annual-income']");
	public static By input_PreRetireInvstReturn = By.xpath("//input[@id='pre-retirement-roi']");
	public static By input_PostRetireInvstReturn = By.xpath("//input[@id='post-retirement-roi']");
	public static By button_SaveChanges = By.xpath("//button[text()='Save changes']");
	public static By button_Calculate = By.xpath("//button[text()='Calculate']");
	public static By label_Results = By.xpath("//h3[text()='Results']");
		
  @Test
  public void test() throws InterruptedException {
	  
	  sendKeys(input_CurrentAge,Current_Age);
	  sendKeys(input_RetirementAge,Retirement_Age);
	  sendKeys(input_CurrentIncome,Current_annual_income);
	  sendKeys(input_SpouseIncome,Spouses_annual_income);
	  sendKeys(input_CurrentRetSavings,Current_retirement_savings);
	  sendKeys(input_CurrentSavingsPerYear,Current_retirement_contribution);
	  sendKeys(input_SavingsIncrPerYear,Annual_retirement_contribution_increase);
	  click(radio_SSN);
	  Thread.sleep(2000);
	  click(radio_married);
	  sendKeys(input_SSOverideAmount,Social_Security_Override);
	  click(link_AdjustDefaltValues);
	  sendKeys(input_AdditionalIncome,Additional_other_income);
	  sendKeys(input_YearstoDepend,Number_of_years_retirement_needs_to_last);
	  click(radio_InflationIncrYes);
	  Thread.sleep(2000);
	  sendKeys(input_FinalAnnlIncome,Percent_of_final_annual_income_desired);
	  sendKeys(input_PreRetireInvstReturn,PreRetirement_investment_return);
	  sendKeys(input_PostRetireInvstReturn,PostRretirement_investment_return);
	  click(button_SaveChanges);
	  wait.until(ExpectedConditions.elementToBeClickable(button_Calculate));
	  click(button_Calculate);
	  wait.until(ExpectedConditions.visibilityOf(driver.findElement(label_Results)));
	  isDisplayed(label_Results);	  
  }
  
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", driverPath);
	  driver = new ChromeDriver();
	  wait = new WebDriverWait(driver, 40);
	  driver.get(URL);	
	  driver.manage().window().maximize();
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
	  driver.quit();
  }
  
  public static void sendKeys(By element, String parameter) {
	  try {
		  click(element);
		  driver.findElement(element).sendKeys(parameter);
	  }catch(Exception ex) {
		  ex.printStackTrace();
	  }	  
  }
  
  public static void click(By element) {
	  try {
		  driver.findElement(element).click();
	  }catch(Exception ex) {
		  ex.printStackTrace();
	  }	  
  }
  
  public static void isDisplayed(By element) {
	  try {
		  if(driver.findElement(element).isDisplayed()) {
			  System.out.println("Results displayed.");
		  }else {
			  throw new NoSuchElementException("Results label not displayed.");
		  }
	  }catch(Exception ex) {
		  ex.printStackTrace();
	  }
  }

}
