package vox;

import alemax.ByteHandler;

public class VoxChunkRGBA extends VoxChunk {
	
	public Color[] palette;
	
	public VoxChunkRGBA(byte[] voxData, int index) {
		super(voxData, index);
		index += 12;
		palette = new Color[256];
		
		for(int i = 0; i < palette.length; i++) {
			palette[i].R = ByteHandler.getInt8(ByteHandler.getSubArray(voxData, index++, 1));
			palette[i].G = ByteHandler.getInt8(ByteHandler.getSubArray(voxData, index++, 1));
			palette[i].B = ByteHandler.getInt8(ByteHandler.getSubArray(voxData, index++, 1));
			palette[i].A = ByteHandler.getInt8(ByteHandler.getSubArray(voxData, index++, 1));
		}
		
		
	}
	
}
