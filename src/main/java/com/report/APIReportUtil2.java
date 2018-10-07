package com.report;
/**
 * @author Nitin Singh
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class APIReportUtil2 
{
	public static int c=0;
	public int scriptNumber=1;
	public String indexResultFilename;
	public String currentDir;
	public String currentSuiteName;
	public int tcid;
	//public static String currentSuitePath;
	
	public  double passNumber;
	public  double failNumber;
	public  boolean newTest=true;
	public  ArrayList<String> testCaseIdLst	=	new ArrayList<String>();;
	public  ArrayList<String> descriptionLst=	new ArrayList<String>();;
	public  ArrayList<String> actualValueLst=	new ArrayList<String>();;
	public  ArrayList<String> requestLst	=	new ArrayList<String>();;
	public  ArrayList<String> expectedLst	=	new ArrayList<String>();;
	public  ArrayList<String> responseLst	=	new ArrayList<String>();;
	public  ArrayList<String> resultLst 	=	new ArrayList<String>();;
	public  ArrayList<String> methodLst		=	new ArrayList<String>();;
	public  ArrayList<String> locatorLst	=	new ArrayList<String>();;
	public  ArrayList<String> blankRow		=	new ArrayList<String>();;
	public  ArrayList<String> screenShotLst	=	new ArrayList<String>();;

	
	FileWriter testStepFileStream=null;
	public String testStepFileName;
	public int sNo=1;

	
	public static String now(String dateFormat) {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    return (String)sdf.format(cal.getTime());

	}
	

	
	public void startTesting(String filename,String testStartTime,String env,String rel, String bwr)
	  {
		indexResultFilename = filename;
		currentDir = filename.substring(0,filename.lastIndexOf(File.separator));
		
		FileWriter fstream =null;
		 BufferedWriter out =null;
	      try{
	    // Create file 
	   
	     fstream = new FileWriter(filename);
	     out = new BufferedWriter(fstream);

        String RUN_DATE = now("dd.MMMMM.yyyy").toString();
	    
	    String ENVIRONMENT = env;//SeleniumServerTest.ConfigurationMap.getProperty("environment");
	    String RELEASE = rel;//SeleniumServerTest.ConfigurationMap.getProperty("release");
	    String BROWSER = bwr;
	    out.newLine();
	  
	    out.write("<html lang=\"en\">\n" + 
	    		"\n" + 
	    		"<head>\n" + 
	    		"    <title>Bootstrap Example</title>\n" + 
	    		"    <meta charset=\"utf-8\">\n" + 
	    		"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + 
	    		"    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.min.css\" />\n" + 
	    		"    <style>\n" + 
	    		"        .jumbotron {\n" + 
	    		"            background: #ff0008;\n" + 
	    		"            color: #fff;\n" + 
	    		"			  padding: 15px;"+
	    		"        }\n" + 
	    		"\n" +
				".result, .description{\n" + 
				"           min-width: 480px;\n" + 
				"           max-width: 480px;\n" + 
				"           white-space: normal;\n" + 
				"           word-break: normal;\n" + 
				"       }"+
	    		"    </style>\n" + 
	    		"</head>\n" + 
	    		"\n" + 
	    		"<body>\n" + 
	    		"\n");
	     out.write(" <div class=\"jumbotron\">\n" + 
	     		"        <div class=\"container text-center\">\n" + 
	     		"            <h1>Automation Test Results</h1>\n" + 
	     		//"            <p>Additional description can go here</p>\n" + 
	     		"        </div>\n" + 
	     		"    </div>");
	     
	     out.write("<div class=\"container-fluid bg-3 text-center\">");
	     out.write("<div class=\"row\">"); 
	     out.write("<div class=\"col-sm-3\">\n" + 
	     		"                <div class=\"card text-white bg-info mb-4\">\n" + 
	     		"                    <div class=\"card-header\">Environment - Release</div>\n" + 
	     		"                    <div class=\"card-body\">\n" + 
	     		"                        <h5 class=\"card-title\">"+ ENVIRONMENT +" - "+rel+"</h5>\n" + 
	     		"                    </div>\n" + 
	     		"                </div>\n" + 
	     		"            </div>");
			out.write("<div class=\"col-sm-3\">\n" + 
					"                <div class=\"card text-white bg-info mb-4\">\n" + 
					"                    <div class=\"card-header\">Browser</div>\n" + 
					"                    <div class=\"card-body\">\n" + 
					"                        <h5 class=\"card-title\">"+BROWSER+"</h5>\n" + 
					"                    </div>\n" + 
					"                </div>\n" + 
					"            </div>");
		out.write("<div class=\"col-sm-3\">               \n" + 
				"               <div class=\"card text-white bg-info mb-4\">\n" + 
				"                    <div class=\"card-header\">StartTime</div>\n" + 
				"                    <div class=\"card-body\">\n" + 
				"                        <h5 class=\"card-title\">"+ testStartTime +"</h5>\n" + 
				"                    </div>\n" + 
				"                </div>\n" + 
				"            </div>\n" + 
				"            <div class=\"col-sm-3\">\n" + 
				"              <div class=\"card text-white bg-info mb-4\">\n" + 
				"                    <div class=\"card-header\">EndTime</div>\n" + 
				"                    <div class=\"card-body\">\n" + 
				"                        <h5 class=\"card-title\">END_TIME</h5>\n" + 
				"                    </div>\n" + 
				"                </div>\n" + 
				"            </div>");
		out.write("</div>");
		out.write("<div class=\"row\">");
		out.write(" <div class=\"col-sm-3\">\n" + 
			"                <div class=\"card text-white bg-info mb-4\">\n" + 
			"                    <div class=\"card-header\">Total Test Cases Run</div>\n" + 
			"                    <div class=\"card-body\">\n" + 
			"                        <h5 class=\"card-title\">Total_Count</h5>\n" + 
			"                    </div>\n" + 
			"                </div>\n" + 
				"        </div>\n" + 
				"        <div class=\"col-sm-3\">\n" + 
				"                <div class=\"card text-white bg-success mb-4\">\n" + 
				"                    <div class=\"card-header\">Passed</div>\n" + 
				"                    <div class=\"card-body\">\n" + 
				"                        <h5 class=\"card-title\">Total_Pass</h5>                        \n" + 
				"                    </div>\n" + 
				"                </div>\n" + 
				"            </div>\n" + 
				"            <div class=\"col-sm-3\">\n" + 
				"                <div class=\"card text-white bg-danger mb-4\">\n" + 
				"                    <div class=\"card-header\">Failed</div>\n" + 
				"                    <div class=\"card-body\">\n" + 
				"                        <h5 class=\"card-title\">Total_Fail</h5>                        \n" + 
				"                    </div>\n" + 
				"                </div>\n" + 
				"            </div>\n" + 
				"            <div class=\"col-sm-3\">\n" + 
				"                <div class=\"card text-white bg-warning mb-4\">\n" + 
				"                    <div class=\"card-header\">Skipped</div>\n" + 
				"                    <div class=\"card-body\">\n" + 
				"                        <h5 class=\"card-title\">Total_Skipped</h5>                        \n" + 
				"                    </div>\n" + 
				"                </div>\n" + 
				"            </div>"+ 
				"        </div>\n" + 
				"    </div>");
	    out.close();
	    }catch (Exception e){//Catch exception if any
	      System.err.println("Error: " + e.getMessage());
	    }finally{
	    	
		    fstream=null;
		    out=null;
	    }
	  }
	


    public void createTestCaseSummaryReport(String suiteName){

	    FileWriter fstream =null;
		BufferedWriter out =null;
		currentSuiteName = suiteName.replaceAll(" ", "_");
		tcid=1;
	    try{
		    fstream = new FileWriter(indexResultFilename,true);
		    out = new BufferedWriter(fstream);
	          	
		    
		    out.write("<div class=\"container-fluid text-center\">\n" + 
			 		"        <div class=\"row\">\n" + 
			 		"            <div class=\"col-sm-12\">\n" + 
			 		"                <div class=\"card bg-dark text-white mb-12\">\n" + 
			 		"                    <div class=\"card-body\">\n" + 
			 		"                        <h5 class=\"card-title\">"+suiteName+" Report </h5>\n" + 
			 		"                    </div>\n" + 
			 		"                </div>\n" + 
			 		"            </div>\n" + 
			 		"        </div>\n" + 
			 		"        <div class=\"table-responsive-sm table-responsive-md table-responsive-lg\">\n" + 
			 		"            <table class=\"table table-hover table-striped table-bordered text-nowrap\">");
		    
		    out.write("<thead class='bg-secondary text-white'>" + 
	        		"                    <tr>" + 
	        		"                        <th scope='col'>Test Script#</th>" + 
	        		"                        <th scope='col'>Test Case Name</th>" + 
	        		"                        <th scope='col'>Description</th>" + 
	        		"                        <th scope='col'>Status</th>" + 
	        		"                        <th scope='col'>Run Start Time</th>" + 
	        		"                        <th scope='col'>Run End Time</th>" + 
	        		"                    </tr>" + 
	        		"                </thead>");
	        out.write("<tbody>");
	        out.close();
		    }catch(Exception e){
			      System.err.println("Error: " + e.getMessage());
		    }finally{
		    	
			    fstream=null;
			    out=null;
		    }
	}
    
  //Summary method
  	public void addTestCaseSummary(String testCaseName, String suitDesc, String testCaseStartTime, String testCaseEndTime, String status)  {
  		try {
  		FileWriter fstream =null;

  		fstream = new FileWriter(indexResultFilename,true);
  		String dtTime= now("dd.MMMMM.yyyyhh.mm.ss");
  		
  		BufferedWriter out =null;
  		 out = new BufferedWriter(fstream);
  		 out.write("<tr><th scope='row'>"+scriptNumber+"</th>"); 
  		 if(status.equalsIgnoreCase(ReportConstants.SKIP)) {
  			 out.write("<td>"+testCaseName+"</td>");
  		 }else {
  			 out.write("<td><a href='file:///"+testStepFileName+"' target=_blank>"+testCaseName+"</a></td>");
  		 }
  		 
  		 out.write("<td>"+suitDesc+"</td>" );
  			 
  			 		if(status.startsWith(ReportConstants.PASS)) {
  			 			 out.write("<td class='bg-success'>"+status+"</td>"); 
  			 		}else if(status.startsWith(ReportConstants.FAIL)) {
  			 			out.write("<td class='bg-danger'>"+status+"</td>");
  			 		}else if(status.equalsIgnoreCase("Skipped") || status.equalsIgnoreCase("Skip")) {
  			 			out.write("<td class='bg-warning'>"+status+"</td>");
  			 		} else {
  			 			out.write("<td class='bg-warning'>"+status+"</td>");
  			 		}
  			 		out.write("<td>"+testCaseStartTime+"</td>"); 
  			 		out.write("<td>"+testCaseEndTime+"</td>"); 
  			out.write("</tr>");
  			scriptNumber++;
     	 	 out.close();
  		}catch (IOException e) {
  			System.out.println(""+e);
  		}
  	}
  	

	public void createTestStepReport(String currentSuiteName, String testCaseName, String desc) {
		sNo = 1;
		BufferedWriter out=null;
		String dtTime= now("dd.MMMMM.yyyyhh.mm.ss");
		try {
				testStepFileName = currentDir+File.separator+currentSuiteName+"_"+testCaseName.replaceAll(" ", "_")+"_"+dtTime+".html";
			    File f = new File(testStepFileName);
			    f.createNewFile();
			    testStepFileStream = new FileWriter(testStepFileName);
				out = new BufferedWriter(testStepFileStream);
				out.write("<!DOCTYPE html>\n" + 
						"<html lang=\"en\">\n" + 
						"\n" + 
						"<head>\n" + 
						"    <title>Bootstrap Example</title>\n" + 
						"    <meta charset=\"utf-8\">\n" + 
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + 
						"    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.min.css\" />\n" + 
						"    <style>\n" + 
						"        .jumbotron {\n" + 
						"            background: #ff0008;\n" + 
			            "			 padding: 15px;"+
						"            color: #fff;\n" + 
						"        }\n" + 
						"\n" + 
						".result, .description{\n" + 
						"           min-width: 480px;\n" + 
						"           max-width: 480px;\n" + 
						"           white-space: normal;\n" + 
						"           word-break: normal;\n" + 
						"       }"+
						"    </style>\n" + 
						"</head>\n" + 
						"\n" + 
						"<body>\n" + 
						"\n" + 
						"    <div class=\"jumbotron\">\n" + 
						"        <div class=\"container text-center\">\n" + 
						"            <h1>Detailed Report</h1>\n" + 
						//"            <p>Additional description can go here</p>\n" + 
						"        </div>\n" + 
						"    </div>");
			 out.write("<div class=\"container-fluid text-center\">\n" + 
			 	 		"        <div class=\"row\">\n" + 
			 	 		"            <div class=\"col-sm-12\">\n" + 
			 	 		"                <div class=\"card bg-dark text-white mb-12\">\n" + 
			 	 		"                    <div class=\"card-body\">\n" + 
			 	 		"                        <h5 class=\"card-title\">"+testCaseName+"</h5>\n" + 
			 	 		"                    </div>\n" + 
			 	 		"                </div>\n" + 
			 	 		"            </div>\n" + 
			 	 		"        </div>"); 
			out.close();

		}catch (Exception e) {
			
		}
		createTestStepReportHeader();
		writeTestStepInHtml();
	}
	

	public void addRequestReportData(String service, String method, String url, String request, String response, String description) {
		//createRequestReportHeader();
		sNo = 1;
		BufferedWriter out=null;
		try {
			testStepFileStream = new FileWriter(testStepFileName,true);
			out = new BufferedWriter(testStepFileStream);
		 	 out.write("<tr> ");
		 	 	out.write("<td align=center width=10%  align=center ><FONT  FACE=Arial SIZE=1></td>");	
		 	 	out.write("<td align=center width=10%  align=center ><FONT  FACE=Arial SIZE=1>"+description+"</td>");
		 	 	out.write("<td align=center width=10%  align=left ><FONT  FACE=Arial SIZE=1><b>Service&nbsp;:</b>"+service+"<br><br><b>Method&nbsp;:</b>"+method+"<br><br><b>URL&nbsp;:</b>"+url+"</td>");
		        out.write("<td align=center  width=20% align=center ><FONT  FACE=Arial SIZE=1>"+request+"</td>");
		        out.write("<td align=center  width=20% align=center ><FONT  FACE=Arial SIZE=1>"+response+"</td>");
		        out.write("<td align=center width=20% align=center ><FONT COLOR=#000000 FACE=Arial SIZE=2><b>&nbsp;</b></td>");
		        out.write("<td align=center width=20% align=center ><FONT COLOR=#000000 FACE=Arial SIZE=2><b>&nbsp;</b></td>");
		 		out.write("<td align=center width=20% align=center ><FONT COLOR=#000000 FACE=Arial SIZE=2><b>&nbsp;</b></td>");
		 		out.write("<td align=center width=20% align=center ><FONT COLOR=#000000 FACE=Arial SIZE=2><b>&nbsp;</b></td>");

		 	 out.write("</tr><br>");
		 	//out.write("</table>");
		 	out.close();

		}catch (Exception e) {
			
		}
		//createTestStepReportHeader();
	}

	
	public void createTestStepReportHeader() {
		BufferedWriter out=null;
		try {
			testStepFileStream = new FileWriter(testStepFileName,true);
			out = new BufferedWriter(testStepFileStream);
		 	
		 	 out.write("<div class=\"table-responsive\">\n" + 
		 	 		"            <table class=\"table table-hover table-striped table-bordered text-nowrap\">\n" + 
		 	 		"                <thead class=\"bg-secondary text-white\">\n" + 
		 	 		"                    <tr>\n" + 
		 	 		"                        <th scope=\"col\">Step#</th>\n" + 
		 	 		"                        <th scope=\"col\">Description</th>\n" + 
		 	 		"                        <th scope=\"col\">Method/URL</th>\n" + 
		 	 		"                        <th scope=\"col\">Request Data</th>\n" + 
		 	 		"                        <th scope=\"col\">Response</th>\n" + 
		 	 		"                        <th scope=\"col\">Element</th>\n" + 
		 	 		"                        <th scope=\"col\">Expected</th>\n" + 
		 	 		"                        <th scope=\"col\">Actual</th>\n" + 
		 	 		"                        <th scope=\"col\">Result</th>\n" + 
		 	 		"                    </tr>\n" + 
		 	 		"                </thead>\n" + 
		 	 		"                <tbody>");
		 	out.close();
		}catch(Exception e) {
			
		}
	}
		//String testCaseId,String description,String currentKeyword, String request, String expected, String response, 
	//String result, String service, String method,String url
	public void writeTestStepInHtml(){
		BufferedWriter out=null;
		try {
			testStepFileStream = new FileWriter(testStepFileName,true);
			out = new BufferedWriter(testStepFileStream);
			
			for(int i=0; i< methodLst.size(); i++) {
				/* if(!serviceLst.get(i).equals("")) {
					 out.write("<tr>");
					 	out.write("<td align=center width=5% colspan=10><FONT COLOR=#000000 FACE=Arial SIZE=1><b>"+serviceLst.get(i)+"</b></td>");
					 out.write("</tr>");
	 			 }*/
				 if(!blankRow.get(i).equalsIgnoreCase("")) {
					 out.write("<tr><td colspan=10 class='bg-info text-left'>"+blankRow.get(i)+"</td></tr><tr>");
				 }
				if(!methodLst.get(i).equalsIgnoreCase("")) {
					 out.write("<tr> ");
						 out.write("<th scope=\"row\">"+sNo+"</th>");
			 			 out.write("<td class='description'>"+descriptionLst.get(i)+"</td>");
				 	 	 out.write("<td class=\"text-left\">"+methodLst.get(i)+"</td>");
				         out.write("<td class=\"text-left\"><pre class=\"pre-scrollable\">"+requestLst.get(i)+"</pre></td>");
				         out.write("<td class=\"text-left\"><pre class=\"pre-scrollable\">"+responseLst.get(i)+"</pre></td>");
			 			 out.write("<td >"+locatorLst.get(i)+"</td>");
			 			 out.write("<td >"+expectedLst.get(i)+"</td>");
			 			 out.write("<td >"+actualValueLst.get(i)+"</td>");//request
			 			if(resultLst.get(i).startsWith(ReportConstants.PASS))
			 			     out.write("<td class=\"bg-success result\">"+resultLst.get(i)+"</b></td>");
			 			else if(resultLst.get(i).startsWith(ReportConstants.FAIL))
			 			  	 out.write("<td class=\"bg-danger result\">"+resultLst.get(i)+"<br><a href='"+screenShotLst.get(i)+"' target='_blank'><img src='"+screenShotLst.get(i)+"' scale=0 class='img-thumbnail rounded float-left' width=200px height=200px></a></td>");
			 			else
			 				out.write("<td>&nbsp;</td>");
		 			 out.write("</tr>");
		 			 sNo++;
				} 
	 	  }
			 out.close();
		}catch (Exception e) {
			
		}
		
		
		descriptionLst 		= new ArrayList<String>();
		locatorLst 			= new ArrayList<String>();
		requestLst 			= new ArrayList<String>();
		expectedLst 		= new ArrayList<String>();
		responseLst 		= new ArrayList<String>();
		resultLst 			= new ArrayList<String>();
		actualValueLst			= new ArrayList<String>();
		methodLst			= new ArrayList<String>();
		blankRow			= new ArrayList<String>();
		newTest				=false;
		
  }
	
	public void addTestStepWeb(String description, String method, String locator,String request, String expected, String acString, String result, String screenShot) {
		addTestStep(description, method, request, "", expected,locator, acString, result, screenShot);
	}
	
	public void createBlankHeader(String heading) {
		blankRow.add(heading);
		descriptionLst.add("");
 		actualValueLst.add("");
		requestLst.add("");
		expectedLst.add("");
		responseLst.add("");
		resultLst.add("");
		methodLst.add("");
		locatorLst.add("");
		screenShotLst.add("");
	}
	
	public void addTestStep(String description, String method, String request, String response, 
			String expected,String locator, String actualValue, String result, String screenShot){
			
 		descriptionLst.add(description);
 		actualValueLst.add(actualValue);
		requestLst.add(request);
		expectedLst.add(expected);
		responseLst.add(response);
		resultLst.add(result);
		methodLst.add(method);
		locatorLst.add(locator);
		blankRow.add("");
		screenShotLst.add(screenShot);
	}
	
	public void createRequestReportHeader() {
		sNo = 1;
		BufferedWriter out=null;
		try {
			testStepFileStream = new FileWriter(testStepFileName,true);
			out = new BufferedWriter(testStepFileStream);
	 		 out.write("<table  border=1 cellspacing=1    cellpadding=1 width=90% align='center'>");
		 	 out.write("<tr> ");
		 	 	out.write("<td align=center width=10%  align=center bgcolor=#70cef6><FONT  FACE=Arial SIZE=2><b>Service</b></td>");
		 	 	out.write("<td align=center width=10%  align=center bgcolor=#70cef6><FONT FACE=Arial SIZE=2><b>Method/URL</b></td>");
		        out.write("<td align=center  width=40% align=center bgcolor=#70cef6><FONT FACE=Arial SIZE=2><b>Request</b></td>");
		        out.write("<td align=center  width=40% align=center bgcolor=#70cef6><FONT FACE=Arial SIZE=2><b>Response</b></td>");
		 	 out.write("</tr>");
		 	out.close();

		}catch (Exception e) {
			
		}
		
	}
	
    public  void endSuite(){
    	 FileWriter fstream =null;
 		BufferedWriter out =null;
 		
 	    try{
 	    fstream = new FileWriter(indexResultFilename,true);
 	  	out = new BufferedWriter(fstream);
        out.write("</tbody>\n" + 
        		"            </table>\n" + 
        		"        </div>\n" + 
        		"    </div>\n" + 
        		"\n" + 
        		"</body>\n" + 
        		"\n" + 
        		"</html>");
        out.close();
 	    }catch(Exception e){
		      System.err.println("Error: " + e.getMessage());
	    }finally{
	    	
		    fstream=null;
		    out=null;
	    }

    }
	
	
	

	public void updateEndTime(String endTime) {
		StringBuffer buf = new StringBuffer();
		try{
		    // Open the file that is the first 
		    // command line parameter
		    FileInputStream fstream = new FileInputStream(indexResultFilename);
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strLine;
		    
		    //Read File Line By Line
		    
		    while ((strLine = br.readLine()) != null)   {
		    	
			     if(strLine.indexOf("END_TIME") !=-1){
			    	 strLine=strLine.replace("END_TIME", endTime);
			     }
		       buf.append(strLine);
		    }
		  //Close the input stream
		    in.close();
		    System.out.println(buf);
		    FileOutputStream fos=new FileOutputStream(indexResultFilename);
			 DataOutputStream   output = new DataOutputStream (fos);	 
	    	 output.writeBytes(buf.toString());
	    	 fos.close();
		    
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
		
	}

	
	public void updateExecutionReport(ArrayList arrList) {
		
		int fail = Collections.frequency(arrList, ReportConstants.FAIL);
		int pass  = Collections.frequency(arrList, ReportConstants.PASS);
		int skip  = Collections.frequency(arrList, ReportConstants.SKIP);
		
		int totalCount = fail+pass; 
		
		StringBuffer buf = new StringBuffer();
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(indexResultFilename);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			// Read File Line By Line

			while ((strLine = br.readLine()) != null) {

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
				if (strLine.indexOf("FAIL_PERCENTAGE") != -1) {
					int failPer = fail * 100 / totalCount;
					strLine = strLine.replace("FAIL_PERCENTAGE",
							Integer.toString(failPer))+" %";
				}
				
				
				buf.append(strLine);
			}
			// Close the input stream
			in.close();
			System.out.println(buf);
			FileOutputStream fos = new FileOutputStream(indexResultFilename);
			DataOutputStream output = new DataOutputStream(fos);
			output.writeBytes(buf.toString());
			fos.close();

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}

 
}
