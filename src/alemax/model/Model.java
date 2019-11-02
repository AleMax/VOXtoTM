package alemax.model;

import java.util.ArrayList;
import java.util.Collections;

import org.joml.Matrix3f;
import org.joml.Vector3d;
import org.joml.Vector3f;

import alemax.vox.VoxChunk;
import alemax.vox.VoxChunkMain;
import alemax.vox.VoxChunkNGRP;
import alemax.vox.VoxChunkNSHP;
import alemax.vox.VoxChunkNTRN;
import alemax.vox.VoxChunkRGBA;
import alemax.vox.VoxChunkSize;
import alemax.vox.VoxChunkXYZI;
import alemax.vox.VoxRotation;
import org.joml.Vector4f;

public class Model {
	
	private ArrayList<StrippedChunk> initialChunks;
	
	public ArrayList<Chunk> chunks;
	public Vector4f[] colors;
	
	private VoxChunkMain main;
	
	public Model(byte[] voxData) {
		main = new VoxChunkMain(voxData, 8);
		initialChunks = new ArrayList<StrippedChunk>();
		for(VoxChunk voxChunk : main.childrenChunks) {
			if(voxChunk instanceof VoxChunkSize) {
				initialChunks.add(new StrippedChunk());
				initialChunks.get(initialChunks.size() - 1).setSize(((VoxChunkSize) voxChunk).sizeX, ((VoxChunkSize) voxChunk).sizeY, ((VoxChunkSize) voxChunk).sizeZ);
			} else if(voxChunk instanceof VoxChunkXYZI) {
				initialChunks.get(initialChunks.size() - 1).setVoxels(((VoxChunkXYZI) voxChunk).voxels);
			} else if(voxChunk instanceof VoxChunkRGBA) {
				this.colors = ((VoxChunkRGBA) voxChunk).palette;
			}
		}
		
		chunks = new ArrayList<Chunk>();
		ArrayList<Integer> childIDs = new ArrayList<Integer>();
		
		for(VoxChunk voxChunk : main.childrenChunks) {
			if(voxChunk instanceof VoxChunkNGRP) {
				for(int i = 0; i < ((VoxChunkNGRP) voxChunk).childNodeIDs.length; i++) {
					childIDs.add(((VoxChunkNGRP) voxChunk).childNodeIDs[i]);
				}
			}
		}
		
		for(VoxChunk voxChunk : main.childrenChunks) {
			if(voxChunk instanceof VoxChunkNTRN) {
				int nodeID = ((VoxChunkNTRN) voxChunk).nodeID;
				boolean found = false;
				for(Integer id : childIDs) {
					if(id.intValue() == nodeID) {
						found = true;
						break;
					}
				}
				if(!found) {
					Vector3f translation = new Vector3f();
					Matrix3f rotation = new Matrix3f();
					goDownNodes(nodeID, translation, rotation);
					break;
				}
			}
		}
		
		
		/*
		for(VoxChunk voxChunk : main.childrenChunks) {
			if(voxChunk instanceof VoxChunkNSHP) {
				
				//ArrayList<Vector3D> translations = new ArrayList<Vector3D>();
				//ArrayList<RealMatrix> rotations = new ArrayList<RealMatrix>();
				ArrayList<Vector3d> translations = new ArrayList<Vector3d>();
				ArrayList<Matrix3f> rotations = new ArrayList<Matrix3f>();
				
				int nodeID = ((VoxChunkNSHP) voxChunk).nodeID;
				int modelID = ((VoxChunkNSHP) voxChunk).modelIDs[0];
				
				getHigherNodes(nodeID, translations, rotations);
				
				/*
				Vector3D translation = new Vector3D(0, 0, 0);
				for(Vector3D trans : translations) {
					translation.add(trans);
				} */
		/*
				Vector3d translation = new Vector3d(0, 0, 0);
				for(Vector3d trans : translations) {
					translation.add(trans);
				}
				System.out.println(translations.get(0).x + " " + translations.get(0).y + " " +translations.get(0).z + "\t" +translations.get(1).x + " " +translations.get(1).y + " " + translations.get(1).z);
				
				//RealMatrix rotation = null;
				Matrix3f rotation = null;
				if(rotations.size() == 0) {
					//double[][] bytes = {{0,0,0},{0,0,0},{0,0,0}};
					//rotation = MatrixUtils.createRealMatrix(bytes);
					rotation = new Matrix3f(0, 0, 0, 0, 0, 0, 0, 0, 0);
				} else {
					Collections.reverse(rotations); 
					rotation = rotations.get(0);
					if(rotations.size() > 1) {
						for(int i = 1; i < rotations.size(); i++) {
							//rotation = rotation.multiply(rotations.get(i));
							rotation = rotation.mul(rotations.get(i));
						}			
					}
				}
				rotation.transpose();
				
				chunks.add(new Chunk());
				chunks.get(chunks.size() - 1).setSize(initialChunks.get(modelID).getSizeX(), initialChunks.get(modelID).getSizeY(), initialChunks.get(modelID).getSizeZ());
				chunks.get(chunks.size() - 1).setVoxels(initialChunks.get(modelID).getVoxels());
				
				chunks.get(chunks.size() - 1).setTranslation((int) Math.round(translation.x), (int) Math.round(translation.y), (int) Math.round(translation.z));
				chunks.get(chunks.size() - 1).setRotation(rotation);
				chunks.get(chunks.size() - 1).bake();
				
			}
		}
		*/
	}
	
