#version 400 core

layout(location = 0) in vec3 inPosition;
layout(location = 2) in vec4 inColor;

out vec4 color;

uniform vec4 uniColor;
uniform mat4 mvpMatrix;

void main() {

	gl_Position = vec4(inPosition, 1.0) * mvpMatrix;
	color = uniColor;

}
