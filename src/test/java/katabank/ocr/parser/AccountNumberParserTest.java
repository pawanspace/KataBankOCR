package katabank.ocr.parser;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import katabank.ocr.AccountNumber;
import katabank.ocr.exception.OCRException;

public class AccountNumberParserTest {
	
	private static String SAMPLE_ACCOUNT_FIRST_LINE =  "    _  _     _  _  _  _  _ "; 
	private static String SAMPLE_ACCOUNT_SECOND_LINE = "  | _| _||_||_ |_   ||_||_|";
	private static String SAMPLE_ACCOUNT_THIRD_LINE =  "  ||_  _|  | _||_|  ||_| _|";
	private AccountNumberParser parser;
	
	@Before
	public void setUp(){
		parser = new AccountNumberParser();
	}
	
	@Test
	public void shouldParseAccountNumberInOCRRepersentation() throws Exception {
		AccountNumber accountNUmber = parser.parse(SAMPLE_ACCOUNT_FIRST_LINE, SAMPLE_ACCOUNT_SECOND_LINE, SAMPLE_ACCOUNT_THIRD_LINE);
		assertThat(accountNUmber, notNullValue());
	}
	
	@Test
	public void shouldParseAccountNumberAndAddOCRDigits() throws Exception {
		AccountNumber accountNUmber = parser.parse(SAMPLE_ACCOUNT_FIRST_LINE, SAMPLE_ACCOUNT_SECOND_LINE, SAMPLE_ACCOUNT_THIRD_LINE);
		assertThat(accountNUmber, notNullValue());
		assertThat(accountNUmber.getOcrDigitsForAccountNumber(), notNullValue());
		assertThat(accountNUmber.getOcrDigitsForAccountNumber().size() , is(9));
	}
	
	@Test(expected = OCRException.class)
	public void shouldThrowExceptionForInvalidInputWhereLengthsOfAllLinesAreNotSame() throws Exception {
		 parser.parse("-  - -", " - - - ", " | |");
	}
	
	

}
