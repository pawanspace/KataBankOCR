package codekata.ocr.reportgenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import codekata.ocr.validtor.AccountNumberValidator;

public class BankReportGenerator {

    private static final String ERROR = " ERR";
    private static final String INVALID = " ILL";
    
    private AccountNumberValidator accountNumberValidator;
    
    
    public void setAccountNumberValidator(AccountNumberValidator accountNumberValidator) {
        this.accountNumberValidator = accountNumberValidator;
    }

    public void generateReport(List<String> accountNumbers, String fileName) throws IOException {
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writeReportToFile(accountNumbers, writer);
        writer.flush();
        writer.close();
    }

    protected void writeReportToFile(List<String> accountNumbers, Writer writer) throws IOException {
        for (String accountNumber : accountNumbers) {
            accountNumber = validateAccountNumber(accountNumber);
            writer.write(accountNumber+"\n");
        }
    }

    private String validateAccountNumber(String accountNumber) {
        if(!accountNumberValidator.isValid(accountNumber)){
            accountNumber += INVALID;
        }else if(!accountNumberValidator.hasValidCheckSum(accountNumber)){
            accountNumber += ERROR;
        }
        return accountNumber;
    }

}
