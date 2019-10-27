package alemax.opengl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class ModelRenderer {
	
	private Shader shader;
	
	public ModelRenderer(Shader shader) {
		this.shader = shader;
	}
	
	public void renderModel(Renderable model) {
		GL30.glBindVertexArray(model.getVAO());
		
		GL30.glEnableVertexAttribArray(Renderable.VBOIndex.POSITION.value());
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, model.getIndexBuffer());
		
		shader.bind();
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		
		shader.unbind();
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL30.glDisableVertexAttribArray(Renderable.VBOIndex.POSITION.value());
		
		GL30.glBindVertexArray(0);
	}
	
}
