package alemax;

import alemax.model.Chunk;
import alemax.model.Model;
import alemax.model.Voxel;


public class Main {

	public static void main(String[] args) {
		FileHandler handler = new FileHandler();
		byte[] bytes = handler.readVoxFile("ice2.vox");
		Model model = new Model(bytes);
		
		int count = 0;
		for(Chunk chunk : model.chunks) {
			for(Voxel voxel : chunk.voxels) {
				count++;
			}
		}
		
		System.out.println(count);
		

		
	}


}
