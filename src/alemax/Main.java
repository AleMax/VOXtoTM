package alemax;

import alemax.vox.VoxChunkMain;
import alemax.vox.VoxDict;
import alemax.vox.VoxString;

public class Main {

	public static void main(String[] args) {
		FileHandler handler = new FileHandler();
		byte[] bytes = handler.readVoxFile("ice2.vox");
		
		VoxChunkMain main = new VoxChunkMain(bytes, 8);
		
	}

}
