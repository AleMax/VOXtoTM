package alemax;

import org.joml.Vector3f;

import alemax.opengl.ModelRenderer;
import alemax.opengl.Renderable;
import alemax.opengl.Shader;
import alemax.opengl.Vertex;

public class ModelView {
	
	private ModelRenderer renderer;
	private Shader shader;
	
	private Renderable model;
	
	public ModelView() {
		this.shader = new Shader("/shaders/vertex.glsl", "/shaders/fragment.glsl");
		shader.compile();
		this.renderer = new ModelRenderer(shader);
		
		Vertex[] vertices = {new Vertex(new Vector3f(-0.5f, 0.5f, 0f)), new Vertex(new Vector3f(0.5f, 0.5f, 0f)), new Vertex(new Vector3f(0.5f, -0.5f, 0f)), new Vertex(new Vector3f(-0.5f, -0.5f, 0f))};
		int[] indices = {0, 3, 1, 1, 3, 2 };
		model = new Renderable(vertices, indices);
		model.loadToMemory();
		
	}
	
	public void update() {
		
		renderer.renderModel(model);
		
	}
	
}
