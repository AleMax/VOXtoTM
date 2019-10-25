package alemax.vox;

import alemax.ByteHandler;

public class VoxChunkXYZI extends VoxChunk {
	
	public Voxel[] voxels;
	
	public VoxChunkXYZI(byte[] voxData, int index) {
		super(voxData, index);
		index += 12;
		
		voxels = new Voxel[ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4))]; 
		
		for(int i = 0; i < voxels.length; i++) {
			voxels[i] = new Voxel();
			voxels[i].x = ByteHandler.getInt8(ByteHandler.getSubArray(voxData, index++, 1));
			voxels[i].z = ByteHandler.getInt8(ByteHandler.getSubArray(voxData, index++, 1)); //And again: the swap of z and y is intended! Its for the update of the gravitational axis from z to y
			voxels[i].y = ByteHandler.getInt8(ByteHandler.getSubArray(voxData, index++, 1));
			voxels[i].i = ByteHandler.getInt8(ByteHandler.getSubArray(voxData, index++, 1));
		}
		
	}
	
	
}
