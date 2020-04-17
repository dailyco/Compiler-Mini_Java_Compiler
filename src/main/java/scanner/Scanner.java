package scanner;

import data.SymbolTable;

public class Scanner {
	
	public static void main(String[] args) {
		if (args[0] == null) {
			System.err.print("Please enter the file path for scanner");
			return;
		}
		
		Scanner scanner = new Scanner();
		String src = scanner.readSource(args[0]);
		SymbolTable symbolTable = scanner.analysisTokens(src);
		scanner.writeTokens(symbolTable);
	}
	
	private String readSource(String path) {
		SourceReader sourceReader = new SourceReader();
		return sourceReader.run(path);
	}
	
	private SymbolTable analysisTokens(String src) {
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
		lexicalAnalyzer.run(src);
	}
	
	private void writeTokens(SymbolTable symbolTable) {
		TokenWriter tokenWriter = new TokenWriter();
		tokenWriter.run(symbolTable);
	}
}
