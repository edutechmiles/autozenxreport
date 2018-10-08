package com.report;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import com.report.html.ReportHeaderHtml;
import com.report.html.ReportStepsDetails;
import com.report.html.ReportSummaryHtml;
import com.report.html.ReportTestStepHeaderHtml;
import com.report.html.SuiteHeaderHtml;
import com.report.html.TestCaseRowHtml;

import utility.CommonUtility;
import utility.ReadDefaultConfigurationFile;
import utility.ScreenShot;
import utility.SystemProperties;


public class ZenXReport 
{
	SystemProperties objSystemProperties; 
	Map<String, String> configurationMap;
	Map<String, String> systemInfoMap;
	ReportStepsDetails objReportStepsDetails;
	ReportTestStepHeaderHtml objReportTestStepHeaderHtml ;
	public  String fileName = "";
	public String executionStartTime;
	public String executionEndTime;
	public String testStartTime;
	public String testEndTime;
	private FileWriter fstream =null;
	public String dateTimeFormat = "dd-MMM-yyyy hh:mm:ss" ;
	public String reportPath ;
	public static int autoTestId =0;
	public static int snoTestCase = 0;
	public String testStepHtml = "" ;
	public ArrayList<String> totalStepsLst;
	public ArrayList<String> testCaseLst;
	public String reportSummartHtml = "";
	
	/**  will be use in  @Beforesuite
	 * Create report 
	 * @param fileName
	 * @param newFile
	 */
	
	public ZenXReport(String fileName, boolean newFile) {
		
		configurationMap = new ReadDefaultConfigurationFile().readConfigurationFile();
		if(configurationMap.containsKey("reportPath")) {
			reportPath = configurationMap.get("reportPath");
		}
		
		fileName = reportPath+File.separator+fileName;
		setFileName(fileName);
		if(newFile) {
			try {
				fstream = new FileWriter(fileName);
				
				if(configurationMap.isEmpty()) {
					throw new FileNotFoundException("Configuration file (report-config.xml) is not exist in the resource folder");
				}
			}catch(Exception e) {
				
			}
		}else {
			try {
				fstream = new FileWriter(fileName,true);
			}catch (FileNotFoundException e) {
				System.out.println("File not found");
			}catch(Exception e1) {
				
			}

		}
	
		if(configurationMap.containsKey("dateTimeFormat") && !configurationMap.get("dateTimeFormat").equals("")) {
			dateTimeFormat = configurationMap.get("dateTimeFormat");
		}
		
		setExecutionStartTime(CommonUtility.now(dateTimeFormat));
		objSystemProperties = new SystemProperties();
		objReportStepsDetails = new ReportStepsDetails();
		objReportTestStepHeaderHtml = new ReportTestStepHeaderHtml();
		totalStepsLst = new ArrayList<String>();
		testCaseLst = new ArrayList<String>();
		createReportHeader(fileName);
		autoTestId++;

	}
	/****
	 * Create summary report Header -  will be use in  @Beforesuite
	 * @param fileName
	 */
	public void createReportHeader(String fileName) {
		ReportHeaderHtml objReportHeaderHtml = new ReportHeaderHtml();
		String header = objReportHeaderHtml.reportHeader(configurationMap);
		writeDataInReport(header, fileName);
	}
	
	/**
	 * Create summary of report - will be use in  @Beforesuite
	 * @param fileName
	 */
	public String createReportSummary(String fileName) {
		ReportSummaryHtml objReportSummaryHtml = new ReportSummaryHtml();
		String summary = objReportSummaryHtml.reportSummary(configurationMap, objSystemProperties.getSystemInfoMap());
		writeDataInReport(summary, fileName);
		
		return summary;
	}
	
	/**
	 * Create suite header - will be use in  @Beforesuite
	 * @param fileName
	 */
	
	public void startSuit(String suiteName) {
		setTestStartTime(CommonUtility.now(dateTimeFormat));
		createReportSummary(fileName);
		
		SuiteHeaderHtml objSuiteHeaderHtml = new SuiteHeaderHtml();
		String summary = objSuiteHeaderHtml.testCaseSummaryHeader(suiteName);
		writeDataInReport(summary, getFileName());
	}
	
	/*public void startTest(String header) {
		createTestStepReportHeader(header);
	}*/
	
	
	public void endTest(String testCaseName, String description) {
		snoTestCase++;
		
		logTestCaseDetail(String.valueOf(snoTestCase), testCaseName, description);
		setTestStartTime(CommonUtility.now(dateTimeFormat));

	}
	
	/*public void createTestStepReportHeader(String heading) {
		ReportTestStepHeaderHtml objTestRepHeaderHtml = new ReportTestStepHeaderHtml();
		String testHeader = objTestRepHeaderHtml.testStepReportHeaderForAPIAndUi(heading);
		writeDataInReport(testHeader, getFileName());
	}*/
	
	
 	 
