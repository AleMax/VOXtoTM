package vox;

import alemax.ByteHandler;

public class VoxString extends VoxPart {

	public String string;
	
	
	public static VoxString create(byte[] voxData, int index) {
		
		VoxString voxString = new VoxString();
		voxString.rawData = voxData;
		
		int bufferSize = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		if(bufferSize > 0) {
			voxString.string = ByteHandler.getString(ByteHandler.getSubArray(voxData, index + 4, bufferSize));
		} else {
			voxString.string = "";
		}
		
		
		return voxString;
	}
	
	
	
}
