package alemax;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import alemax.model.Chunk;
import alemax.model.Model;
import alemax.model.Voxel;
import alemax.opengl.ModelRenderer;
import alemax.opengl.Renderable;
import alemax.opengl.Vertex;
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
		
		ModelRenderer modelRenderer = new ModelRenderer();
		
		Vertex[] vertices = {new Vertex(new Vector3f(-0.5f, 0.5f, 0f)), new Vertex(new Vector3f(0.5f, 0.5f, 0f)), new Vertex(new Vector3f(0.5f, -0.5f, 0f)), new Vertex(new Vector3f(-0.5f, -0.5f, 0f))};
		int[] indices = {0, 3, 1, 1, 3, 2 };
		
		Renderable renderModel = new Renderable(vertices, indices);
		renderModel.loadToMemory();
		
		while(!window.shouldClose()) {
			window.startRender();
			
			//Escape to close
			if(window.getInput().isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
				break;
			}
			
			//Render
			modelRenderer.renderModel(renderModel);
			
			
			window.finishRender();

		}
		
		window.close();
		
	}


}
