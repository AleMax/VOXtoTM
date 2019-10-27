package alemax.opengl;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Window {
	
	public static final int STANDARD_WIDTH = 1280;
	public static final int STANDARD_HEIGHT = 720;
	public static final String STANDARD_TITLE = "Vox to TrainsMod";
	//public static final int STANDARD_FRAMERATE = 60;
	
	public int width;
	public int height;
	public String title;
	
	private long window;
	
	private boolean isInit;
	
	public Window() {
		window = 0;
		width = STANDARD_WIDTH;
		height = STANDARD_HEIGHT;
		title = STANDARD_TITLE;
		
		isInit = false;
	}
	
	public void init() {
		if(!isInit) {
			if(GLFW.glfwInit()) {
				
				GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
				
				int monitorWidth = videoMode.width();
				int monitorHeight = videoMode.height();
				
				if(monitorWidth < 1500) {
					width = monitorWidth / 2;
					height = monitorHeight / 2;
				}
				
				window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
				
				if(window != 0) {
					
					GLFW.glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
					GLFW.glfwShowWindow(window);
					GLFW.glfwMakeContextCurrent(window);
					
					GLFW.glfwSetWindowTitle(window, title);
					GLFW.glfwSwapInterval(1);
					
					isInit = true;
					
				} else {
					System.err.println("Couldnt create Window");
				}
			} else {
				System.err.println("Couldnt initialize GLFW");
			}
		}
	}
	
	public void refresh() {
		if(isInit) {
			GLFW.glfwPollEvents();
			GLFW.glfwSwapBuffers(window);;
		}
	}
	
	
	
	public void close() {
		if(isInit) {
			
		}
	}
	
	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window);

	}
}
