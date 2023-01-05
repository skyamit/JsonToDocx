package com.gyan;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyan.model.Disease;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;

import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	
	public static void main(String[] args) {
	    
		try {
			XWPFDocument document = new XWPFDocument();
			BufferedReader br = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("res/data.json")));
		    
			ObjectMapper mapper = new ObjectMapper();
			List<Disease> diseases = Arrays.asList(mapper.readValue(br, Disease[].class));
		    
		    
		    for(Disease disease : diseases) {
				XWPFParagraph paragraph = document.createParagraph();
				XWPFRun title = paragraph.createRun();
				title.setFontSize(12);
				title.setBold(true);
				title.setText(disease.getTitle());
				title.addBreak();
				
				XWPFHyperlinkRun hyperlinkrun = paragraph.createHyperlinkRun(disease.getUrl());
				hyperlinkrun.setText(disease.getUrl());
				hyperlinkrun.setColor("0000FF");
				hyperlinkrun.setUnderline(UnderlinePatterns.SINGLE);
				
			    CTAbstractNum cTAbstractNum = CTAbstractNum.Factory.newInstance();
			    cTAbstractNum.setAbstractNumId(BigInteger.valueOf(0));
			    CTLvl cTLvl = cTAbstractNum.addNewLvl();
			    cTLvl.addNewNumFmt().setVal(STNumberFormat.BULLET);
	
			    cTLvl.addNewLvlText().setVal("•");
			    
			    XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);
			    XWPFNumbering numbering = document.createNumbering();
			    BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);
			    BigInteger numID = numbering.addNum(abstractNumID);
			    
			    for(String fact : disease.getFacts()) {
	
			        XWPFParagraph bulletedPara = document.createParagraph();
			        XWPFRun runn = bulletedPara.createRun();
			        runn.setFontSize(10);
			        runn.setText(fact);
			        bulletedPara.setNumID(numID);
			        	
			    }
		    }

			FileOutputStream output = new FileOutputStream("Data.docx");
			document.write(output);
			
			System.out.println("File Created.. Please Check folder where this Jar is Present!");
			document.close();
			
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}

	
}
