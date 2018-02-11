package tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport {
  @Test
  public void f() 
  {
	  ExtentReports report = new ExtentReports("./test-output/avinash.html", true, DisplayOrder.OLDEST_FIRST);
	  ExtentTest test  = report.startTest("Test1");
	  test.log(LogStatus.PASS, "First Step");
	  report.endTest(test);
	  if(report!=null)
	  {
		  report.flush();
		  report.close();
		  report = null;
	  }
  }
}
