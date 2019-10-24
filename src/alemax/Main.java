package alemax;

import vox.VoxString;

public class Main {

	public static void main(String[] args) {
		FileHandler handler = new FileHandler();
		//byte[] bytes = handler.readVoxFile("vox/test.vox");
		byte[] bytes = { 6, 0, 0, 0, (byte)'A', (byte)'l', (byte)'e', (byte)'M', (byte)'a', (byte)'x'};
		VoxString string = VoxString.create(bytes, 0);
		
		System.out.println(string.string);
	}

}
