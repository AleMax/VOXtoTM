package alemax.vox;

import java.util.ArrayList;

import alemax.ByteHandler;

public abstract class VoxChunk extends VoxPart {

	public String chunkID;
	public int contentSize;
	public int childrenSize;
	public ArrayList<VoxChunk> childrenChunks;
	

	public VoxChunk(byte[] voxData, int index) {
		super(voxData, index);
		int startIndex = index;
		
		int byteSize = 12;
		
		chunkID = ByteHandler.getString(ByteHandler.getSubArray(voxData, index, 4));
		contentSize = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 4, 4));
		childrenSize = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index + 8, 4));
		
		byteSize += contentSize;
		
		index += (contentSize + 12);
		childrenChunks = new ArrayList<VoxChunk>();
		//TODO: ChildrenChunk
		while ((index - (contentSize + 8)) < childrenSize) {
			String chunkID = ByteHandler.getString(ByteHandler.getSubArray(voxData, index, 4));
			System.out.println(chunkID + "\t" + index);
			
			VoxChunk childChunk = null;
			switch(chunkID) {
			case "MAIN":
				childChunk = new VoxChunkMain(voxData, index);
				break;
			case "PACK":
				childChunk = new VoxChunkPack(voxData, index);
				break;
			case "SIZE":
				childChunk = new VoxChunkSize(voxData, index);
				break;
			case "XYZI":
				childChunk = new VoxChunkXYZI(voxData, index);
				break;
			case "RGBA":
				childChunk = new VoxChunkRGBA(voxData, index);
				break;
			case "nTRN":
				childChunk = new VoxChunkNTRN(voxData, index);
				break;
			case "nGRP":
				childChunk = new VoxChunkNGRP(voxData, index);
				break;
			case "nSHP":
				childChunk = new VoxChunkNSHP(voxData, index);
				break;
			case "MATL":
				childChunk = new VoxChunkMATL(voxData, index);
				break;
			case "LAYR":
				childChunk = new VoxChunkLAYR(voxData, index);
				break;
			case "rOBJ":
				childChunk = new VoxChunkROBJ(voxData, index);
				break;
			default:
				childChunk = new VoxChunkMain(voxData, index);
				System.err.println(chunkID);
			}	
			
			byteSize += childChunk.rawData.length;
			index += childChunk.rawData.length;
		}
		
		this.rawData = ByteHandler.getSubArray(voxData, startIndex, byteSize);
		
	}
	
}
