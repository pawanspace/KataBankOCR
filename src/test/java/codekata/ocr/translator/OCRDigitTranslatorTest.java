package codekata.ocr.translator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import codekata.ocr.OCRDigit;

public class OCRDigitTranslatorTest {

	private static final OCRDigit OCR_DIGIT_ONE =	new OCRDigit("   ","  |" ,"  |");
	private static final OCRDigit OCR_DIGIT_TWO =	new OCRDigit(" _ "," _|" ,"|_ ");
	private static final OCRDigit OCR_DIGIT_UNKNOWN = new OCRDigit(" _ "," _" ,"|_ ");;
	
	private OCRDigitTranslator parser;

	@Before
	public void setUp(){
		parser = new OCRDigitTranslator();
	}
	
	@Test
	public void shouldParseOCRDigitAndReturnNumericRepersentation() throws Exception {
		String digit = parser.translate(OCR_DIGIT_ONE);
		assertThat(digit, Matchers.not("?"));
	}
	
	@Test
	public void shouldParseOCRDigitTwoAndReturnNumericRepersentation() throws Exception {
		String expected = "2";
		String digit = parser.translate(OCR_DIGIT_TWO);
		
		assertThat(digit, is(expected));
	}

	@Test
	public void shouldReturnQuestionMarkForUnknownDigit() throws Exception {
		String expected = "?";

		String digit = parser.translate(OCR_DIGIT_UNKNOWN);
		
		assertThat(digit, is(expected));
	}
	
	
}
