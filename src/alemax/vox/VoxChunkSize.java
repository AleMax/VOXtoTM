package alemax.vox;

import alemax.util.ByteHandler;

public class VoxChunkSize extends VoxChunk {

	public int sizeX;
	public int sizeY;
	public int sizeZ; //Gravity Direction: In .vox [z] is the gravity direction this will get converted later
	
	
	public VoxChunkSize(byte[] voxData, int index) {
		super(voxData, index);
		index += 12;
		
		sizeX = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		sizeY = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 4, 4));
		sizeZ = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 8, 4));
	}
	
	
}
