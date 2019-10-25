package alemax.model;

import org.apache.commons.math3.linear.MatrixUtils;

public class StrippedChunk {

	private int sizeX;
	private int sizeY; //Here the y Direction is the gravity direction!
	private int sizeZ;
	
	private Voxel[] originalVoxels;
	
	public StrippedChunk() {
		originalVoxels = new Voxel[0];

		sizeX = 0;
		sizeY = 0;
		sizeZ = 0;

	}
	
	public void setVoxels(Voxel[] voxels) {
		originalVoxels = voxels;
	}
	
	public void setSize(int sizeX, int sizeY, int sizeZ) {
		this.sizeX = sizeX;
		this.sizeZ = sizeY; //Swap is intendet!! Dont ever change it, it is to make the y-z switch
		this.sizeY = sizeZ;
	}
	
	public int getSizeX() {
		return sizeX;
	}
	
	public int getSizeY() {
		return sizeY;
	}
	
	public int getSizeZ() {
		return sizeZ;
	}
	
	public Voxel[] getVoxels() {
		return originalVoxels;
	}
}
