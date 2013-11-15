package codekata.ocr.validtor;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class AccountNumberValidatorTest {
	
	private AccountNumberValidator validator;

	@Before
	public void setUp(){
		validator = new AccountNumberValidator();
	}
	
	@Test
	public void shouldReturnTrueIfAccountNumberDoNotHaveUnknownOCRDigit() throws Exception {
		boolean result = validator.isValid("000000051");
		assertThat(result, is(true));
	}

	@Test
	public void shouldReturnFalseIfAccountNumberHasUnknownOCRDigit() throws Exception {
		boolean result = validator.isValid("49006771?");
		assertThat(result, is(false));
	}
	
	@Test
	public void shouldReturnTrueIfAccountNumberHasValidChecksum() throws Exception {
		boolean result = validator.hasValidCheckSum("000000051");
		assertThat(result, is(true));
	}

	@Test
	public void shouldReturnTrueIfAccountNumberHasValidChecksum_2() throws Exception {
	    boolean result = validator.hasValidCheckSum("664371495");
	    assertThat(result, is(false));
	}



}
