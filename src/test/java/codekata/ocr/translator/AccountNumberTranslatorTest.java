package codekata.ocr.translator;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import codekata.ocr.AccountNumber;
import codekata.ocr.OCRDigit;
import codekata.ocr.exception.OCRException;

@RunWith(MockitoJUnitRunner.class)
public class AccountNumberTranslatorTest {
	
	private AccountNumberTranslator translator;
	
	@Mock private OCRDigitTranslator mockOcrDigitTranslator;
	
	@Before
	public void setUp(){
		translator = new AccountNumberTranslator();
		translator.setOcrDigitTranslator(mockOcrDigitTranslator);
	}
	
	@Test
	public void shouldTranslateOCRRepersentationOfAccountNumberToNumericAccountNumber() throws Exception {
		AccountNumber accountNumber = new AccountNumber();
		String result = translator.translate(accountNumber);
		assertThat(result, Matchers.notNullValue());
	}
	
	@Test
	public void shouldTranslateAccountNumberInOCRToExpectedValue() throws Exception {
		AccountNumber accountNumber = getAccountNumberForTest();
		Mockito.when(mockOcrDigitTranslator.translate(Mockito.any(OCRDigit.class))).thenReturn("1", "2","3","4","5","6","7","8","9");
		String result = translator.translate(accountNumber);
		assertThat(result, Matchers.is("123456789"));
	}
	
	@Test(expected = OCRException.class)
	public void shouldThrowNullPointerExceptionIfAccountNumberIsNull() throws Exception {
		translator.translate(null);
	}

	private AccountNumber getAccountNumberForTest() {
		AccountNumber accountNumber = new AccountNumber();
		for (int i = 0; i < 9; i++) {
			accountNumber.getOcrDigitsForAccountNumber().add(new OCRDigit(null, null, null));
		}
		return accountNumber;
	}
	
	
	
}
