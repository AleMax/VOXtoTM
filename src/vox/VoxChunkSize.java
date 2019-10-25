package vox;

import alemax.ByteHandler;

public class VoxChunkSize extends VoxChunk {

	public int sizeX;
	public int sizeY; //Gravity Direction: In .vox [z] is the gravity direction this will get converted here in the create function
	public int sizeZ;
	
	
	public VoxChunkSize(byte[] voxData, int index) {
		super(voxData, index);
		index += 12;
		
		sizeX = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		sizeZ = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 4, 4)); //Important: Z and Y should be swapped like here, dont ever(!) reverse it!
		sizeY = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 8, 4));
	}
	
	public static VoxChunkSize create(byte[] voxData, int index) {
		
		VoxChunkSize voxSize = new VoxChunkSize(voxData, index);
		index = VoxChunk.createBasics(voxSize, voxData, index);
		
		voxSize.sizeX = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		voxSize.sizeZ = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 4, 4)); //Important: Z and Y should be swapped like here, dont ever(!) reverse it!
		voxSize.sizeY = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 8, 4));
		
		return null;
	}
	
	
}
