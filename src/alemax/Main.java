package alemax;

import alemax.opengl.Window;


public class Main {

	public static void main(String[] args) {
		
		Window window = new Window();
		window.init();
		
		ModelView modelView = new ModelView(window);
		
		while(!window.shouldClose()) {
			window.startRender();
			
			modelView.update();
			
			window.finishRender();

		}
		
		modelView.destroy();
		window.close();
		
	}


}
