package alemax.vox;

import alemax.util.ByteHandler;

public class VoxChunkNSHP extends VoxChunk {

	public boolean isValid;
	
	public int nodeID;
	public VoxDict nodeAttributes;
	public int modelCount;
	
	public int[] modelIDs;
	public VoxDict[] modelAttributes;
	
	public VoxChunkNSHP(byte[] voxData, int index) {
		super(voxData, index);
		index += 12;
		
		nodeID = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		nodeAttributes = new VoxDict(voxData, index + 4);
		index += (nodeAttributes.rawData.length + 4);
		modelCount = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		
		if(modelCount == 1)
			isValid = true;
		else
			isValid = false;
		
		index += 4;
		modelIDs = new int[modelCount];
		modelAttributes = new VoxDict[modelCount];
		for(int i = 0; i < modelCount; i++) {
			modelIDs[i] = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
			modelAttributes[i] = new VoxDict(voxData, index + 4);
			index += (modelAttributes[i].rawData.length + 4);
		}
		
	}
	
	
}
