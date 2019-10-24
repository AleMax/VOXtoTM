package alemax;

public class Main {

	public static void main(String[] args) {
		FileHandler handler = new FileHandler();
		byte[] array = handler.readVoxFile("vox/test.vox");
		if(array != null) {
			System.out.println("success, i guess");
		} else {
			System.out.println("nah");
		}
	}

}
