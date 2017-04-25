package com.httpframe.httpframe;

import org.apache.commons.logging.Log;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.log4testng.Logger;


public class CunstomListener extends TestListenerAdapter{
	private static Logger logger = Logger.getLogger(CunstomListener.class);
	
	@Override
	public void onTestFailure(ITestResult tr)
	{
		System.out.println(tr.getName()+"--test method fail!");
		
	}
	@Override
	public void onTestSuccess(ITestResult tr)
	{
		
		
		
	}
	
	

}