	/**
	 * Log steps detail in report
	 * @param testId
	 * @param description
	 * @param result
	 * @param expected
	 * @param actual
	 */
	public void log(String testId, String description, String result, String expected, String actual) {
		if(testId.equals("")) {
			testId = String.valueOf(autoTestId);
			autoTestId++;
		}
		
		objReportStepsDetails.addStepDetails(testId, description, result,expected,actual);
		
		totalStepsLst.add(result);
	}
	
	/**
	 * Log test steps details with screenshot
	 * @param testId
	 * @param description
	 * @param result
	 * @param expected
	 * @param actual
	 * @param screenshotPath
	 */
	public void log(String testId, String description, String result, String expected, String actual, String screenshotPath) {
		
		String screeshotBase64 = ScreenShot.addScreenshot(screenshotPath);
		String stepEndTime = CommonUtility.now(dateTimeFormat);
		objReportStepsDetails.addStepDetails(testId, description, result,expected,actual,  screeshotBase64);//, stepStartTime, stepEndTime);
		//stepStartTime  = CommonUtility.now(dateTimeFormat);
	}
	
	/***
	 * Consolidate the step data in HTML
	 */
	
	public void createTestStepReport() {
		 
		for(int i=0; i< objReportStepsDetails.resultList.size(); i++) {
			if(!objReportStepsDetails.resultList.get(i).equalsIgnoreCase("")) {
				String str = objReportTestStepHeaderHtml.createStepResultRowForWeb(objReportStepsDetails.testCaseIdList.get(i),
						objReportStepsDetails.descriptionList.get(i),
						objReportStepsDetails.expectedList.get(i),objReportStepsDetails.actualValueList.get(i),objReportStepsDetails.resultList.get(i));
				
				if(objReportStepsDetails.resultList.get(i).equals(ReportConstants.FAIL)) {
					testCaseLst.add(ReportConstants.FAIL);
				}
					
				
				testStepHtml+= str;
			} 
 		}
		if(objReportStepsDetails.resultList.contains(ReportConstants.FAIL)) {
			testCaseLst.add(ReportConstants.FAIL);
		}else {
			testCaseLst.add(ReportConstants.PASS);
		}
		
		setTestStepHtml(testStepHtml);
	}
	
	/**
	 * Used in @AfterMethod to log the test case result
	 * @param testId
	 * @param desc
	 */
	public void logTestCaseDetail(String testCaseId, String testCaseName, String description) {
		
		/*** Create step report html from results ***********/
		createTestStepReport();
		
		/**** Create test summary header and test case data ********************/
		TestCaseRowHtml objTestCaseRowHtml = new TestCaseRowHtml();
		setTestEndTime(CommonUtility.now(dateTimeFormat));
		String summary = objTestCaseRowHtml.createResultRow(testCaseId, testCaseName, description, testCaseLst.get(0), getTestStartTime(), getTestEndTime());
		writeDataInReport(summary, getFileName());
		
		/************* Create test steps header **********************/
		String head = objReportTestStepHeaderHtml.testStepReportHeaderForWeb(testCaseName);
		writeDataInReport(head, getFileName());
		
		/************* Create test steps details  **********************/
		writeDataInReport(getTestStepHtml(), getFileName());
		
		/************* Create footer of the HTML **********************/
		String footer = objReportTestStepHeaderHtml.testStepReportFooter();
		writeDataInReport(footer, getFileName());
		
 		 

		
	}
	
