package alemax.model;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import alemax.vox.VoxChunk;
import alemax.vox.VoxChunkMain;
import alemax.vox.VoxChunkNGRP;
import alemax.vox.VoxChunkNSHP;
import alemax.vox.VoxChunkNTRN;
import alemax.vox.VoxChunkRGBA;
import alemax.vox.VoxChunkSize;
import alemax.vox.VoxChunkXYZI;
import alemax.vox.VoxRotation;

public class Model {
	
	private ArrayList<StrippedChunk> initialChunks;
	
	public ArrayList<Chunk> chunks;
	public Color[] colors;
	
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
		for(VoxChunk voxChunk : main.childrenChunks) {
			if(voxChunk instanceof VoxChunkNSHP) {
				
				ArrayList<Vector3D> translations = new ArrayList<Vector3D>();
				ArrayList<RealMatrix> rotations = new ArrayList<RealMatrix>();
				
				int nodeID = ((VoxChunkNSHP) voxChunk).nodeID;
				int modelID = ((VoxChunkNSHP) voxChunk).modelIDs[0];
				
				getHigherNodes(nodeID, translations, rotations);
				
				
				Vector3D translation = new Vector3D(0, 0, 0);
				for(Vector3D trans : translations) {
					translation.add(trans);
				}
				
				
				RealMatrix rotation = null;
				if(rotations.size() == 0) {
					double[][] bytes = {{0,0,0},{0,0,0},{0,0,0}};
					rotation = MatrixUtils.createRealMatrix(bytes);
				} else {
					Collections.reverse(rotations); 
					rotation = rotations.get(0);
					if(rotations.size() > 1) {
						for(int i = 1; i < rotations.size(); i++) {
							rotation = rotation.multiply(rotations.get(i));
						}			
					}
				}
				
				
				chunks.add(new Chunk());
				chunks.get(chunks.size() - 1).setSize(initialChunks.get(modelID).getSizeX(), initialChunks.get(modelID).getSizeY(), initialChunks.get(modelID).getSizeZ());
				chunks.get(chunks.size() - 1).setVoxels(initialChunks.get(modelID).getVoxels());
				
				chunks.get(chunks.size() - 1).setTranslation((int) Math.round(translation.getX()), (int) Math.round(translation.getY()), (int) Math.round(translation.getZ()));
				chunks.get(chunks.size() - 1).setRotation(rotation);
				chunks.get(chunks.size() - 1).bake();
				
			}
		}
	}
	
	
	private void getHigherNodes(int nodeID, ArrayList<Vector3D> translations, ArrayList<RealMatrix> rotations) {
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
						
						
						
						String[] translationSplit = translation.split(" ");
						translations.add(new Vector3D(Integer.parseInt(translationSplit[0]), Integer.parseInt(translationSplit[1]), Integer.parseInt(translationSplit[2]))); 
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
