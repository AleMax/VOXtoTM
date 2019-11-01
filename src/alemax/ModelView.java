package alemax;

import alemax.opengl.*;
import org.joml.Vector3f;
import org.joml.Vector4f;

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

		Vertex[] vertices = {
				new Vertex(new Vector3f(-0.5f, 0.5f, 0f), new Vector4f(1f, 0f, 0f, 1f)), 
				new Vertex(new Vector3f(0.5f, 0.5f, 0f), new Vector4f(0f, 1f, 0f, 1f)), 
				new Vertex(new Vector3f(0.5f, -0.5f, 0f), new Vector4f(0f, 0f, 1f, 1f)), 
				new Vertex(new Vector3f(-0.5f, -0.5f, 0f), new Vector4f(1f, 1f, 0f, 1f))};
		int[] indices = {0, 3, 1, 1, 3, 2 };
		model = new RenderableModel(vertices, indices);
		model.loadToMemory();
		
		shader.setUniform("uniColor", new Vector4f(1f, 0f, 0f, 1f));

		model.position = new Vector3f(0,0, -5);
	}

	private float time;

	public void update() {
		time = System.nanoTime() / 1000000000.0f;

		model.rotation = new Vector3f(time, time, time);

		renderer.renderModel(model, camera);
		
	}
	
	public void destroy() {
		model.unload();
		shader.delete();
	}
	
}
