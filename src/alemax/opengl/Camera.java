package alemax.opengl;

import alemax.util.ResizeListener;
import org.joml.Vector3f;

public class Camera implements ResizeListener {

    private static final float STANDARD_FOV = (float) Math.toRadians(70);
    private static final float STANDARD_NEAR = 0.1f;
    private static final float STANDARD_FAR = 2000;

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

        position = new Vector3f();
        rotation = new Vector3f();
    }

    public void rotateHorizontal(float amount) {
        rotation.add(0, amount, 0);
    }

    public void rotateVertical(float amount) {
        rotation.add(amount, 0, 0);
        if(rotation.x > Math.toRadians(90)) rotation.x = (float) Math.toRadians(90);
        if(rotation.x < Math.toRadians(-90)) rotation.x = (float) Math.toRadians(-90);
    }

    public void moveForward(float amount) {
        this.position.add(getForwardVector(amount));
    }

    public void moveBackward(float amount) {
        this.position.add(getForwardVector(-amount));
    }

    public void moveLeft(float amount) {
        Vector3f left = getForwardVector(amount);
        left.y = 0;
        left.rotateY((float) Math.toRadians(90));
        this.position.add(left);
    }

    public void moveRight(float amount) {
        Vector3f right = getForwardVector(amount);
        right.y = 0;
        right.rotateY((float) Math.toRadians(-90));
        this.position.add(right);
    }

    public void moveUp(float amount) {
        this.position.add(new Vector3f(0, amount, 0));
    }

    public void moveDown(float amount) {
        this.position.add(new Vector3f(0, -amount, 0));
    }

    private Vector3f getForwardVector(float amount) {
        Vector3f forward = new Vector3f(0,0, -amount);
        forward.rotateX(-rotation.x);
        forward.rotateY(-rotation.y);
        forward.rotateZ(-rotation.z);
        return forward;
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
