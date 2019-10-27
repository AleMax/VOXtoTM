package alemax.opengl;

import org.joml.Vector3f;
import org.joml.Vector4f;

public class Vertex {
	
	//To be honest, i also could just use 1 byte for the normal and color, but is this performance gain really that impactful? Still can do it later if needed
	
	Vector3f position;
	Vector3f normal;
	Vector4f color;
	
	
	public Vertex(Vector3f position, Vector4f color) {
		this.position = position;
		this.normal = normal;
		this.color = color;
	}
	
	
	
}
