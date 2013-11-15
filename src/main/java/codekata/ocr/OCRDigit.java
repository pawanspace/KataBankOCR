package codekata.ocr;

public class OCRDigit {

	private String firstLine;
	private String secondLine;
	private String thirdLine;

	public OCRDigit(String firstLine, String secondLine, String thirdLine) {
		this.firstLine = firstLine;
		this.secondLine = secondLine;
		this.thirdLine = thirdLine;
	}



	@Override
	public String toString() {
		return firstLine.concat(secondLine).concat(thirdLine);
	}

	

}
