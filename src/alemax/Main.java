package alemax;

import org.lwjgl.glfw.GLFW;

import alemax.model.Chunk;
import alemax.model.Model;
import alemax.model.Voxel;
import alemax.opengl.Window;
import alemax.util.FileHandler;


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
		
		Window window = new Window();
		window.init();
		
		//long lastFrame = System.nanoTime();
		
		while(!window.shouldClose()) {
			//long time = System.nanoTime();
			
			window.startRender();
			
			//System.out.println(window.getInput().getScrollX() + "\t" + window.getInput().getScrollY());
			
			if(window.getInput().isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
				break;
			}
			
			
			window.finishRender();
			/*
			long currentFrame = System.nanoTime();
			System.out.println( 1_000_000_000 / (currentFrame - lastFrame));
			lastFrame = currentFrame;
			*/
		}
		
		window.close();
		
	}


}
