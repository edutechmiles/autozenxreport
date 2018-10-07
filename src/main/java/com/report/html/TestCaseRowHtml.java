package com.report.html;

import com.report.ReportConstants;

/**
 * 
 * @author nitin.singh
 * @email nitinsingh.k2@gmail.com
 */
public class TestCaseRowHtml {

	
	public String createResultRow(String testCaseId, String testCaseName, String description, String result, 
			String startTime, String endTime) {
		
		 
		String html = "<div class=\"table-row\">\r\n" + 
				"            <!--Button to open modal popup -->\r\n" + 
				"            <a data-toggle=\"modal\" data-target=\"#myModal\">\r\n" + 
				"                <div class=\"row parent-row\">\r\n" + 
				"                    <div class=\"col-1\">"+testCaseId+"</div>\r\n" + 
				"                    <div class=\"col-2\">"+testCaseName+"</div>\r\n" + 
				"                    <div class=\"col-4 \">"+description+"</div>\r\n" ;

									if(result.startsWith(ReportConstants.PASS)) {
										html+= "<div class='col-1 bg-success'>"+result+"</div>\r\n" ; 
									}else if(result.startsWith(ReportConstants.FAIL)) {
										html+= "<div class='col-1 bg-danger'>"+result+"</div>\r\n" ;
									}else if(result.equalsIgnoreCase("Skipped") || result.equalsIgnoreCase("Skip")) {
										html+= "<div class='col-1 bg-warning'>"+result+"</div>\r\n" ;
									} else {
										html+= "<div class='col-1 bg-warning'>"+result+"</div>\r\n" ;
									}
				
				html+= 	"             <div class=\"col\">"+startTime+"</div>\r\n" + 
				"                    <div class=\"col\">"+endTime+"</div>\r\n" + 
				"                </div>\r\n" + 
				"            </a>";
		return html;
		
	}
	
	public String createResultRow1(String testCaseId, String testCaseName, String description, String result, 
			String startTime, String endTime) {
		
		String html = 
				"            <!--Button to open modal popup -->\r\n" + 
				"            <a data-toggle=\"modal\" data-target=\"#myModal\">\r\n" + 
				"                <div class=\"row parent-row\">\r\n" ;
		 html = "<tr class=\"row parent-row\"><th scope='row'>2</th>"; 
		 if(result.equalsIgnoreCase(ReportConstants.SKIP)) {
			 html+= "<td>"+testCaseName+"</td>";
		 }else {
			 html+= "<td><a href='file:///testStepFileName target=_blank>"+testCaseName+"</a></td>";
		 }
		 
		 html+= "<td class='description'>suitDesc</td>" ;
			 
			 		if(result.startsWith(ReportConstants.PASS)) {
			 			html+= "<td class='bg-success'>"+result+"</td>"; 
			 		}else if(result.startsWith(ReportConstants.FAIL)) {
			 			html+= "<td class='bg-danger'>"+result+"</td>";
			 		}else if(result.equalsIgnoreCase("Skipped") || result.equalsIgnoreCase("Skip")) {
			 			html+= "<td class='bg-warning'>"+result+"</td>";
			 		} else {
			 			html+= "<td class='bg-warning'>"+result+"</td>";
			 		}
			 		html+= "<td>testCaseStartTime</td>"; 
			 		html+= "<td>testCaseEndTime</td>"; 
			 	html+= "</tr>";
			 	html+= 	"             <div class=\"col\">"+startTime+"</div>\r\n" + 
						"                    <div class=\"col\">"+endTime+"</div>\r\n" + 
						"                </div>\r\n" + 
						"            </a>";
			 	
		return html;	 	
	}
}
