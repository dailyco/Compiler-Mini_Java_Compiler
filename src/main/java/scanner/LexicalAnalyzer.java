package scanner;

import data.AnalyzedTokenList;
import data.KeywordTable;
import data.States;
import data.Token;
import data.Token.TokenType;
import exception.LexicalAnalysisException;

public class LexicalAnalyzer {
	int idx;
	int line;
	String src;
	String fileName;
	AnalyzedTokenList tokenList = new AnalyzedTokenList();
	
	public LexicalAnalyzer(String src, String fileName) {
		idx = 0;
		line = 1;
		this.src = src;
		this.fileName = fileName;
	}
	
	public AnalyzedTokenList run() {
		while(idx < src.length()) {
			// keyword check
			System.out.println("idx: " + idx);
			KeywordTable keywordType = KeywordTable.find(src.substring(idx));
			
			if (keywordType != null) {
				Token token = new Token(TokenType.KEYWORD, keywordType.getKeyword());
				System.out.println("token: " + token);
				
				tokenList.add(token);
				idx += token.length();
			} else {
				// DFA check
				Token token = checkDFA(States.Q0, idx, idx);
				tokenList.add(token);
			}
		}
		
		return tokenList;
	}
	

	private Token checkDFA(States curr_state, int start_idx, int r_idx) {
		do {
			curr_state = curr_state.transition(src.charAt(r_idx++));
		} while (curr_state != States.ERROR && !curr_state.isAccept());
		
		System.out.println("states: " + curr_state);
		
		if (curr_state == States.ERROR) return null;
		else {
			Token token = null;
			
			if (isMoreChar(curr_state.getTokenType())) {
				token = checkDFA(curr_state, start_idx, r_idx);
			}
			
			if (token == null) {
				token = new Token(curr_state.getTokenType(), src.substring(start_idx, r_idx));
				if (token.getTokenType() == TokenType.NEW_LINE) line++;
				idx = r_idx;
				
				System.out.println("token: " + token);
				
				return token;
			} else return token;
		}
	}
	
	private boolean isMoreChar(TokenType type) {
		switch(type) {
			case PLUS: case MINUS: case TIMES: case DIVIDE: case REMAINDER: 
				case GREATER_THAN: case LESS_THAN: case ASSIGNMENT: case NOT:
				case ID: case NUMBER_LITERAL: case LINE_COMMENTS:
				return true;
			default:
				return false;
		}
	}
	
}