	private void goDownNodes(int nodeID, Vector3f translation, Matrix3f rotation) {
		
		Vector3f newTranslation = new Vector3f(translation);
		Matrix3f newRotation = new Matrix3f(rotation);
		
		for(VoxChunk voxChunk : main.childrenChunks) {
			if(voxChunk instanceof VoxChunkNTRN) {
				if(((VoxChunkNTRN) voxChunk).nodeID == nodeID) {		
					
					if(((VoxChunkNTRN) voxChunk).frameAttributes[0].map.containsKey("_r")) {
						String rotationString = ((VoxChunkNTRN) voxChunk).frameAttributes[0].map.get("_r").string;
						
						byte rotationByte = (byte) Integer.parseInt(rotationString);
						VoxRotation rot = new VoxRotation(rotationByte);
						newRotation.mul(rot.rotMatrix);
					}
					
					if(((VoxChunkNTRN) voxChunk).frameAttributes[0].map.containsKey("_t")) {
						String translationString = ((VoxChunkNTRN) voxChunk).frameAttributes[0].map.get("_t").string;
						
						//HERE?? yes...
						
						
						
						String[] translationSplit = translationString.split(" ");
						newTranslation.add(new Vector3f(Integer.parseInt(translationSplit[0]), Integer.parseInt(translationSplit[1]), Integer.parseInt(translationSplit[2]))); 
					}
					
					goDownNodes(((VoxChunkNTRN) voxChunk).childNodeID, newTranslation, newRotation);
					
				}
			}
			if(voxChunk instanceof VoxChunkNGRP) {
				if(((VoxChunkNGRP) voxChunk).nodeID == nodeID) {
					for(int i = 0; i < ((VoxChunkNGRP) voxChunk).childNodeIDs.length; i++) {
						goDownNodes(((VoxChunkNGRP) voxChunk).childNodeIDs[i], newTranslation, newRotation);
					}
				}
			}
			if(voxChunk instanceof VoxChunkNSHP) {
				if(((VoxChunkNSHP) voxChunk).nodeID == nodeID) {
					//rotation.transpose();
					int modelID = ((VoxChunkNSHP) voxChunk).modelIDs[0];
					
					chunks.add(new Chunk());
					chunks.get(chunks.size() - 1).setSize(initialChunks.get(modelID).getSizeX(), initialChunks.get(modelID).getSizeY(), initialChunks.get(modelID).getSizeZ());
					chunks.get(chunks.size() - 1).setVoxels(initialChunks.get(modelID).getVoxels());
					
					chunks.get(chunks.size() - 1).setTranslation((int) Math.round(newTranslation.x), (int) Math.round(newTranslation.y), (int) Math.round(newTranslation.z));
					chunks.get(chunks.size() - 1).setRotation(newRotation);
					chunks.get(chunks.size() - 1).bake();
				}
			}
		}
	}
	
	
	//private void getHigherNodes(int nodeID, ArrayList<vector3D> translations, ArrayList<RealMatrix> rotations) {
	private void getHigherNodes(int nodeID, ArrayList<Vector3d> translations, ArrayList<Matrix3f> rotations) {
		for(VoxChunk voxChunk : main.childrenChunks) {
			if(voxChunk instanceof VoxChunkNTRN) {
				if(((VoxChunkNTRN) voxChunk).childNodeID == nodeID) {
					if(((VoxChunkNTRN) voxChunk).frameAttributes[0].map.containsKey("_r")) {
						String rotation = ((VoxChunkNTRN) voxChunk).frameAttributes[0].map.get("_r").string;
						
						byte rotationByte = (byte) Integer.parseInt(rotation);
						VoxRotation rot = new VoxRotation(rotationByte);
						rotations.add(rot.rotMatrix);
					}
					
					if(((VoxChunkNTRN) voxChunk).frameAttributes[0].map.containsKey("_t")) {
						String translation = ((VoxChunkNTRN) voxChunk).frameAttributes[0].map.get("_t").string;
						
						//HERE!!!
						
						String[] translationSplit = translation.split(" ");
						//translations.add(new Vector3D(Integer.parseInt(translationSplit[0]), Integer.parseInt(translationSplit[1]), Integer.parseInt(translationSplit[2]))); 
						translations.add(new Vector3d(Integer.parseInt(translationSplit[0]), Integer.parseInt(translationSplit[1]), Integer.parseInt(translationSplit[2]))); 
					}
					
					getHigherNodes(((VoxChunkNTRN) voxChunk).nodeID, translations, rotations);
					
					break;
				}
			}
			if(voxChunk instanceof VoxChunkNGRP) {
				for(int i = 0; i < ((VoxChunkNGRP) voxChunk).childNodeIDs.length; i++) {
					if(((VoxChunkNGRP) voxChunk).childNodeIDs[i] == nodeID) {
						
						getHigherNodes(((VoxChunkNGRP) voxChunk).nodeID, translations, rotations);
						
						break;
					}
				}
			}
		}
	}
}
