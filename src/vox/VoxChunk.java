package vox;

import alemax.ByteHandler;

public abstract class VoxChunk extends VoxPart {

	public String chunkID;
	public int contentSize;
	public int childrenCount;
	public VoxChunk[] childrenChunks;
	

	public VoxChunk(byte[] voxData, int index) {
		super(voxData, index);
		chunkID = ByteHandler.getString(ByteHandler.getSubArray(voxData, index, 4));
		contentSize = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 4, 4));
		childrenCount = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 8, 4));
		
		childrenChunks = new VoxChunk[childrenCount];
		//TODO: ChildrenChunk
	}
	
	//Returns the new index for the "chunkContent"
	public static int createBasics(VoxChunk voxChunk, byte[] voxData, int index) {
		
		voxChunk.chunkID = ByteHandler.getString(ByteHandler.getSubArray(voxData, index, 4));
		voxChunk.contentSize = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 4, 4));
		voxChunk.childrenCount = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 8, 4));
		
		return index += 12;
	}
	
}
