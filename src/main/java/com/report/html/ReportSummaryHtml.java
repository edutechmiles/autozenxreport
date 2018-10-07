package com.report.html;

import java.util.Map;

import com.report.ReportConstants;

public class ReportSummaryHtml {

	
	public String reportSummary(Map<String,String> configurationMap, Map<String,String> systemInfoMap) {
		
		String releaseVersion;
		String runOnMachine;
		String browser;
		String environment;
		
		if(systemInfoMap.containsKey(ReportConstants.ENVIRONMENT)) {
			environment = systemInfoMap.get(ReportConstants.ENVIRONMENT);
		}else {
			environment = "Unknown Envornment";
		}
		
		if(systemInfoMap.containsKey(ReportConstants.RELEASE_VERSION)) {
			releaseVersion = systemInfoMap.get(ReportConstants.RELEASE_VERSION);
		}else {
			releaseVersion = "Unknown Release version";
		}
		
		if(systemInfoMap.containsKey(ReportConstants.BROWSER)) {
			browser = systemInfoMap.get(ReportConstants.BROWSER);
		}else {
			browser = "Unknown Browser";
		}
		 
		
		
		
		String html = "<div class=\"container-fluid bg-3 text-center\">\r\n" + 
				"\r\n" + 
				"        <div class=\"row\">\r\n" + 
				"            <div class=\"col-sm-3\">\r\n" + 
				"                <div class=\"card text-white bg-info mb-4\">\r\n" + 
				"                    <div class=\"card-header\">Environment - Release</div>\r\n" + 
				"                    <div class=\"card-body\">\r\n" + 
				"                        <h5 class=\"card-title\">"+environment+" - "+releaseVersion+"</h5>\r\n" + 
				"                    </div>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" + 
				"            <div class=\"col-sm-3\">\r\n" + 
				"                <div class=\"card text-white bg-info mb-4\">\r\n" + 
				"                    <div class=\"card-header\">Browser</div>\r\n" + 
				"                    <div class=\"card-body\">\r\n" + 
				"                        <h5 class=\"card-title\">"+browser+"</h5>\r\n" + 
				"                    </div>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" + 
				"            <div class=\"col-sm-3\">\r\n" + 
				"                <div class=\"card text-white bg-info mb-4\">\r\n" + 
				"                    <div class=\"card-header\">StartTime</div>\r\n" + 
				"                    <div class=\"card-body\">\r\n" + 
				"                        <h5 class=\"card-title\">START_TIME</h5>\r\n" + 
				"                    </div>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" + 
				"            <div class=\"col-sm-3\">\r\n" + 
				"                <div class=\"card text-white bg-info mb-4\">\r\n" + 
				"                    <div class=\"card-header\">EndTime</div>\r\n" + 
				"                    <div class=\"card-body\">\r\n" + 
				"                        <h5 class=\"card-title\">END_TIME</h5>\r\n" + 
				"                    </div>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" + 
				"        </div>\r\n" + 
				"        <div class=\"row\">\r\n" + 
				"            <div class=\"col-sm-3\">\r\n" + 
				"                <div class=\"card text-white bg-info mb-4\">\r\n" + 
				" <div class=\"card-header\">Total Test Cases Run - Total steps</div>\n" + 
							"                    <div class=\"card-body\">\n" + 
												"<div style=\"\n" + 
										"				   width: 50%;\n" + 
										"				   float: left;\n" + 
										"				\"><h5 class=\"card-title\">Total_Count</h5></div>\n" + 
										"				<div style=\"\n" + 
										"				   width: 50%;\n" + 
										"				   float: left;\n" + 
										"				\"><h5 class=\"card-title\">TotalStepsCount</h5></div>\n" + 
										
							"                    </div>\n" + 
							"                </div>\n" +  
				"            </div>\r\n" + 
				"            <div class=\"col-sm-3\">\r\n" + 
				"                <div class=\"card text-white bg-success mb-4\">\r\n" + 
				"                    <div class=\"card-header\">Passed - Passed steps</div>\r\n" + 
				"					<div class=\"card-body\">\n" + 
						"				<div style=\"\n" + 
						"				   width: 50%;\n" + 
						"				   float: left;\n" + 
						"				\"><h5 class=\"card-title\">Total_Pass</h5></div>\n" + 
						"				<div style=\"\n" + 
						"				   width: 50%;\n" + 
						"				   float: left;\n" + 
						"				\"><h5 class=\"card-title\">TotalPassSteps</h5></div>\n" + 
						
			"                    </div>\n" + 
						"                    </div>\n" + 
						"                </div>\n" + 
				"            <div class=\"col-sm-3\">\r\n" + 
				"                <div class=\"card text-white bg-danger mb-4\">\r\n" + 
				"                    <div class=\"card-header\">Failed - Failed steps</div>\r\n" + 
				"					 <div class=\"card-body\">\n" + 
							"					<div style=\"\n" + 
							"				   width: 50%;\n" + 
							"				   float: left;\n" + 
							"				\"><h5 class=\"card-title\">Total_Fail</h5></div>\n" + 
							"				<div style=\"\n" + 
							"				   width: 50%;\n" + 
							"				   float: left;\n" + 
							"				\"><h5 class=\"card-title\">TotalFailSteps</h5></div>\n" + 
							
							"                    </div>\n" + 
							"                </div>\n" + 
				"            </div>\r\n" + 
				"            <div class=\"col-sm-3\">\r\n" + 
				"                <div class=\"card text-white bg-warning mb-4\">\r\n" + 
				"                    <div class=\"card-header\">Skipped - Skipped steps</div>\r\n" + 
				"<div class=\"card-body\">\n" + 
						"<div style=\"\n" + 
						"				   width: 50%;\n" + 
						"				   float: left;\n" + 
						"				\"><h5 class=\"card-title\">Total_Skipped</h5></div>\n" + 
						"				<div style=\"\n" + 
						"				   width: 50%;\n" + 
						"				   float: left;\n" + 
						"				\"><h5 class=\"card-title\">TotalSkipSteps</h5></div>\n" + 
						
						"                    </div>\n" +	 
						"                </div>\n" +                      
				"            </div>\r\n" + 
				"        </div>\r\n" + 
				"    </div>";
		
		return html;
		
	}
}
