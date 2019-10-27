package alemax.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteHandler {
	
	public static int getInt32(byte[] bytes) {
		if(bytes.length == 4) {
			return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
		}
		return 0;
	}
	
	//Actually useless...
	public static byte getInt8(byte[] bytes) {
		if(bytes.length == 1) {
			return bytes[0];
		}
		return (byte) 0;
	}
	
	public static String getString(byte[] bytes) {
		return new String(bytes);
	}
	
	public static byte[] getSubArray(byte[] bytes, int index, int count) {
		byte[] returnBytes = new byte[count];
		for(int i = 0; i < count; i++) {
			returnBytes[i] = bytes[index + i];
		}
		return returnBytes;
	}
	
}
