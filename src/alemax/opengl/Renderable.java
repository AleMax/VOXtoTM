package alemax.opengl;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public class Renderable {
	
	private Vertex[] vertices;
	private int[] indices;

	private int VAO;
	private int indexBuffer;
	private int positionBuffer;
	private int normalBuffer;
	private int colorBuffer;
	
	public enum VBOIndex {
		POSITION(0),
		NORMAL(1),
		COLOR(2);
		
		private int numVal;

		VBOIndex(int numVal) {
	        this.numVal = numVal;
	    }

	    public int value() {
	        return numVal;
	    }
	}

	
	public Renderable(Vertex[] vertices, int[] indices) {
		this.indices = indices;
		this.vertices = vertices;
	}
	
	public void loadToMemory() {
		VAO = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(VAO);
		
		loadIndices();
		loadPositions();
		
		GL30.glBindVertexArray(0);
	}
	
	private void loadIndices() {
		IntBuffer indices = MemoryUtil.memAllocInt(this.indices.length);
		indices.put(this.indices);
		indices.flip();
		
		indexBuffer = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBuffer);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	private void loadPositions() {
		FloatBuffer positions = MemoryUtil.memAllocFloat(vertices.length * 3);
		float[] positionData = new float[vertices.length * 3];
		for(int i = 0; i < vertices.length; i++) {
			positionData[i * 3] = vertices[i].position.x; 
			positionData[i * 3 + 1] = vertices[i].position.y;
			positionData[i * 3 + 2] = vertices[i].position.z;
		}
		positions.put(positionData);
		positions.flip();
		
		positionBuffer = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, positionBuffer);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, positions, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(VBOIndex.POSITION.value(), 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	public int getVAO() {
		return VAO;
	}

	public int getIndexBuffer() {
		return indexBuffer;
	}

	public int getPositionBuffer() {
		return positionBuffer;
	}

	public int getNormalBuffer() {
		return normalBuffer;
	}

	public int getColorBuffer() {
		return colorBuffer;
	}

	public int[] getIndices() {
		return indices;
	}

	public Vertex[] getVertices() {
		return vertices;
	}
	
	
	
}
