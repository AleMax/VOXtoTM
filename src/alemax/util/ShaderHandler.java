package alemax.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShaderHandler {
	
	public String loadShader(String path) {
		StringBuilder shader = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(path)));
			String line = "";

			while((line = reader.readLine()) != null) {
				shader.append(line).append("\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return shader.toString();
	}
	
}
