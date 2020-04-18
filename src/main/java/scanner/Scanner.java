package scanner;

import data.AnalyzedTokenList;
import exception.LexicalAnalysisException;
import exception.LexicalAnalysisException.LexicalErrorCode;
import exception.ScannerException;
import exception.ScannerException.ScannerErrorCode;

public class Scanner {
	private String src;
	private String path;
	private String fileName;
	private AnalyzedTokenList tokenList = new AnalyzedTokenList();
	
	private LexicalAnalysisException lException;
	private ScannerException sException;
	
	public void run(String[] args) {
		if (args[0] == null) {
			sException = new ScannerException();
			System.err.print(sException.getMessage(ScannerErrorCode.NO_ARGUMENT));
			return;
		}
		
		getInfos(args[0]);
		readSource();
		
		if (src == "") {
			sException = new ScannerException(path);
			System.err.print(sException.getMessage(ScannerErrorCode.FILE_NOT_FOUND));
			return;
		}
		
		
		int flag = analysisTokens();
		if (flag == -1) {
			System.err.print(lException.getMessage(LexicalErrorCode.ILLEGAL_CODE));
			return;
		} else if (flag == 0) return;
		
		writeTokens();
	}
	
	private void getInfos(String path) {
		String[] strs = path.split("/");
		
		this.path = path;
		this.fileName = strs[strs.length - 1];
	}
	
	private void readSource() {
		SourceReader sourceReader = new SourceReader();
		src = sourceReader.run(path);
	}
	
	private int analysisTokens() {
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(src, fileName);
		tokenList = lexicalAnalyzer.run();
		
		if (tokenList == null && lexicalAnalyzer.getIllegalFlag() == 1) {
			lException = new LexicalAnalysisException(lexicalAnalyzer.getLine(), fileName, src.charAt(lexicalAnalyzer.getIdx()));
			return -1;
		} else if (tokenList == null) {
			return 0;
		} else return 1;
	}
	
	private void writeTokens() {
		TokenWriter tokenWriter = new TokenWriter(fileName);
		tokenWriter.run(tokenList, false);
	}
}
