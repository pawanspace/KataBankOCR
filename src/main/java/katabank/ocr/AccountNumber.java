package katabank.ocr;

import java.util.LinkedList;
import java.util.List;

public class AccountNumber {
	

	private List<OCRDigit> ocrDigitsForAccountNumber = new LinkedList<OCRDigit>();

	public List<OCRDigit> getOcrDigitsForAccountNumber() {
		return ocrDigitsForAccountNumber;
	}


}
