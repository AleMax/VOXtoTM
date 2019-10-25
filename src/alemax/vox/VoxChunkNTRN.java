package alemax.vox;

import alemax.ByteHandler;

public class VoxChunkNTRN extends VoxChunk {

	public boolean isValid;
	
	public int nodeID;
	public VoxDict nodeAttributes;
	public int childNodeID;
	public int reservedID;
	public int layerID;
	public int frameCount;
	public VoxDict[] frameAttributes;
	
	
	public VoxChunkNTRN(byte[] voxData, int index) {
		super(voxData, index);
		index += 12;
		
		nodeID = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		nodeAttributes = new VoxDict(voxData, index + 4);
		index += (nodeAttributes.rawData.length + 4);
		childNodeID = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		reservedID = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 4, 4));
		layerID = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 8, 4));
		frameCount = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 12, 4));
		
		if(reservedID == -1 && frameCount == 1)
			isValid = true;
		else
			isValid = false;
		
		index += 16;
		frameAttributes = new VoxDict[frameCount];
		for(int i = 0; i < frameCount; i++) {
			frameAttributes[i] = new VoxDict(voxData, index);
			index += frameAttributes[i].rawData.length;
		}
		
	}
	
	
}
