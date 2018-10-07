package com.report.html;

import java.util.ArrayList;
import java.util.Map;

import com.report.ReportConstants;

public class ReportStepsDetails {

	public  ArrayList<String> testCaseIdList	=	new ArrayList<String>();
	public  ArrayList<String> descriptionList=	new ArrayList<String>();
	public  ArrayList<String> actualValueList=	new ArrayList<String>();
	public  ArrayList<String> expectedList	=	new ArrayList<String>();
	public  ArrayList<String> resultList 	=	new ArrayList<String>();
	public  ArrayList<String> screenShotList	=	new ArrayList<String>();
	public  ArrayList<String> stepStartTimeLst	=	new ArrayList<String>();
	public  ArrayList<String> stepEndTimeLst	=	new ArrayList<String>();
	
	
	public void addStepDetails(String testId, String description, String result, String expected, String actual, String screenShot
			, String stepStartTime, String stepEndTime) {
		
		testCaseIdList.add(testId);
		descriptionList.add(description);
		actualValueList.add(actual);
		expectedList.add(expected);
		resultList.add(result);
		screenShotList.add(screenShot);
		stepStartTimeLst.add(stepStartTime);
		stepEndTimeLst.add(stepEndTime);
	}
	
	
	public void addStepDetails(String testId, String description, String result, String expected, String actual
			, String stepStartTime, String stepEndTime) {
		//InitClass.now("dd.MMMMM.yyyy hh.mm.ss")
		
		testCaseIdList.add(testId);
		descriptionList.add(description);
		actualValueList.add(actual);
		expectedList.add(expected);
		resultList.add(result);
		stepStartTimeLst.add(stepStartTime);
		stepEndTimeLst.add(stepEndTime);
		
	}


	public ArrayList<String> getTestCaseIdList() {
		return testCaseIdList;
	}


	public void setTestCaseIdList(ArrayList<String> testCaseIdList) {
		this.testCaseIdList = testCaseIdList;
	}


	public ArrayList<String> getDescriptionList() {
		return descriptionList;
	}


	public void setDescriptionList(ArrayList<String> descriptionList) {
		this.descriptionList = descriptionList;
	}


	public ArrayList<String> getActualValueList() {
		return actualValueList;
	}


	public void setActualValueList(ArrayList<String> actualValueList) {
		this.actualValueList = actualValueList;
	}


	public ArrayList<String> getExpectedList() {
		return expectedList;
	}


	public void setExpectedList(ArrayList<String> expectedList) {
		this.expectedList = expectedList;
	}


	public ArrayList<String> getResultList() {
		return resultList;
	}


	public void setResultList(ArrayList<String> resultList) {
		this.resultList = resultList;
	}


	public ArrayList<String> getScreenShotList() {
		return screenShotList;
	}


	public void setScreenShotList(ArrayList<String> screenShotList) {
		this.screenShotList = screenShotList;
	}


	public ArrayList<String> getStepStartTimeLst() {
		return stepStartTimeLst;
	}


	public void setStepStartTimeLst(ArrayList<String> stepStartTimeLst) {
		this.stepStartTimeLst = stepStartTimeLst;
	}


	public ArrayList<String> getStepEndTimeLst() {
		return stepEndTimeLst;
	}


	public void setStepEndTimeLst(ArrayList<String> stepEndTimeLst) {
		this.stepEndTimeLst = stepEndTimeLst;
	}
	
	
}
