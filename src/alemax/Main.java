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
		
		Window window = new Window();
		window.init();
		
		ModelView modelView = new ModelView();
		
		while(!window.shouldClose()) {
			window.startRender();
			
			modelView.update();
			
			window.finishRender();

		}
		
		window.close();
		
	}


}
