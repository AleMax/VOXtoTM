package alemax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
	
	private static final byte FirstChar = (byte) 'V';
	private static final byte SecondChar = (byte) 'O';
	private static final byte ThirdChar = (byte) 'X';
	private static final byte FourthChar = (byte) ' ';

	
	public byte[] readVoxFile(String filePath) {
		
		
		try {
			byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
			
			if(filePath.endsWith(".vox") && fileContent.length > 7) {
				if(fileContent[0] == FirstChar && fileContent[1] == SecondChar &&
						fileContent[2] == ThirdChar && fileContent[3] == FourthChar) {
					return fileContent;
				}
			}
		} catch (IOException e) {
			System.err.println("EXCEPTION READING FILE");
			e.printStackTrace();
		}
		
		return null;
	}
	
}
