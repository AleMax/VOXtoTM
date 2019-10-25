package alemax.model;

import java.util.ArrayList;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class Chunk {
	
	public int sizeX;
	public int sizeY; //Here the y Direction is the gravity direction!
	public int sizeZ;
	
	public Voxel[] voxels; //Already updated voxels with translation , y-z switch and rotation taken into account
	
	private boolean baked;
	
	private int translateX;
	private int translateY; //Every private property has still the wrong y-z in it. (Z: gravity)
	private int translateZ;
	
	private RealMatrix rotMatrix;
	
	private Voxel[] originalVoxels;
	
	public Chunk() {
		baked = false;
		
		originalVoxels = new Voxel[0];
		
		translateX = 0;
		translateY = 0;
		translateZ = 0;
		
		sizeX = 0;
		sizeY = 0;
		sizeZ = 0;
		
		double[][] bytes = {{0,0,0},{0,0,0},{0,0,0}};
		rotMatrix = MatrixUtils.createRealMatrix(bytes);
	}
	
	public void setVoxels(Voxel[] voxels) {
		originalVoxels = voxels;
	}
	
	public void setSize(int sizeX, int sizeY, int sizeZ) {
		if(!baked) {
			this.sizeX = sizeX;
			this.sizeZ = sizeY; //Swap is intendet!! Dont ever change it, it is to make the y-z switch
			this.sizeY = sizeZ;
		}
	}
	
	public void setRotation(RealMatrix rotMatrix) {
		if(!baked) {
			this.rotMatrix = rotMatrix;
		}
	}
	
	public void setTranslation(int translateX, int translateY, int translateZ) {
		if(!baked) {
			this.translateX = translateX;
			this.translateY = translateY;
			this.translateZ = translateZ;
		}
	}
	
	public void bake() {
		if(!baked) {
			baked = true;
			
			voxels = new Voxel[originalVoxels.length];
			for(int i = 0; i < originalVoxels.length; i++) {
				voxels[i] = new Voxel();
			}
			
			//Rotation:
			Vector3D rotMiddlePoint = new Vector3D((sizeX / 2.0) - 0.5, (sizeZ / 2.0) - 0.5, (sizeY / 2.0) - 0.5); //Switchup intended again, cuz we have to make it wrong again here
			
			for(int i = 0; i < originalVoxels.length; i++) {
				double[] toMiddleArray = {originalVoxels[i].x - rotMiddlePoint.getX(),originalVoxels[i].y - rotMiddlePoint.getY(), originalVoxels[i].z - rotMiddlePoint.getZ()};
				RealVector toMiddle = new ArrayRealVector(toMiddleArray);
				RealVector rotated = rotMatrix.preMultiply(toMiddle);
				voxels[i].x = (int) Math.round(originalVoxels[i].x + rotated.getEntry(0));
				voxels[i].y = (int) Math.round(originalVoxels[i].x + rotated.getEntry(1));
				voxels[i].z = (int) Math.round(originalVoxels[i].x + rotated.getEntry(2));
				voxels[i].i = originalVoxels[i].i;
			}
			
			//Translation:
			for(int i = 0; i < originalVoxels.length; i++) {
				voxels[i].x += translateX;
				voxels[i].y += translateY;
				voxels[i].z += translateZ;
			}
			
			//Adjusting Y-Z:
			//New - Old
			// X = X
			// Y = -Z
			// Z = Y
			for(int i = 0; i < originalVoxels.length; i++) {
				int oldY = voxels[i].y;
				voxels[i].y += -voxels[i].z;
				voxels[i].z = oldY;
			}
		}
	}
	
}
