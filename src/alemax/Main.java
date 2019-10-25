package alemax;

import alemax.model.Model;
import alemax.vox.VoxRotation;

public class Main {

	public static void main(String[] args) {
		
		FileHandler handler = new FileHandler();
		byte[] bytes = handler.readVoxFile("ice2.vox");
		
		Model model = new Model(bytes);
		System.out.println(model.chunks.size());
		
	}
	


}
