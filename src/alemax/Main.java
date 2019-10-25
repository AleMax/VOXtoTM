package alemax;

import alemax.vox.VoxRotation;

public class Main {

	public static void main(String[] args) {
		/*
		FileHandler handler = new FileHandler();
		byte[] bytes = handler.readVoxFile("boxNoRotation.vox");
		
		VoxChunkMain main = new VoxChunkMain(bytes, 8);
		

		short _r = (1 << 0) | (2 << 2) | (0 << 4) | (1 << 5) | (1 << 6);
		
		System.out.println(_r);
		*/
	
		VoxRotation rot = new VoxRotation((byte) 113);
		
		System.out.println(rot.rotMatrix.getRow(0)[0] + "\t" + rot.rotMatrix.getRow(0)[1] + "\t" + rot.rotMatrix.getRow(0)[2]);
		System.out.println(rot.rotMatrix.getRow(1)[0] + "\t" + rot.rotMatrix.getRow(1)[1] + "\t" + rot.rotMatrix.getRow(1)[2]);
		System.out.println(rot.rotMatrix.getRow(2)[0] + "\t" + rot.rotMatrix.getRow(2)[1] + "\t" + rot.rotMatrix.getRow(2)[2]);
		
	}
	


}
