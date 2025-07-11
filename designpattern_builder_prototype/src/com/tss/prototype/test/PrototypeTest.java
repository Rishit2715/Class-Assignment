package com.tss.prototype.test;

import com.tss.prototype.model.IDocument;
import com.tss.prototype.model.Report;
import com.tss.prototype.model.Resume;

public class PrototypeTest {
	public static void main(String[] args) {
		IDocument resumeTemplate = new Resume("Rishit Rathod", "Fresher Java Developer");
		IDocument reportTemplate = new Report("Monthly Report", "Excellent");

		IDocument clonedResume = resumeTemplate.clone();
		IDocument clonedReport = reportTemplate.clone();

		resumeTemplate.print();
		clonedResume.print();

		reportTemplate.print();
		clonedReport.print();
	}
}