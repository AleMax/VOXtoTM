package alemax.vox;

import alemax.util.ByteHandler;
import org.joml.Vector4f;

public class VoxChunkRGBA extends VoxChunk {
	
	public Vector4f[] palette;
	
	public VoxChunkRGBA(byte[] voxData, int index) {
		super(voxData, index);
		index += 12;
		palette = new Vector4f[256];
		
		for(int i = 0; i < palette.length; i++) {
			palette[i] = new Vector4f();
			palette[i].x = ByteHandler.getInt8(ByteHandler.getSubArray(voxData, index++, 1));
			palette[i].y = ByteHandler.getInt8(ByteHandler.getSubArray(voxData, index++, 1));
			palette[i].z = ByteHandler.getInt8(ByteHandler.getSubArray(voxData, index++, 1));
			palette[i].w = ByteHandler.getInt8(ByteHandler.getSubArray(voxData, index++, 1));
		}
		
		
	}
	
}
