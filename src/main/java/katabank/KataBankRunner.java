package katabank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import katabank.ocr.AccountNumber;
import katabank.ocr.Bank;
import katabank.ocr.parser.AccountNumberParser;
import katabank.ocr.parser.OCRFileParser;
import katabank.ocr.reportgenerator.BankReportGenerator;
import katabank.ocr.translator.AccountNumberTranslator;
import katabank.ocr.translator.OCRDigitTranslator;
import katabank.ocr.validtor.AccountNumberValidator;

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
