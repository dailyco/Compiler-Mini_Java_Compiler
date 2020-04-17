package scanner;

import data.AnalyzedTokenList;

public class Scanner {
	private String src;
	private String path;
	private String fileName;
	private AnalyzedTokenList tokenList = new AnalyzedTokenList();
	
	public void run(String[] args) {
		if (args[0] == null) {
			System.err.print("Please enter the file path for scanner"); // TODO create argument not found exception
			return;
		}
		
		getInfos(args[0]);
		readSource();
		if (src == "") return; // File not found exception
//		analysisTokens();
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
	
//	private void analysisTokens() {
//		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
//		symbolTable = lexicalAnalyzer.run(src);
//	}
	
	private void writeTokens() {
		TokenWriter tokenWriter = new TokenWriter(fileName);
		tokenWriter.run(tokenList, false);
	}
}
