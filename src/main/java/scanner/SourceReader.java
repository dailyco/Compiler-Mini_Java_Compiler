package scanner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SourceReader {
	public String run(String path) {
		StringBuffer sb = new StringBuffer();
		List<String> lines = null;
		
		try {
			lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.print("File not found: Please check the file"); // TODO create file not found exception
			return "";
		}
		
		for(String line: lines)
            sb.append(line + "\n");
        
		return sb.toString();
	}
}
