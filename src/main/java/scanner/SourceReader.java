package scanner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SourceReader {
	public String run(String path) {
		StringBuffer sb = new StringBuffer();
		
		try {
			List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
			
	        for(String line: lines)
	            sb.append(line + "\n");
	        sb.append('\255');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return sb.toString();
	}
}
