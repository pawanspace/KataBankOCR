package codekata.ocr.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import codekata.ocr.AccountNumber;
import codekata.ocr.Bank;

public class OCRFileParser {

	private AccountNumberParser accountNumberParser;
	
	
	
	public void setAccountNumberParser(AccountNumberParser accountNumberParser) {
		this.accountNumberParser = accountNumberParser;
	}



	public Bank parse(File file) throws IOException {
		Bank ocrBank = new Bank();
		List<AccountNumber> accounts = scanAccountNumbers(file);
		ocrBank.setAccounts(accounts);
		return ocrBank;
	}



	private List<AccountNumber> scanAccountNumbers(File file)
			throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		List<AccountNumber> accounts = new ArrayList<AccountNumber>();
		while(reader.ready()){
			accounts.add(accountNumberParser.parse(reader.readLine(), reader.readLine(), reader.readLine()));
			//4th line is blank.
			reader.readLine();
		}
		reader.close();
		return accounts;
	}

}
