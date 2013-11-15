package katabank.ocr.validtor;

public class AccountNumberValidator {

	private static final int OCR_DIVISOR_FOR_VALIDATING_CHECKSUM = 11;

	public boolean isValid(String accountNumber) {
		boolean isValid = true;
		if(accountNumber.contains("?")){
			isValid = false;
		}
		return isValid;
	}

	public boolean hasValidCheckSum(String accountNumber) {
		
		int checkSum = calculateCheckSum(accountNumber);
		
		if(checkSum % OCR_DIVISOR_FOR_VALIDATING_CHECKSUM == 0){
			return true;
		}
		
		return false;
	}

	// d1 + (d2 * 2) + (d3 * 3)
	private int calculateCheckSum(String accountNumber) {
		int checkSum = 0;
		int multiplier = 1;
	
		for (int i = accountNumber.length() -1; i >= 0; i--) {
			checkSum +=  Character.getNumericValue(accountNumber.charAt(i)) * multiplier;
			multiplier++;
		}
		return checkSum;
	}

}
