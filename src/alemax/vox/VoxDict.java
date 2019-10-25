package alemax.vox;

import java.util.HashMap;

import alemax.ByteHandler;

public class VoxDict extends VoxPart {

	public HashMap<String, VoxString> map;
	
	
	public VoxDict(byte[] voxData, int index) {
		super(voxData, index);
		map	= new HashMap<String, VoxString>();
		
		int byteSize = 4;
		int keyValuePairCount = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		
		for(int i = 0; i < keyValuePairCount; i++) {
			
			VoxString key = VoxString.create(voxData, index + byteSize);
			String keyString = key.string;
			byteSize += key.rawData.length;
			
			VoxString value = VoxString.create(voxData, index + byteSize);
			byteSize += value.rawData.length;
			
			map.put(keyString, value);
		}
		
		rawData = ByteHandler.getSubArray(voxData, index, byteSize);
	}
	
}
