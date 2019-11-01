package alemax.opengl;

import alemax.util.ResizeListener;
import org.joml.Vector3f;

public class Camera implements ResizeListener {

    private static final float STANDARD_FOV = (float) Math.toRadians(70);
    private static final float STANDARD_NEAR = 0.1f;
    private static final float STANDARD_FAR = 150;

    private float FOV;
    private float aspectRatio;
    private float near;
    private float far;

    public Vector3f position;
    public Vector3f rotation;

    public Camera(Window window) {
        aspectRatio = window.getWidth() / 1f / window.getHeight();
        window.addResizeListener(this);
        FOV = STANDARD_FOV;
        near = STANDARD_NEAR;
        far = STANDARD_FAR;

        position = new Vector3f(0,4,5);
        rotation = new Vector3f((float) Math.toRadians(45),(float) Math.toRadians(0),(float) Math.toRadians(0));
    }


    @Override
    public void windowResized(int width, int height) {
        aspectRatio = width / 1f / height;
    }

    public float getFOV() {
        return FOV;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public float getFar() {
        return far;
    }

    public float getNear() {
        return near;
    }

}
