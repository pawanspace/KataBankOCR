package katabank.ocr.parser;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import katabank.ocr.Bank;

public class OCRFileParserTest {

    
    private OCRFileParser parser;


    @Before
    public void setUp(){
        parser = new OCRFileParser();
    }
	
	@Test
	public void shouldReadFileAndReturnBankWithAccounts() throws Exception {
		parser.setAccountNumberParser(new AccountNumberParser());
		
		URL url = getClass().getClassLoader().getResource("ocr_enteries_test.txt");
		Bank bank = parser.parse(new File(url.toURI()));
		
		assertThat(bank, notNullValue());
		assertThat(bank.getAccounts(), notNullValue());
		assertThat(bank.getAccounts().size(), Matchers.is(5));
	}
	
	
	@Test(expected = FileNotFoundException.class)
	public void shouldThrowExceptionWhenFileNotFound() throws Exception {
	    parser.parse(new File("does_not_exist.txt"));
	}
	
	
	
}
