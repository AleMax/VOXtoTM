package alemax.opengl;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;

public class Window {
	
	private static final int STANDARD_WIDTH = 1280;
	private static final int STANDARD_HEIGHT = 720;
	private static final String STANDARD_TITLE = "Vox to TrainsMod";
	//public static final int STANDARD_FRAMERATE = 60;
	
	private int[] width;
	private int[] height;
	private String title;
	
	private Input input;
	
	private long window;
	
	private boolean isInit;
	
	public Window() {
		window = 0;
		width = new int[1];
		height = new int[1];
		width[0] = STANDARD_WIDTH;
		height[0] = STANDARD_HEIGHT;
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
					width[0] = monitorWidth / 2;
					height[0] = monitorHeight / 2;
				}
				
				window = GLFW.glfwCreateWindow(width[0], height[0], title, 0, 0);
				
				if(window != 0) {
					
					GLFW.glfwSetWindowPos(window, (videoMode.width() - width[0]) / 2, (videoMode.height() - height[0]) / 2);
					GLFW.glfwShowWindow(window);
					GLFW.glfwMakeContextCurrent(window);
					
					GLFW.glfwSetWindowTitle(window, title);
					GLFW.glfwSwapInterval(1);
					
					input = new Input(window);
					
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
			
			GLFW.glfwGetWindowSize(window, width, height);
			
			GLFW.glfwPollEvents();
			GLFW.glfwSwapBuffers(window);;
		}
	}
	
	
	
	public void close() {
		if(isInit) {
			input.free();
			GLFW.glfwSetWindowShouldClose(window, true);
			GLFW.glfwDestroyWindow(window);
			GLFW.glfwTerminate();
		}
	}
	
	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window);
	}
	
	public Input getInput() {
		return input;
	}
	
	public int getWidth() {
		return width[0];
	}
	
	public int getHeight() {
		return height[0];
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
		GLFW.glfwSetWindowTitle(window, title);
	}
	
}
