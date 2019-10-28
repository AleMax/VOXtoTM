package alemax.opengl;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLXARBGetProcAddress;
import org.lwjgl.system.MemoryUtil;

import alemax.util.ShaderHandler;

public class Shader {
	
	private int program;
	
	private int vertexID;
	private int fragmentID;
	
	private String vertexPath;
	private String fragmentPath;
	
	private ShaderHandler shaderHandler;
	
	public Shader(String vertexPath, String fragmentPath) {
		this.vertexPath = vertexPath;
		this.fragmentPath = fragmentPath;
		shaderHandler = new ShaderHandler();
	}
	
	public void compile() {
		
		String vertexSource = shaderHandler.loadShader(vertexPath);
		String fragmentSource = shaderHandler.loadShader(fragmentPath);
		
		program = GL20.glCreateProgram();
		
		vertexID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		GL20.glShaderSource(vertexID, vertexSource);
		GL20.glCompileShader(vertexID);
		
		if(GL20.glGetShaderi(vertexID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Error compiling Vertex Shader: ");
			System.err.println(GL20.glGetShaderInfoLog(vertexID));
			return;
		}
		
		fragmentID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		GL20.glShaderSource(fragmentID, fragmentSource);
		GL20.glCompileShader(fragmentID);
		
		if(GL20.glGetShaderi(fragmentID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Error compiling Fragment Shader:");
			System.err.println(GL20.glGetShaderInfoLog(fragmentID));
			return;
		}
		
		GL20.glAttachShader(program, vertexID);
		GL20.glAttachShader(program, fragmentID);
		
		GL20.glLinkProgram(program);
		
		if(GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
			System.err.println("Error linking shader program:");
			System.err.println(GL20.glGetProgramInfoLog(program));
			return;
		}
		
		GL20.glValidateProgram(program);
		
		if(GL20.glGetProgrami(program, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Error validating shader program:");
			System.err.println(GL20.glGetProgramInfoLog(program));
			return;
		}

	}
	
	private int getUniformLocation(String uniform) {
		return GL20.glGetUniformLocation(program, uniform);
	}
	
	public void setUniform(String uniform, Vector3f value) {
		GL20.glUniform3f(getUniformLocation(uniform), value.x, value.y, value.z);
	}
	
	public void setUniform(String uniform, Vector4f value) {
		GL20.glUniform4f(getUniformLocation(uniform), value.x, value.y, value.z, value.w);
	}
	
	public void setUniform(String uniform, Matrix4f value) {
		FloatBuffer matrix = MemoryUtil.memAllocFloat(16);
		value.get(matrix);
		matrix.flip();
		GL20.glUniformMatrix4fv(getUniformLocation(uniform), true, matrix);
	}
	
	
	public void bind() {
		GL20.glUseProgram(program);
	}
	
	public void unbind() {
		GL20.glUseProgram(0);
	}
	
	public void delete() {
		GL20.glDetachShader(program, vertexID);
		GL20.glDetachShader(program, fragmentID);
		GL20.glDeleteShader(vertexID);
		GL20.glDeleteShader(fragmentID);
		GL20.glDeleteProgram(program);
	}
	
	
}