	public void addSystemInformation(String key, String value) {
		objSystemProperties.addSystemInfo(key, value);
	}
	 
	
	public void writeDataInReport(String dataToWrite, String fileName) {
		try {
			FileWriter fstream =  new FileWriter(fileName, true);
			BufferedWriter  out = new BufferedWriter(fstream);
			out.write(dataToWrite);
			out.close();
		} catch (IOException e) {

		}
	}
	

	
	public void endReport() {
		 
		int fail = Collections.frequency(testCaseLst, ReportConstants.FAIL);
		int pass  = Collections.frequency(testCaseLst, ReportConstants.PASS);
		int skip  = Collections.frequency(testCaseLst, ReportConstants.SKIP);
		
		
		int totalFailSteps = Collections.frequency(totalStepsLst, ReportConstants.FAIL);
		int totalPassSteps  = Collections.frequency(totalStepsLst, ReportConstants.PASS);
		int totalSkipSteps  = Collections.frequency(totalStepsLst, ReportConstants.SKIP);
		
		
		
		int totalCount = fail+pass; 
		
		StringBuffer buf = new StringBuffer();
		try {
			//Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(getFileName());
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			// Read File Line By Line
		
			while ((strLine = br.readLine()) != null) {

				 if(strLine.indexOf("END_TIME") !=-1){
			    	 strLine=strLine.replace("END_TIME", CommonUtility.now(dateTimeFormat));
			     }
				 
				if(strLine.indexOf("START_TIME") !=-1){
			    	 strLine=strLine.replace("START_TIME", getExecutionStartTime());
			     }
				
				if (strLine.indexOf("Total_Pass") != -1) {
					strLine = strLine.replace("Total_Pass",
							Integer.toString(pass));
				}
				if (strLine.indexOf("Total_Fail") != -1) {
					strLine = strLine.replace("Total_Fail",
							Integer.toString(fail));
				}
				if (strLine.indexOf("Total_Count") != -1) {
					strLine = strLine.replace("Total_Count",
							Integer.toString(totalCount));
				}
				if (strLine.indexOf("Total_Skipped") != -1) {
					strLine = strLine.replace("Total_Skipped",
							Integer.toString(skip));
				}
				if (strLine.indexOf("TotalStepsCount") != -1) {
					strLine = strLine.replace("TotalStepsCount", String.valueOf(totalPassSteps+totalFailSteps+totalSkipSteps));
				}
				if (strLine.indexOf("TotalPassSteps") != -1) {
					strLine = strLine.replace("TotalPassSteps", String.valueOf(totalPassSteps));
				}
				if (strLine.indexOf("TotalFailSteps") != -1) {
					strLine = strLine.replace("TotalFailSteps", String.valueOf(totalFailSteps));
				}
				if (strLine.indexOf("TotalSkipSteps") != -1) {
					strLine = strLine.replace("TotalSkipSteps", String.valueOf(totalSkipSteps));
				}
				
				if (strLine.indexOf("FAIL_PERCENTAGE") != -1) {
					int failPer = fail * 100 / totalCount;
					strLine = strLine.replace("FAIL_PERCENTAGE",
							Integer.toString(failPer))+" %";
				}
				
				
				buf.append(strLine);
				
			}
			// Close the input stream
			in.close();
			FileOutputStream fos = new FileOutputStream(getFileName());
			DataOutputStream output = new DataOutputStream(fos);
			output.writeBytes(buf.toString());
			fos.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}
	
	
	public void endReport1() {
		 
		int fail = Collections.frequency(testCaseLst, ReportConstants.FAIL);
		int pass  = Collections.frequency(testCaseLst, ReportConstants.PASS);
		int skip  = Collections.frequency(testCaseLst, ReportConstants.SKIP);
		
		
		int totalFailSteps = Collections.frequency(totalStepsLst, ReportConstants.FAIL);
		int totalPassSteps  = Collections.frequency(totalStepsLst, ReportConstants.PASS);
		int totalSkipSteps  = Collections.frequency(totalStepsLst, ReportConstants.SKIP);
		
		
		
		int totalCount = fail+pass; 
		
		StringBuffer buf = new StringBuffer();
		try {
			//Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(getFileName());
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			// Read File Line By Line
		
			String htmlSummary = getReportSummartHtml();
	    	 htmlSummary+=htmlSummary.replace("END_TIME", CommonUtility.now(dateTimeFormat));
	    	 htmlSummary+=htmlSummary.replace("START_TIME", getExecutionStartTime());
	    	 htmlSummary+= htmlSummary.replace("Total_Pass",
							Integer.toString(pass));
			 htmlSummary+= htmlSummary.replace("Total_Fail",
							Integer.toString(fail));
			 htmlSummary+= htmlSummary.replace("Total_Count",
							Integer.toString(totalCount));
			 htmlSummary+= htmlSummary.replace("Total_Skipped",
							Integer.toString(skip));
		 	 htmlSummary+= htmlSummary.replace("TotalStepsCount", String.valueOf(totalPassSteps+totalFailSteps+totalSkipSteps));
			 htmlSummary+= htmlSummary.replace("TotalPassSteps", String.valueOf(totalPassSteps));
			 htmlSummary+= htmlSummary.replace("TotalFailSteps", String.valueOf(totalFailSteps));
			 htmlSummary+= htmlSummary.replace("TotalSkipSteps", String.valueOf(totalSkipSteps));
				int failPer = fail * 100 / totalCount;
			htmlSummary+= htmlSummary.replace("FAIL_PERCENTAGE",
							Integer.toString(failPer))+" %";
			
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	 
	
	 
	public String getExecutionStartTime() {
		return executionStartTime;
	}
	public void setExecutionStartTime(String stepStartTime) {
		this.executionStartTime = stepStartTime;
	}
	public String getExecutionEndTime() {
		return executionEndTime;
	}
	public void setExecutionEndTime(String stepEndTime) {
		this.executionEndTime = stepEndTime;
	}
	public String getTestStartTime() {
		return testStartTime;
	}
	public void setTestStartTime(String testStartTime) {
		this.testStartTime = testStartTime;
	}
	public String getTestEndTime() {
		return testEndTime;
	}
	public void setTestEndTime(String testEndTime) {
		this.testEndTime = testEndTime;
	}
	public String getTestStepHtml() {
		return testStepHtml;
	}
	public void setTestStepHtml(String testStepHtml) {
		this.testStepHtml = testStepHtml;
	}
	public String getReportSummartHtml() {
		return reportSummartHtml;
	}
	public void setReportSummartHtml(String reportSummartHtml) {
		this.reportSummartHtml = reportSummartHtml;
	}
	
}
