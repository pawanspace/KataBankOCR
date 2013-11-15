package codekata.ocr.parser;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.hamcrest.Matchers;
import org.junit.Test;

import codekata.ocr.Bank;

public class OCRFileParserTest {

	
	@Test
	public void shouldReadFileAndReturnBankWithAccounts() throws Exception {
		OCRFileParser parser = new OCRFileParser();
		parser.setAccountNumberParser(new AccountNumberParser());
		URL url = getClass().getClassLoader().getResource("ocr_enteries_test.txt");
		Bank bank = parser.parse(new File(url.toURI()));
		assertThat(bank, notNullValue());
		assertThat(bank.getAccounts(), notNullValue());
		assertThat(bank.getAccounts().size(), Matchers.is(5));
	}
	
}
