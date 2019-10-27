package alemax.opengl;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Input {
	
	private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST + 1];
	private boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST + 1];
	private double mouseX, mouseY;
	
	private GLFWKeyCallback keyboard;
	private GLFWCursorPosCallback cursor;
	private GLFWMouseButtonCallback mouse;
	
	private long window;
	
	public Input(long window) {	
		
		this.window = window;
		
		keyboard = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				keys[key] = (action == GLFW.GLFW_PRESS);	
			}
		};
		
		cursor = new GLFWCursorPosCallback() {	
			@Override
			public void invoke(long window, double x, double y) {
				mouseX = x;
				mouseY = y;
			}
		};
		
		mouse = new GLFWMouseButtonCallback() {
			@Override
			public void invoke(long window, int button, int action, int mods) {
				buttons[button] = (action == GLFW.GLFW_PRESS);
			}
		};
		
		GLFW.glfwSetKeyCallback(window, keyboard);
		GLFW.glfwSetCursorPosCallback(window, cursor);
		GLFW.glfwSetMouseButtonCallback(window, mouse);
	}
	
	public void free() {
		keyboard.free();
		cursor.free();
		mouse.free();
	}
	

	public boolean isKeyDown(int key) {
		return keys[key];
	}

	public boolean isButtonDown(int button) {
		return buttons[button];
	}

	public double getMouseX() {
		return mouseX;
	}

	public double getMouseY() {
		return mouseY;
	}
	
}
