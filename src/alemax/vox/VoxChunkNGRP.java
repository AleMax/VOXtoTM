package alemax.vox;

import alemax.util.ByteHandler;

public class VoxChunkNGRP extends VoxChunk {

	public int nodeID;
	public VoxDict nodeAttributes;
	public int childCount;
	public int[] childNodeIDs;
	
	public VoxChunkNGRP(byte[] voxData, int index) {
		super(voxData, index);
		index += 12;
		
		nodeID = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		nodeAttributes = new VoxDict(voxData, index + 4);
		index += (nodeAttributes.rawData.length + 4);
		childCount = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		
		index += 4;
		childNodeIDs = new int[childCount];
		for(int i = 0; i < childCount; i++) {
			childNodeIDs[i] = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
			index += 4;
		}
		
	}
	
	
	
}
