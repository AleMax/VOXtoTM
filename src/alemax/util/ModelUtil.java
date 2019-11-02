package alemax.util;

import alemax.model.Chunk;
import alemax.model.Model;
import alemax.model.Voxel;
import alemax.opengl.RenderableModel;
import alemax.opengl.Vertex;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;

public class ModelUtil {

    public static RenderableModel convertToRenderable(Model model) {

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Integer> indices = new ArrayList<Integer>();

        for(Chunk chunk : model.chunks) {
            for(Voxel voxel : chunk.voxels) {
                Vector4f color = new Vector4f(model.colors[voxel.i]);
                color.mul(1f / 255);
                color.w = 1f;
                //Vector4f color = new Vector4f(1,0,0,1);

                vertices.add(new Vertex(new Vector3f(voxel.x, voxel.y, voxel.z), color));
                vertices.add(new Vertex(new Vector3f(voxel.x + 1, voxel.y, voxel.z), color));
                vertices.add(new Vertex(new Vector3f(voxel.x + 1, voxel.y + 1, voxel.z), color));
                vertices.add(new Vertex(new Vector3f(voxel.x, voxel.y + 1, voxel.z), color));

                vertices.add(new Vertex(new Vector3f(voxel.x, voxel.y, voxel.z + 1), color));
                vertices.add(new Vertex(new Vector3f(voxel.x + 1, voxel.y, voxel.z + 1), color));
                vertices.add(new Vertex(new Vector3f(voxel.x + 1, voxel.y + 1, voxel.z + 1), color));
                vertices.add(new Vertex(new Vector3f(voxel.x, voxel.y + 1, voxel.z + 1), color));

                int index = vertices.size() - 8;

                indices.add(index + 0); //BACK
                indices.add(index + 1);
                indices.add(index + 2);
                indices.add(index + 0);
                indices.add(index + 2);
                indices.add(index + 3);

                indices.add(index + 4); //FRONT
                indices.add(index + 5);
                indices.add(index + 6);
                indices.add(index + 4);
                indices.add(index + 6);
                indices.add(index + 7);

                indices.add(index + 7); //TOP
                indices.add(index + 6);
                indices.add(index + 2);
                indices.add(index + 7);
                indices.add(index + 2);
                indices.add(index + 3);

                indices.add(index + 4); //BOTTOM
                indices.add(index + 5);
                indices.add(index + 1);
                indices.add(index + 4);
                indices.add(index + 1);
                indices.add(index + 0);

                indices.add(index + 0); //LEFT
                indices.add(index + 3);
                indices.add(index + 7);
                indices.add(index + 0);
                indices.add(index + 7);
                indices.add(index + 4);

                indices.add(index + 5); //RIGHT
                indices.add(index + 1);
                indices.add(index + 2);
                indices.add(index + 5);
                indices.add(index + 2);
                indices.add(index + 6);

            }
        }

        Vertex[] vertexArray = new Vertex[vertices.size()];
        int[] indexArray = new int[indices.size()];

        for(int i = 0; i < vertexArray.length; i++) {
            vertexArray[i] = vertices.get(i);
        }

        for(int i = 0; i < indexArray.length; i++) {
            indexArray[i] = indices.get(i);
        }

        return new RenderableModel(vertexArray, indexArray);
    }

}
