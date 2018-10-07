package com.report.html;

import com.report.ReportConstants;

public class ReportTestStepHeaderHtml {

	
	public String testStepReportHeaderForAPIAndUi(String heading) {
		
		String html = "<div class=\"modal fade\" id=\"myModal\">\r\n" + 
				"                <div class=\"modal-dialog modal-dialog-centered\">\r\n" + 
				"                    <div class=\"modal-content\">\r\n" + 
				"                        <!-- Modal Header -->\r\n" + 
				"                        <div class=\"modal-header\">\r\n" + 
				"                            <h4 class=\"modal-title\">"+heading+"</h4>\r\n" + 
				"                            <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n" + 
				"                        </div>\r\n" + 
				"                        <!-- Modal body -->\r\n" + 
				"                        <div class=\"modal-body\">\r\n" + 
				"                            <div class=\"table-responsive\">\r\n" +
				"                                <table class=\"table table-hover table-striped table-bordered text-nowrap\">\r\n" + 
				"                                    <thead class=\"bg-secondary text-white\">\r\n" + 
				"                                        <tr>\r\n" + 
				"                                            <th scope=\"col\">Step#</th>\r\n" + 
				"                                            <th scope=\"col\">Description</th>\r\n" + 
				"                                            <th scope=\"col\">Method/URL</th>\r\n" + 
				"                                            <th scope=\"col\">Request</th>\r\n" + 
				"                                            <th scope=\"col\">Response</th>\r\n" + 
				"                                            <th scope=\"col\">Json Node</th>\r\n" + 
				"                                            <th scope=\"col\">Expected</th>\r\n" + 
				"                                            <th scope=\"col\">Actual</th>\r\n" + 
				"                                            <th scope=\"col\">Result</th>\r\n" + 
				"                                        </tr>\r\n" + 
				"                                    </thead>\r\n" + 
				"                                    <tbody>";
		
		return html;
		
	}

	
	public String testStepReportHeaderForWeb(String heading) {
		
		String html = "<div class=\"modal fade\" id=\"myModal\">\r\n" + 
				"                <div class=\"modal-dialog modal-dialog-centered\">\r\n" + 
				"                    <div class=\"modal-content\">\r\n" + 
				"                        <!-- Modal Header -->\r\n" + 
				"                        <div class=\"modal-header\">\r\n" + 
				"                            <h4 class=\"modal-title\">"+heading+"</h4>\r\n" + 
				"                            <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n" + 
				"                        </div>\r\n" + 
				"                        <!-- Modal body -->\r\n" + 
				"                        <div class=\"modal-body\">\r\n" + 
				"                            <div class=\"table-responsive\">\r\n" +
				"                                <table class=\"table table-hover table-striped table-bordered text-nowrap\">\r\n" + 
				"                                    <thead class=\"bg-secondary text-white\">\r\n" + 
				"                                        <tr>\r\n" + 
				"                                            <th scope=\"col\">Step#</th>\r\n" + 
				"                                            <th scope=\"col\">Description</th>\r\n" + 
				"                                            <th scope=\"col\">Expected</th>\r\n" + 
				"                                            <th scope=\"col\">Actual</th>\r\n" + 
				"                                            <th scope=\"col\">Result</th>\r\n" + 
				"                                        </tr>\r\n" + 
				"                                    </thead>\r\n" + 
				"                                    <tbody>";
		
		return html;
		
	}
	public String createStepResultRow(String sNo, String description, String method, String request, 
			String response, String jsonNode, String expected, String actual, String result) {
		
		 
		String html = "	<tr>\r\n" + 
				"		<th scope=\"row\">"+sNo+"</th>\r\n" + 
				"		<td class=\"description\">"+description+"</td>\r\n" + 
				"		<td class=\"text-left\">\r\n" + 
				"			<ul class=\"list-unstyled\">\r\n" + 
				"				<li>\r\n" + 
				"					<strong>Service :</strong>Employees\r\n" + 
				"				</li>\r\n" + 
				"				<li>\r\n" + 
				"					<strong>Method :</strong>POST\r\n" + 
				"				</li>\r\n" + 
				"				<li>\r\n" + 
				"					<strong>URL :</strong>employees\r\n" + 
				"				</li>\r\n" + 
				"			</ul>\r\n" + 
				"		</td>\r\n" + 
				"		<td class=\"text-left\">\r\n" + 
				"			<pre class=\"pre-scrollable\">"+request+"\r\n" + 
				"</pre>\r\n" + 
				"		</td>\r\n" + 
				"		<td class=\"text-left\">\r\n" + 
				"			<pre class=\"pre-scrollable\">"+response+"\r\n" + 
				"</pre>\r\n" + 
				"		</td>\r\n" + 
				"		<td>"+jsonNode+"</td>\r\n" + 
				"		<td>"+expected+"</td>\r\n" + 
				"		<td>"+actual+"</td>\r\n" ;
				if(result.startsWith(ReportConstants.PASS)) {
					html+="<td class='bg-success'>"+result+"</td>"; 
			 	}else if(result.startsWith(ReportConstants.FAIL)) {
			 		html+="<td class='bg-danger'>"+result+"</td>";
			 	}else if(result.equalsIgnoreCase("Skipped") || result.equalsIgnoreCase("Skip")) {
			 		html+="<td class='bg-warning'>"+result+"</td>";
			 	} else {
			 		html+="<td class='bg-warning'>"+result+"</td>";
			 	}
				
		html+= "	</tr>\r\n" + 
				"";
		return html;
		
	}
	
	public String createStepResultRowForWeb(String sNo, String description, String expected, String actual, String result) {
		
		 
		String html = "	<tr>\r\n" + 
				"		<th scope=\"row\">"+sNo+"</th>\r\n" + 
				"		<td class=\"description\">"+description+"</td>\r\n" + 
				"		<td>"+expected+"</td>\r\n" + 
				"		<td>"+actual+"</td>\r\n" ;
				if(result.startsWith(ReportConstants.PASS)) {
					html+="<td class='bg-success'>"+result+"</td>"; 
			 	}else if(result.startsWith(ReportConstants.FAIL)) {
			 		html+="<td class='bg-danger'>"+result+"</td>";
			 	}else if(result.equalsIgnoreCase("Skipped") || result.equalsIgnoreCase("Skip")) {
			 		html+="<td class='bg-warning'>"+result+"</td>";
			 	} else {
			 		html+="<td class='bg-warning'>"+result+"</td>";
			 	}
				
		html+= "</tr>\r\n" ; 
				
		return html;
		
	}
	
	public String testStepReportFooter() {
		
		 
		String html = "</tbody>\r\n" + 
				"                                </table>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"\r\n" + 
				"                        <!-- Modal footer -->\r\n" + 
				"                        <div class=\"modal-footer\">\r\n" + 
				"                            <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\r\n" + 
				"                        </div>\r\n" + 
				"\r\n" + 
				"                    </div>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" + 
				"        </div>";
		return html;
		
	}
}
