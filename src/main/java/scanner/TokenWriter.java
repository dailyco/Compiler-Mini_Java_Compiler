package scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import data.AnalyzedTokenList;
import data.Token;

public class TokenWriter {
	String path;
	
	public TokenWriter() {
		 this.path = "scanner_result/result.txt";
	}
	
	public TokenWriter(String fileName) {
		 this.path = "scanner_result/" + fileName.split("//.")[0] + "_result.txt";
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
            	for (Token token : tokenList) {
                	switch(token.getTokenType()) {
                		// Fake token 일 경우
                		case LINE_COMMENTS: case SPACE: case NEW_LINE: case TAB:
                			continue;
                		// Error가 발생한 경우
//                		case ERROR:
//                			fw.write(token.getValue());
//                			break;
                		default:
                			fw.write(token.toString() + "\n");
                	}
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
