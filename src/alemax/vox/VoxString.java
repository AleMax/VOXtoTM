package alemax.vox;

import alemax.ByteHandler;

public class VoxString extends VoxPart {

	public String string;
	
	public VoxString(byte[] voxData, int index) {
		super(voxData, index);
		int bufferSize = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		if(bufferSize > 0) {
			string = ByteHandler.getString(ByteHandler.getSubArray(voxData, index + 4, bufferSize));
		} else {
			string = "";
		}
		
		rawData = ByteHandler.getSubArray(voxData, index, bufferSize + 4);
	}

	
	
	public static VoxString create(byte[] voxData, int index) {
		
		VoxString voxString = new VoxString(voxData, index);

		
		int bufferSize = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		if(bufferSize > 0) {
			voxString.string = ByteHandler.getString(ByteHandler.getSubArray(voxData, index + 4, bufferSize));
		} else {
			voxString.string = "";
		}
		
		voxString.rawData = ByteHandler.getSubArray(voxData, index, bufferSize + 4);
		
		
		return voxString;
	}
	
	
	
}
