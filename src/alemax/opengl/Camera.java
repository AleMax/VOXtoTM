package alemax.opengl;

import alemax.util.ResizeListener;

public class Camera implements ResizeListener {

    private static final float STANDARD_FOV = 70;
    private static final float STANDARD_NEAR = 0.1f;
    private static final float STANDARD_FAR = 150;

    private float FOV;
    private float aspectRatio;
    private float near;
    private float far;

    public Camera(Window window) {
        aspectRatio = window.getWidth() / 1f / window.getHeight();
        window.addResizeListener(this);
        FOV = STANDARD_FOV;
        near = STANDARD_NEAR;
        far = STANDARD_FAR;
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
