package alemax;

import alemax.model.Chunk;
import alemax.model.Model;
import alemax.model.Voxel;
import alemax.vox.VoxRotation;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Group group = new Group();
		Scene scene = new Scene(group, 1500, 800);
		
		primaryStage.setTitle("OBJToTM");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		FileHandler handler = new FileHandler();
		byte[] bytes = handler.readVoxFile("ice2.vox");
		Model model = new Model(bytes);
		
		for(Chunk chunk : model.chunks) {
			for(Voxel voxel : chunk.voxels) {
				Box box = new Box(1, 1, 1);
				box.translateXProperty().set(voxel.x + 0.5 + 500);
				box.translateXProperty().set(voxel.y + 0.5 + 500);
				box.translateXProperty().set(voxel.z + 0.5);
				
				group.getChildren().add(box);
			}
		}
		
	}
	


}
