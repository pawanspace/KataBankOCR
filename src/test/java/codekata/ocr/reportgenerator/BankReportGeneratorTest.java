package codekata.ocr.reportgenerator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.io.StringWriter;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import codekata.ocr.validtor.AccountNumberValidator;

@RunWith(MockitoJUnitRunner.class)
public class BankReportGeneratorTest {

	
    private static final String ACCOUNT_NUMBER_WITH_BAD_CHECKSUM = "123416789";
    private static final String INVALID_ACCOUNT_NUMBER = "1234??672";
    private static final String VALID_ACCOUNT_NUMBER = "457508000";

    private BankReportGenerator reportGenerator;
    
    @Mock private AccountNumberValidator mockAccountNumberValidator;
    
    @Before
    public void setUp(){
        reportGenerator = new BankReportGenerator();
        reportGenerator.setAccountNumberValidator(mockAccountNumberValidator);
    }
    
	@Test
	public void shouldOuputAllValidAccountNumbers() throws Exception {
		when(mockAccountNumberValidator.isValid(VALID_ACCOUNT_NUMBER)).thenReturn(true);
		when(mockAccountNumberValidator.hasValidCheckSum(VALID_ACCOUNT_NUMBER)).thenReturn(true);
		StringWriter stringWriter = new StringWriter();
		
        reportGenerator.writeReportToFile(Arrays.asList(VALID_ACCOUNT_NUMBER), stringWriter);

        assertThat(stringWriter.toString(), is("457508000\n"));
	}

	@Test
	public void shouldAddILLForAllInValidAccountNumbers() throws Exception {
	    when(mockAccountNumberValidator.isValid(VALID_ACCOUNT_NUMBER)).thenReturn(true);
	    when(mockAccountNumberValidator.isValid(INVALID_ACCOUNT_NUMBER)).thenReturn(false);
	    when(mockAccountNumberValidator.hasValidCheckSum(VALID_ACCOUNT_NUMBER)).thenReturn(true);
        when(mockAccountNumberValidator.hasValidCheckSum(INVALID_ACCOUNT_NUMBER)).thenReturn(true);
	    StringWriter stringWriter = new StringWriter();
	    
	
	    reportGenerator.writeReportToFile(Arrays.asList(VALID_ACCOUNT_NUMBER, INVALID_ACCOUNT_NUMBER), stringWriter);
	    
	    assertThat(stringWriter.toString(), is("457508000\n1234??672 ILL\n"));
	}

	@Test
	public void shouldAddERRForAllAccountNumbersWithBadChecksum() throws Exception {
	    when(mockAccountNumberValidator.isValid(VALID_ACCOUNT_NUMBER)).thenReturn(true);
	    when(mockAccountNumberValidator.isValid(INVALID_ACCOUNT_NUMBER)).thenReturn(false);
	    when(mockAccountNumberValidator.isValid(ACCOUNT_NUMBER_WITH_BAD_CHECKSUM)).thenReturn(true);
	    when(mockAccountNumberValidator.hasValidCheckSum(VALID_ACCOUNT_NUMBER)).thenReturn(true);
        when(mockAccountNumberValidator.hasValidCheckSum(INVALID_ACCOUNT_NUMBER)).thenReturn(true);
        when(mockAccountNumberValidator.hasValidCheckSum(ACCOUNT_NUMBER_WITH_BAD_CHECKSUM)).thenReturn(false);
        StringWriter stringWriter = new StringWriter();
        
	    
	    reportGenerator.writeReportToFile(Arrays.asList(VALID_ACCOUNT_NUMBER, INVALID_ACCOUNT_NUMBER, ACCOUNT_NUMBER_WITH_BAD_CHECKSUM), stringWriter);
	    
	    assertThat(stringWriter.toString(), is("457508000\n1234??672 ILL\n123416789 ERR\n"));
	}
	
}
