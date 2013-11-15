package codekata;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import codekata.ocr.AccountNumber;
import codekata.ocr.Bank;
import codekata.ocr.parser.AccountNumberParser;
import codekata.ocr.parser.OCRFileParser;
import codekata.ocr.reportgenerator.BankReportGenerator;
import codekata.ocr.translator.AccountNumberTranslator;
import codekata.ocr.translator.OCRDigitTranslator;
import codekata.ocr.validtor.AccountNumberValidator;

public class KataBankRunner {

    public static void main(String[] args) throws IOException {
        OCRFileParser parser = new OCRFileParser();
        parser.setAccountNumberParser(new AccountNumberParser());
        Bank bank = parser.parse(new File("ocr_enteries.txt"));
        
        List<AccountNumber> accounts = bank.getAccounts();
        
        AccountNumberTranslator translator = new AccountNumberTranslator();
        translator.setOcrDigitTranslator(new OCRDigitTranslator());
        List<String> accountNumbers = new ArrayList<>();
        for (AccountNumber accountNumber : accounts) {
            accountNumbers.add(translator.translate(accountNumber));
        }
        
        BankReportGenerator reportGenerator = new BankReportGenerator();
        reportGenerator.setAccountNumberValidator(new AccountNumberValidator());
        reportGenerator.generateReport(accountNumbers, "scanned_ocr_enteries.txt");
    }    
}
