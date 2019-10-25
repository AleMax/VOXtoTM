package vox;

import java.util.HashMap;

import alemax.ByteHandler;

public class VoxDict extends VoxPart {

	public HashMap<VoxString, VoxString> map;
	
	
	public VoxDict(byte[] voxData, int index) {
		super(voxData, index);
		map	= new HashMap<VoxString, VoxString>();
		
		int byteSize = 4;
		int keyValuePairCount = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		
		for(int i = 0; i < keyValuePairCount; i++) {
			
			VoxString key = VoxString.create(voxData, index + byteSize);
			byteSize += key.rawData.length;
			
			VoxString value = VoxString.create(voxData, index + byteSize);
			byteSize += value.rawData.length;
			
			map.put(key, value);
		}
		
		rawData = ByteHandler.getSubArray(voxData, index, byteSize);
	}
	
	public static VoxDict create(byte[] voxData, int index) {
		
		VoxDict voxDict = new VoxDict(voxData, index);
		voxDict.map	= new HashMap<VoxString, VoxString>();
		
		int byteSize = 4;
		int keyValuePairCount = ByteHandler.getInt32(ByteHandler.getSubArray(voxData, index, 4));
		
		for(int i = 0; i < keyValuePairCount; i++) {
			
			VoxString key = VoxString.create(voxData, index + byteSize);
			byteSize += key.rawData.length;
			
			VoxString value = VoxString.create(voxData, index + byteSize);
			byteSize += value.rawData.length;
			
			voxDict.map.put(key, value);
		}
		
		voxDict.rawData = ByteHandler.getSubArray(voxData, index, byteSize);
		
		return voxDict;
	}
	
}
