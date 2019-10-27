package alemax.vox;

import org.joml.Matrix3f;

//Because the rotation is stored in a VoxString this here just makes conversions
//The code in here is the worst code ive ever written
//I dont want to deal with this any more so i just leave it as is
//I could probably extrapolate the index of the last row with the given data(this has to be possible)
//But no, im to lazy to find out how, so im just looping through all possible combination and check if it fits...
//HAHAHAHAHAH no i found it out
//So all this above text is useless now, ill keep it anyway for history reasons...
public class VoxRotation {

	//public RealMatrix rotMatrix;
	public Matrix3f rotMatrix;
	
	public VoxRotation(byte i) {
		boolean a = (i & 0x1) != 0;
		boolean b = (i & 0x2) != 0;
		boolean c = (i & 0x4) != 0;
		boolean d = (i & 0x8) != 0;
		boolean e = (i & 0x10) != 0;
		boolean f = (i & 0x20) != 0;
		boolean g = (i & 0x40) != 0;
		//boolean h = (rotation & 0x80) != 0;
		
		byte indexFirstRow = 0;
		if(a) indexFirstRow = 1;
		else if(b) indexFirstRow = 2;
		
		byte indexSecondRow = 0;
		if(c) indexSecondRow = 1;
		else if(d) indexSecondRow = 2;
		
		float[] firstRow = {0, 0, 0};
		float[] secondRow = {0, 0, 0};
		float[] thirdRow = {0, 0, 0};
		
		float firstNumber = (e ? -1 : 1);
		float secondNumber = (f ? -1 : 1);
		float thirdNumber = (g ? -1 : 1);
		
		firstRow[indexFirstRow] = firstNumber;
		secondRow[indexSecondRow] = secondNumber;
		
		boolean indexOne = (indexFirstRow == 0 || indexSecondRow == 0) ? true : false;
		boolean indexTwo = (indexFirstRow == 1 || indexSecondRow == 1) ? true : false;
		boolean indexThree = (indexFirstRow == 2 || indexSecondRow == 2) ? true : false;
		
		if(!indexOne) thirdRow[0] = thirdNumber;
		else if(!indexTwo) thirdRow[1] = thirdNumber;
		else if(!indexThree) thirdRow[2] = thirdNumber;
		
		float[][] matrixData = {firstRow, secondRow, thirdRow};
		
		//rotMatrix = MatrixUtils.createRealMatrix(matrixData);
		rotMatrix = new Matrix3f(firstRow[0], firstRow[1], firstRow[2], secondRow[0], secondRow[1], secondRow[2], thirdRow[0], thirdRow[1], thirdRow[2]);
	}
	
	
	/*
	private RealMatrix getXMatrix(int rot) {
		double[][] matrixData = {{1,0,0}, {0,Math.cos(rot * Math.PI / 2),-Math.sin(rot * Math.PI / 2)}, {0,Math.sin(rot * Math.PI / 2),Math.cos(rot * Math.PI / 2)}};
		RealMatrix m = MatrixUtils.createRealMatrix(matrixData);
		return m;
	}
	
	private RealMatrix getYMatrix(int rot) {
		double[][] matrixData = {{Math.cos(rot * Math.PI / 2),0,Math.sin(rot * Math.PI / 2)}, {0,1,0}, {-Math.sin(rot * Math.PI / 2),0,Math.cos(rot * Math.PI / 2)}};
		RealMatrix m = MatrixUtils.createRealMatrix(matrixData);
		return m;
	}
	
	private RealMatrix getZMatrix(int rot) {
		double[][] matrixData = {{Math.cos(rot * Math.PI / 2),-Math.sin(rot * Math.PI / 2),0}, {Math.sin(rot * Math.PI / 2),Math.cos(rot * Math.PI / 2),0}, {0,0,1}};
		RealMatrix m = MatrixUtils.createRealMatrix(matrixData);
		return m;
	}
	*/
	
	
	
}
