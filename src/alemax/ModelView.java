package alemax;

import alemax.model.Model;
import alemax.opengl.*;
import alemax.util.ModelUtil;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

public class ModelView {
	
	private ModelRenderer renderer;
	private Shader shader;
	private final Window window;
	private Camera camera;

	private RenderableModel model;
	
	public ModelView(Window window) {
		this.window = window;
		this.shader = new Shader("/shaders/vertex.glsl", "/shaders/fragment.glsl");
		shader.compile();
		this.renderer = new ModelRenderer(shader);

		this.camera = new Camera(window);
		this.camera.position = new Vector3f(0,0,200);

		/*
		Vertex[] vertices = {
				new Vertex(new Vector3f(-0.5f, 0.5f, 0f), new Vector4f(1f, 0f, 0f, 1f)),
				new Vertex(new Vector3f(0.5f, 0.5f, 0f), new Vector4f(0f, 1f, 0f, 1f)),
				new Vertex(new Vector3f(0.5f, -0.5f, 0f), new Vector4f(0f, 0f, 1f, 1f)),
				new Vertex(new Vector3f(-0.5f, -0.5f, 0f), new Vector4f(1f, 1f, 0f, 1f))};
		int[] indices = {0, 3, 1, 1, 3, 2 };
		model = new RenderableModel(vertices, indices);
		model.loadToMemory();
		*/

		shader.setUniform("uniColor", new Vector4f(1f, 0f, 0f, 1f));

		//model.position = new Vector3f(0.25f,0, -1);
	}

	public void setModel(Model model) {
		this.model = ModelUtil.convertToRenderable(model);
		this.model.loadToMemory();
	}

	private float time;

	private double lastMouseX;
	private double lastMouseY;
	private boolean isDown = true;

	public void update() {
		time = System.nanoTime() / 1000000000.0f;

		//model.rotation = new Vector3f(time, time, time);

		float moveSpeed = 1f;
		if(window.getInput().isKeyDown(GLFW.GLFW_KEY_LEFT_CONTROL))
			moveSpeed = 3f;

		if(window.getInput().isKeyDown(GLFW.GLFW_KEY_W))
			camera.moveForward(moveSpeed);
			//camera.position.add(0,0, -1f);

		if(window.getInput().isKeyDown(GLFW.GLFW_KEY_S))
			camera.moveBackward(moveSpeed);
			//camera.position.add(0,0, 1f);

		if(window.getInput().isKeyDown(GLFW.GLFW_KEY_A))
			camera.moveLeft(moveSpeed);
			//camera.position.add(-1f,0, 0);

		if(window.getInput().isKeyDown(GLFW.GLFW_KEY_D))
			camera.moveRight(moveSpeed);
			//camera.position.add(1f,0, 0);

		if(window.getInput().isKeyDown(GLFW.GLFW_KEY_SPACE))
			camera.moveUp(moveSpeed);
			//camera.position.add(0,1f, 0);

		if(window.getInput().isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT))
			camera.moveDown(moveSpeed);
			//camera.position.add(0,-1f, 0);

		if(window.getInput().isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
			if(!isDown) {
				isDown = true;
				lastMouseX = window.getInput().getMouseX();
				lastMouseY = window.getInput().getMouseY();
			} else {
				//camera.rotation.add(0, (float) (window.getInput().getMouseX() - lastMouseX) / 100f, 0);
				//camera.rotation.add((float) (window.getInput().getMouseY() - lastMouseY) / 100f, 0, 0);
				camera.rotateHorizontal((float) (window.getInput().getMouseX() - lastMouseX) / 100f);
				camera.rotateVertical((float) (window.getInput().getMouseY() - lastMouseY) / 100f);
				lastMouseX = window.getInput().getMouseX();
				lastMouseY = window.getInput().getMouseY();
			}
		} else {
			isDown = false;
		}

		renderer.renderModel(model, camera);
		
	}
	
	public void destroy() {
		model.unload();
		shader.delete();
	}
	
}
