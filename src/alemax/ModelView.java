package alemax;

import org.joml.Vector3f;
import org.joml.Vector4f;

import alemax.opengl.ModelRenderer;
import alemax.opengl.RenderableModel;
import alemax.opengl.Shader;
import alemax.opengl.Vertex;

public class ModelView {
	
	private ModelRenderer renderer;
	private Shader shader;
	
	private RenderableModel model;
	
	public ModelView() {
		this.shader = new Shader("/shaders/vertex.glsl", "/shaders/fragment.glsl");
		shader.compile();
		this.renderer = new ModelRenderer(shader);
		
		Vertex[] vertices = {
				new Vertex(new Vector3f(-0.5f, 0.5f, 0f), new Vector4f(1f, 0f, 0f, 1f)), 
				new Vertex(new Vector3f(0.5f, 0.5f, 0f), new Vector4f(0f, 1f, 0f, 1f)), 
				new Vertex(new Vector3f(0.5f, -0.5f, 0f), new Vector4f(0f, 0f, 1f, 1f)), 
				new Vertex(new Vector3f(-0.5f, -0.5f, 0f), new Vector4f(1f, 1f, 0f, 1f))};
		int[] indices = {0, 3, 1, 1, 3, 2 };
		model = new RenderableModel(vertices, indices);
		model.loadToMemory();
		
		shader.setUniform("uniColor", new Vector4f(1f, 0f, 0f, 1f));


	}

	private float time;

	public void update() {

		time = System.nanoTime() / 1000000000.0f;

		model.rotation = new Vector3f(time, time, time);

		renderer.renderModel(model);
		
	}
	
	public void destroy() {
		model.unload();
		shader.delete();
	}
	
}
