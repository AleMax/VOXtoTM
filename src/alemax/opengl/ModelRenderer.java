package alemax.opengl;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import sun.text.resources.ro.FormatData_ro;

public class ModelRenderer {
	
	private Shader shader;
	
	public ModelRenderer(Shader shader) {
		this.shader = shader;
	}
	
	public void renderModel(RenderableModel model) {
		GL30.glBindVertexArray(model.getVAO());
		GL30.glEnableVertexAttribArray(RenderableModel.VBOIndex.POSITION.value());
		GL30.glEnableVertexAttribArray(RenderableModel.VBOIndex.COLOR.value());
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, model.getIndexBuffer());
		shader.bind();
		
		shader.setUniform("uniColor", new Vector4f(1f, 1f, 1f, 1f));
		shader.setUniform("mvpMatrix", createMVP(model));
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getIndices().length, GL11.GL_UNSIGNED_INT, 0);

		shader.unbind();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		GL30.glDisableVertexAttribArray(RenderableModel.VBOIndex.POSITION.value());
		GL30.glDisableVertexAttribArray(RenderableModel.VBOIndex.COLOR.value());
		GL30.glBindVertexArray(0);
	}

	private Matrix4f createMVP(RenderableModel model) {
		Matrix4f mvp = new Matrix4f();

		mvp.translate(model.position);
		mvp.rotate(model.rotation.x, 1,0,0);
		mvp.rotate(model.rotation.y, 0,1,0);
		mvp.rotate(model.rotation.z, 0,0,1);
		mvp.scale(model.scale);

		return mvp;
	}
	
}
