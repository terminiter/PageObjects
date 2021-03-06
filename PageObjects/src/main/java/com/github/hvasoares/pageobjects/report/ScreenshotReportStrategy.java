package com.github.hvasoares.pageobjects.report;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.github.hvasoares.pageobjects.WebDriverHolder;

public class ScreenshotReportStrategy implements ReportStrategy{
 
	private PathGenerator pathGenerator = new PathGenerator();
	
	@Override
	public void report( ReportContextI reportContext ,  String event ) {	 
		takeScreenshot( reportContext  , event );
	}
	
	private void takeScreenshot( ReportContextI reportContext  , String event   ) {		
		try{			
			byte[] screenShot = ( (TakesScreenshot) WebDriverHolder.getWebDriver()).getScreenshotAs(OutputType.BYTES);
			
			InputStream in = new ByteArrayInputStream( screenShot );							 
			OutputStream out = new FileOutputStream( pathGenerator.generate(reportContext, event) );	
			IOUtils.copy(in, out);
		} catch ( IOException  e ){			
			throw new RuntimeException("Couldn't create screenshots",e);
		}
	} 	 
}
