package alemax.opengl;

import alemax.util.ResizeListener;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class Window {

	private List<ResizeListener> resizeListenerList = new ArrayList<ResizeListener>();

	private static final int STANDARD_WIDTH = 1280;
	private static final int STANDARD_HEIGHT = 720;
	private static final String STANDARD_TITLE = "Vox to TrainsMod";
	private static final Vector4f STANDARD_REFRESH_COLOR = new Vector4f(0f, 0.7f, 1f, 1f);
	//public static final int STANDARD_FRAMERATE = 60;
	
	private int[] width;
	private int[] height;
	private String title;
	
	private Vector4f refreshColor;
	
	private Input input;
	
	private long window;
	
	private GLFWWindowSizeCallback windowSizeCallback;
	
	private boolean isInit;
	
	public Window() {
		window = 0;
		width = new int[1];
		height = new int[1];
		width[0] = STANDARD_WIDTH;
		height[0] = STANDARD_HEIGHT;
		title = STANDARD_TITLE;
		
		refreshColor = STANDARD_REFRESH_COLOR;
		
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
					
					GL.createCapabilities();
					GL11.glEnable(GL11.GL_DEPTH_TEST);
					
					GLFW.glfwSetWindowTitle(window, title);
					GLFW.glfwSwapInterval(1);
					
					input = new Input(window);
					
					windowSizeCallback = new GLFWWindowSizeCallback() {
						
						@Override
						public void invoke(long window, int w, int h) {
							width[0] = w;
							height[0] = h;
							GL11.glViewport(0, 0, width[0], height[0]);

							for(ResizeListener listener : resizeListenerList) {
								listener.windowResized(width[0], height[0]);
							}

						}
					};
					
					GLFW.glfwSetWindowSizeCallback(window, windowSizeCallback);
					
					isInit = true;
					
				} else {
					System.err.println("Couldnt create Window");
				}
			} else {
				System.err.println("Couldnt initialize GLFW");
			}
		}
	}

	public void addResizeListener(ResizeListener listener) {
		this.resizeListenerList.add(listener);
	}

	public void startRender() {
		if(isInit) {
			GL11.glClearColor(refreshColor.x, refreshColor.y, refreshColor.z, refreshColor.w);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			//GLFW.glfwGetWindowSize(window, width, height);
			
			GLFW.glfwPollEvents();
		}
	}
	
	public void finishRender() {
		GLFW.glfwSwapBuffers(window);;
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
	
	public void setBackgroundColor(Vector4f backgroundColor) {
		this.refreshColor = backgroundColor;
	}
	
}
