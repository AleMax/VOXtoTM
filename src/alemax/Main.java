package alemax;

import alemax.model.Model;
import alemax.opengl.Window;
import alemax.util.FileHandler;


public class Main {

	public static void main(String[] args) {

		FileHandler fileHandler = new FileHandler();
		Model model = new Model(fileHandler.readVoxFile("ice2.vox"));

		Window window = new Window();
		window.init();
		
		ModelView modelView = new ModelView(window);

		modelView.setModel(model);

		while(!window.shouldClose()) {
			window.startRender();
			
			modelView.update();
			
			window.finishRender();

		}
		
		modelView.destroy();
		window.close();
		
	}


}
