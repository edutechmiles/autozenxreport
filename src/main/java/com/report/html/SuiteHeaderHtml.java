package com.report.html;

/**
 * 
 * @author nitin.singh
 * @email nitinsingh.k2@gmail.com
 */
public class SuiteHeaderHtml {

	
	public String reportHeader(String suiteName) {
		
		if(suiteName.equals("")) {
			suiteName = "Default Suite";
		}
		
		String html = "<div class=\"container-fluid text-center\">\r\n" + 
					"        <div class=\"row\">\r\n" + 
					"        <div class=\"col-sm-12\">\r\n" + 
					"            <div class=\"card bg-dark text-white mb-12\">\r\n" + 
					"                <div class=\"card-body\">\r\n" + 
					"                    <h5 class=\"card-title\">"+suiteName+"</h5>\r\n" + 
					"                </div>\r\n" + 
					"            </div>\r\n" + 
					"        </div>\r\n" + 
					"    </div>\r\n" + 
					"</div>\r\n" +
					"<div class=\"container-fluid text-center\">\r\n" + 
					"        <div class=\"row my-header\">\r\n" + 
					"            <div class=\"col-1\">Test Script#</div>\r\n" + 
					"            <div class=\"col-2\"> Test Case Name</div>\r\n" + 
					"            <div class=\"col-4\">Description</div>\r\n" + 
					"            <div class=\"col-1\">Status</div>\r\n" + 
					"            <div class=\"col\">Run Start Time</div>\r\n" + 
					"            <div class=\"col\">Run End Time</div>\r\n" + 
					"        </div>";
		return html;
		
	}
}
