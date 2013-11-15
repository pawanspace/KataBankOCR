package katabank.ocr.translator;

import static katabank.ocr.constants.OCRDigitConstants.EIGHT;
import static katabank.ocr.constants.OCRDigitConstants.FIVE;
import static katabank.ocr.constants.OCRDigitConstants.FOUR;
import static katabank.ocr.constants.OCRDigitConstants.NINE;
import static katabank.ocr.constants.OCRDigitConstants.ONE;
import static katabank.ocr.constants.OCRDigitConstants.SEVEN;
import static katabank.ocr.constants.OCRDigitConstants.SIX;
import static katabank.ocr.constants.OCRDigitConstants.THREE;
import static katabank.ocr.constants.OCRDigitConstants.TWO;
import static katabank.ocr.constants.OCRDigitConstants.ZERO;

import java.util.HashMap;
import java.util.Map;

import katabank.ocr.OCRDigit;


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
