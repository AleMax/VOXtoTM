package alemax.opengl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLXARBGetProcAddress;

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
		
		GL20.glDeleteShader(vertexID);
		GL20.glDeleteShader(fragmentID);
		
	}
	
	public void bind() {
		GL20.glUseProgram(program);
	}
	
	public void unbind() {
		GL20.glUseProgram(0);
	}
	
	public void delete() {
		GL20.glDeleteProgram(program);
	}
	
	
}
