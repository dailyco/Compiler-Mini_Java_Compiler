package scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import data.AnalyzedTokenList;
import data.Token;
import data.Token.TokenType;
import exception.LexicalAnalysisException;
import exception.LexicalAnalysisException.LexicalErrorCode;

public class TokenWriter {
	String path = "scanner_result/";
	String fileName;
	Token preToken;
	
	public TokenWriter() {
		// When there is not dir, make it
		File dir = new File(path);
		
		if (!dir.exists()) {
			try {
				dir.mkdir();
				} 
		        catch(Exception e){
			    e.getStackTrace();
			}
		}
		
		this.path += "result.txt";
	}
	
	public TokenWriter(String fileName) {
		// When there is not dir, make it
		File dir = new File(path);
		
		if (!dir.exists()) {
			try {
				dir.mkdir();
				} 
		        catch(Exception e){
			    e.getStackTrace();
			}
		}
		
		this.fileName = fileName;
		this.path += fileName.split("\\.")[0] + "_result.txt";
	}
	
	public void run(AnalyzedTokenList tokenList, boolean containWhiteSpace) {
		File file = new File(path);
		FileWriter fw = null;
		
		try {
            fw = new FileWriter(file, false);
            
            if (containWhiteSpace) {
            	for (Token token : tokenList)
                	fw.write(token.toString() + "\n");
            } else {
            	preToken = tokenList.get(0);
            	for (Token token : tokenList) {
            		if (preToken.getTokenType() == TokenType.NUMBER_LITERAL && token.getTokenType() == TokenType.ID) {
            			LexicalAnalysisException lException = new LexicalAnalysisException(preToken.getLine(), fileName, preToken.getValue() + token.getValue());
	        			System.err.print(lException.getMessage(LexicalErrorCode.ILLEGAL_TOKEN));
	        			fw.write("\n" + lException.getMessage(LexicalErrorCode.ILLEGAL_TOKEN));
	        			fw.close();
	        			return;
            		}
            		
                	switch(token.getTokenType()) {
                		// Fake token 일 경우
                		case SPACE: case NEW_LINE: case TAB:
                			continue;
                		case ERROR: 
                			fw.write("\n" + token.toString() + "\'" + preToken.getValue() + token.getValue() + "\' (" + fileName + ":" + token.getLine() + ")\n");
                			fw.close();
                			return;
                		default:
                			fw.write(token.toString() + "\n");
                	}
                	preToken = token;
                }
            }
            
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fw != null) fw.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
	}
}
