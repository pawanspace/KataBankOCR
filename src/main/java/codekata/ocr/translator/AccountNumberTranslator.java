package codekata.ocr.translator;

import java.util.List;

import codekata.ocr.AccountNumber;
import codekata.ocr.OCRDigit;
import codekata.ocr.exception.OCRException;

public class AccountNumberTranslator {

	private OCRDigitTranslator ocrDigitTranslator;

	
	
	public OCRDigitTranslator getOcrDigitTranslator() {
		return ocrDigitTranslator;
	}


	public void setOcrDigitTranslator(OCRDigitTranslator ocrDigitTranslator) {
		this.ocrDigitTranslator = ocrDigitTranslator;
	}



	public String translate(AccountNumber accountNumber) {
		
		assertAccountNumberNotNull(accountNumber);
		
		List<OCRDigit> ocrDigitsForAccountNumber = accountNumber.getOcrDigitsForAccountNumber();
		String accountNumberString = "";
		for (OCRDigit ocrDigit : ocrDigitsForAccountNumber) {
			accountNumberString += ocrDigitTranslator.translate(ocrDigit);
		}
		
		return accountNumberString;
	}


	private void assertAccountNumberNotNull(AccountNumber accountNumber) {
		if(accountNumber == null){
			throw new OCRException("Account number should not be null.");
		}
	}

}
