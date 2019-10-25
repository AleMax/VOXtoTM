package alemax.vox;

import alemax.ByteHandler;

public class VoxChunkPack extends VoxChunk {
	
	public int numModels;
	
	public VoxChunkPack(byte[] voxData, int index) {
		super(voxData, index);
		index += 12;
		
		numModels = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
	}
	
}
