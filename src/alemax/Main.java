package alemax;

import vox.VoxDict;
import vox.VoxString;

public class Main {

	public static void main(String[] args) {
		FileHandler handler = new FileHandler();
		//byte[] bytes = handler.readVoxFile("test.vox");
		byte[] bytes = { 0x02, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x5F, 0x72, 0x02, 0x00, 0x00, 0x00, 0x33, 0x36, 0x02, 0x00, 0x00, 0x00, 0x5F, 0x74, 0x07, 0x00, 0x00, 0x00, 0x32, 0x38, 0x20, 0x30, 0x20, 0x2D, 0x35, 0x6E, 0x53, 0x48, 0x50};
		//VoxString string = VoxString.create(bytes, 0);
		VoxDict dict = VoxDict.create(bytes, 0);
		
		VoxString[] keys = dict.map.keySet().toArray(new VoxString[dict.map.size()]);
		for(int i = 0; i < keys.length; i++) {
			System.out.println(keys[i].string + "\t" + dict.map.get(keys[i]).string);
		}
		
		System.out.println(dict.rawData.length);
		
		//System.out.println(string.string);
	}

}
