package codekata.ocr.translator;

import static codekata.ocr.constants.OCRConstants.EIGHT;
import static codekata.ocr.constants.OCRConstants.FIVE;
import static codekata.ocr.constants.OCRConstants.FOUR;
import static codekata.ocr.constants.OCRConstants.NINE;
import static codekata.ocr.constants.OCRConstants.ONE;
import static codekata.ocr.constants.OCRConstants.SEVEN;
import static codekata.ocr.constants.OCRConstants.SIX;
import static codekata.ocr.constants.OCRConstants.THREE;
import static codekata.ocr.constants.OCRConstants.TWO;
import static codekata.ocr.constants.OCRConstants.ZERO;

import java.util.HashMap;
import java.util.Map;

import codekata.ocr.OCRDigit;


public class OCRDigitTranslator {

	private Map<String, String> ocrToNumericRepersentationMap = new HashMap<String, String>();
	
	public OCRDigitTranslator() {
		ocrToNumericRepersentationMap.put(ZERO, "0");
		ocrToNumericRepersentationMap.put(ONE, "1");
		ocrToNumericRepersentationMap.put(TWO, "2");
		ocrToNumericRepersentationMap.put(THREE, "3");
		ocrToNumericRepersentationMap.put(FOUR, "4");
		ocrToNumericRepersentationMap.put(FIVE, "5");
		ocrToNumericRepersentationMap.put(SIX, "6");
		ocrToNumericRepersentationMap.put(SEVEN, "7");
		ocrToNumericRepersentationMap.put(EIGHT, "8");
		ocrToNumericRepersentationMap.put(NINE, "9");
	}
	
	public String translate(OCRDigit ocrDigit) {
		String ocrToNumericRepersentation = ocrToNumericRepersentationMap.get(ocrDigit.toString());
		if(ocrToNumericRepersentation != null){
			return ocrToNumericRepersentation;
		}
		return "?";
	}

	

}
