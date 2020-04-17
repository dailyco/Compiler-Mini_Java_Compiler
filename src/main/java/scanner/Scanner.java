package scanner;

import data.SymbolTable;

public class Scanner {
	private String src;
	private String path;
	private String fileName;
	private SymbolTable symbolTable = new SymbolTable();
	
	public static void main(String[] args) {
		if (args[0] == null) {
			System.err.print("Please enter the file path for scanner");
			return;
		}
		
		Scanner scanner = new Scanner();
		
		scanner.getInfos(args[0]);
		scanner.readSource();
		scanner.analysisTokens();
		scanner.writeTokens();
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
	
	private void analysisTokens() {
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
		symbolTable = lexicalAnalyzer.run(src);
	}
	
	private void writeTokens() {
		TokenWriter tokenWriter = new TokenWriter();
		tokenWriter.run(symbolTable);
	}
}
