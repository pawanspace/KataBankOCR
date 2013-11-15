package codekata.ocr.parser;


import codekata.ocr.AccountNumber;
import codekata.ocr.OCRDigit;
import codekata.ocr.exception.OCRException;

public class AccountNumberParser {

	private static final int ROWS_PER_DIGIT = 3;
	private static final int NUMBER_OF_DIGITS_IN_ACCOUNT_NUMBER = 9;

	public AccountNumber parse(String firstLine, String secondLine, String thirdLine) {
		assertInput(firstLine, secondLine, thirdLine);
		
		AccountNumber accountNumber = new AccountNumber();
		
		int beginIndex = 0;
		for (int i = 0; i < NUMBER_OF_DIGITS_IN_ACCOUNT_NUMBER; i++) {
			int lastIndex = beginIndex + ROWS_PER_DIGIT;
			OCRDigit digit = new OCRDigit(firstLine.substring(beginIndex, lastIndex), secondLine.substring(beginIndex, lastIndex), thirdLine.substring(beginIndex, lastIndex));
			beginIndex +=ROWS_PER_DIGIT;
			accountNumber.getOcrDigitsForAccountNumber().add(digit);
		}
		
		return accountNumber;
	}

	private void assertInput(String firstLine, String secondLine, String thirdLine) {
		if(isDifferentLength(firstLine, secondLine) || isDifferentLength(firstLine, thirdLine)){
			throw new OCRException("Illegal input: Expected atleast 9 digits for Account Number");
		}
	}

	private boolean isDifferentLength(String firstLine, String second) {
		return firstLine.length() != second.length();
	}
}
