package com.report.html;

import java.util.Map;

import com.report.ReportConstants;

public class ReportHeaderHtml {

	
	public String reportHeader(Map<String,String> configurationMap) {
		
		String title = "Automation Result - "+ReportConstants.ORG_TAG_LINE;
		
		if(configurationMap.containsKey("reportTitle")){
			title = configurationMap.get("reportTitle")+" "+ReportConstants.ORG_TAG_LINE;
		}
		
		
		String html = "<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"\r\n" + 
				"<head>\r\n"+
				"    <title>"+title+"</title>\r\n" + 
				"    <meta charset=\"utf-8\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.min.css\" />\r\n" + 
				"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>    \r\n" + 
				"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js\"></script>\r\n" + 
				"    <style>\r\n" + 
				"        .jumbotron {\r\n" + 
				"            background: #ff0008;\r\n" + 
				"            color: #fff;\r\n" + 
				"            padding: 15px;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        .my-header {\r\n" + 
				"            background: #6c757d;\r\n" + 
				"            color: white;\r\n" + 
				"            font-weight: bold;\r\n" + 
				"            vertical-align: middle;\r\n" + 
				"            border-radius: 5px;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        .my-header,\r\n" + 
				"        .parent-row {\r\n" + 
				"            margin: 0;\r\n" + 
				"            padding: 15px;\r\n" + 
				"            border-bottom: 1px solid #000;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        .table-row:nth-last-of-type(even) {\r\n" + 
				"            background: #f2f2f2;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        .table-row:nth-last-of-type(odd) {\r\n" + 
				"            background: #fff;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        .table-row:hover {\r\n" + 
				"            background: #DDDDDD;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        .table-row>a {\r\n" + 
				"            color: #000;\r\n" + 
				"            cursor: pointer;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        .table-row>a:hover {\r\n" + 
				"            color: #000;\r\n" + 
				"            text-decoration: none;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        .modal-dialog {\r\n" + 
				"            width: 100%;\r\n" + 
				"            max-width: 100%;\r\n" + 
				"        }\r\n" + 
				".result, .description{\r\n" + 
				"           min-width: 480px;\r\n" + 
				"           max-width: 480px;\r\n" + 
				"           white-space: normal;\r\n" + 
				"           word-break: normal;\r\n" + 
				"       }"+
				"    </style>\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body>\r\n" + 
				"\r\n" + 
				"    <div class=\"jumbotron\">\r\n" + 
				"        <div class=\"container text-center\">\r\n" + 
				"            <h1>"+configurationMap.get("reportTitle")+"</h1>\r\n" ;
							if(configurationMap.containsKey("reportDescription") && !configurationMap.get("reportDescription").equals("")) {
								html+="<p>"+configurationMap.get("reportDescription")+"</p>\r\n";	
							}
				             
				html+="</div></div>";
		
		return html;
		
	}
}
