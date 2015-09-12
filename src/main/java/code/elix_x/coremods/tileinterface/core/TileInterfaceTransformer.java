package code.elix_x.coremods.tileinterface.core;

import net.minecraft.launchwrapper.IClassTransformer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class TileInterfaceTransformer implements IClassTransformer{

	/*
	 * World
	 */

	public static Logger logger = LogManager.getLogger("Tile Interface Core");

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		/*if(name.equals(TileInterfaceTranslator.getMapedClassName("world.World"))){
			logger.info("##################################################");
			logger.info("Patching World");

			byte[] b = patchWorld(name, bytes);

			logger.info("Patching World Completed");
			logger.info("##################################################");
		 *	return b;
		}*/

		if(name.equals(TileInterfaceTranslator.getMapedClassName("world.chunk.Chunk"))){
			logger.info("##################################################");
			logger.info("Patching Chunk");

			byte[] b = patchChunk(name, bytes);

			logger.info("Patching Chunk Completed");
			logger.info("##################################################");
			return b;
		}
		return bytes;
	}

	/*private byte[] patchWorld(String name, byte[] bytes) {
		String getBlock = TileInterfaceTranslator.getMapedMethodName("World", "func_147439_a", "getBlock");
		String getBlockDesc = TileInterfaceTranslator.getMapedMethodDesc("World", "func_147439_a", "(III)Lnet/minecraft/block/Block;");
		String getBlockMetadata = TileInterfaceTranslator.getMapedMethodName("World", "func_72805_g", "getBlockMetadata");
		String getBlockMetadataDesc = TileInterfaceTranslator.getMapedMethodDesc("World", "func_72805_g", "(III)I");
		String getTileEntity = TileInterfaceTranslator.getMapedMethodName("World", "func_147438_o", "getTileEntity");
		String getTileEntityAtDesc = TileInterfaceTranslator.getMapedMethodDesc("World", "func_147438_o", "(III)Lnet/minecraft/tileentity/TileEntity;");

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for(MethodNode method : classNode.methods){	
			if(method.name.equals(getBlock) && method.desc.equals(getBlockDesc)){
				try{
					logger.info("**************************************************");
					logger.info("Patching getBlock");

					//INVOKEVIRTUAL net/minecraft/world/chunk/Chunk.getBlock (III)Lnet/minecraft/block/Block;

					AbstractInsnNode target = null;
					for(AbstractInsnNode node : method.instructions.toArray()){
						if(node.getOpcode() == Opcodes.INVOKEVIRTUAL){
							MethodInsnNode m = (MethodInsnNode) node;
							if(m.owner.equals(TileInterfaceTranslator.getMapedClassName("world.chunk.Chunk").replace(".", "/")) && method.name.equals(TileInterfaceTranslator.getMapedMethodName("Chunk", "func_150810_a", "getBlock"))){
								target = node;
								break;
							}
						}
					}

					// ALOAD 4

					AbstractInsnNode aload = target;

					boolean done = false;
					while(!done){
						aload = aload.getPrevious();
						if(aload.getOpcode() == Opcodes.ALOAD){
							done = true;
							break;
						}
					}

					InsnList alist = new InsnList();
					alist.add(new VarInsnNode(Opcodes.ALOAD, 0));
					alist.add(new VarInsnNode(Opcodes.ILOAD, 1));
					alist.add(new VarInsnNode(Opcodes.ILOAD, 2));
					alist.add(new VarInsnNode(Opcodes.ILOAD, 3));
					method.instructions.insertBefore(aload, alist);

					//					InsnList mlist = new InsnList();
					method.instructions.insert(target, new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.tileinterface.core.AsmHooks".replace(".", "/"), "getBlockAt", "(L" + TileInterfaceTranslator.getMapedClassName("world.World").replace(".", "/") + ";IIIL" + TileInterfaceTranslator.getMapedClassName("block.Block").replace(".", "/") + ";)L" + TileInterfaceTranslator.getMapedClassName("block.Block").replace(".", "/") + ";", false));

					logger.info("Patching getBlock Completed");
					logger.info("**************************************************");
				}catch(Exception e){
					logger.error("Patching getBlock Failed With Exception: ", e);
					logger.info("**************************************************");
				}
			}

			if(method.name.equals(getBlockMetadata) && method.desc.equals(getBlockMetadataDesc)){
				try{
					logger.info("**************************************************");
					logger.info("Patching getBlockMetadata");

					//INVOKEVIRTUAL net/minecraft/world/chunk/Chunk.getBlock (III)Lnet/minecraft/block/Block;

					/*AbstractInsnNode target = null;
					for(AbstractInsnNode node : method.instructions.toArray()){
						if(node.getOpcode() == Opcodes.INVOKEVIRTUAL){
							MethodInsnNode m = (MethodInsnNode) node;
							if(m.owner.equals(TileInterfaceTranslator.getMapedClassName("world.chunk.Chunk").replace(".", "/")) && method.name.equals(TileInterfaceTranslator.getMapedMethodName("Chunk", "func_76628_c", "getBlockMetadata"))){
								target = node;
								break;
							}
						}
					}

					// ALOAD 4

					AbstractInsnNode aload = target;

					boolean done = false;
					while(!done){
						aload = aload.getPrevious();
						if(aload.getOpcode() == Opcodes.ALOAD){
							done = true;
							break;
						}
					}

					InsnList alist = new InsnList();
					alist.add(new VarInsnNode(Opcodes.ALOAD, 0));
					alist.add(new VarInsnNode(Opcodes.ILOAD, 1));
					alist.add(new VarInsnNode(Opcodes.ILOAD, 2));
					alist.add(new VarInsnNode(Opcodes.ILOAD, 3));
					method.instructions.insertBefore(aload, alist);

//					InsnList mlist = new InsnList();
					method.instructions.insert(target, new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.tileinterface.core.AsmHooks".replace(".", "/"), "getBlockMetadataAt", "(L" + TileInterfaceTranslator.getMapedClassName("world.World").replace(".", "/") + ";IIII)I", false));


					AbstractInsnNode target = null;
					for(AbstractInsnNode node : method.instructions.toArray()){
						if(node.getOpcode() == Opcodes.ASTORE){
							target = node;
							break;
						}
					}



					InsnList list = new InsnList();
					list.add(new LabelNode());
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new VarInsnNode(Opcodes.ALOAD, 4));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new IntInsnNode(Opcodes.BIPUSH, 15));
					list.add(new InsnNode(Opcodes.IADD));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new IntInsnNode(Opcodes.BIPUSH, 15));
					list.add(new InsnNode(Opcodes.IADD));
					list.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, TileInterfaceTranslator.getMapedClassName("world.chunk.Chunk").replace(".", "/"), TileInterfaceTranslator.getMapedMethodName("Chunk", "func_76628_c", "getBlockMetadata"), "(III)I", false));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.tileinterface.core.AsmHooks".replace(".", "/"), "getBlockMetadataAt", "(L" + TileInterfaceTranslator.getMapedClassName("world.World").replace(".", "/") + ";IIII)I", false));
					list.add(new InsnNode(Opcodes.IRETURN));
					list.add(new LabelNode());

					method.instructions.insert(target, list);

					logger.info("Patching getBlockMetadata Completed");
					logger.info("**************************************************");
				}catch(Exception e){
					logger.error("Patching getBlockMetadat Failed With Exception: ", e);
					logger.info("**************************************************");
				}
			}

			if(method.name.equals(getTileEntity) && method.desc.equals(getTileEntityAtDesc)){
				try{
					logger.info("**************************************************");
					logger.info("Patching getTileEntity");

					/* 
	 * ALOAD 4
	 * ARETURN



					AbstractInsnNode target = null;
					for(AbstractInsnNode node : method.instructions.toArray()){
						if(node.getOpcode() == Opcodes.ARETURN){
							if(node.getPrevious().getOpcode() == Opcodes.ALOAD){
								target = node.getPrevious();
								break;
							}
						}
					}

					InsnList list = new InsnList();
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					method.instructions.insertBefore(target, list);

					method.instructions.insert(target, new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.tileinterface.core.AsmHooks".replace(".", "/"), "getTileEntityAt", "(L" + TileInterfaceTranslator.getMapedClassName("world.World").replace(".", "/") + ";IIIL" + TileInterfaceTranslator.getMapedClassName("tileentity.TileEntity").replace(".", "/") + ";)L" + TileInterfaceTranslator.getMapedClassName("tileentity.TileEntity").replace(".", "/") + ";", false));

					logger.info("Patching getTileEntity Completed");
					logger.info("**************************************************");
				}catch(Exception e){
					logger.error("Patching getTileEntity Failed With Exception: ", e);
					logger.info("**************************************************");
				}
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(writer);
		return writer.toByteArray();
	}*/

	private byte[] patchChunk(String name, byte[] bytes) {
		String chunk = TileInterfaceTranslator.getMapedClassName("world.chunk.Chunk").replace(".", "/");

		String getBlock = TileInterfaceTranslator.getMapedMethodName("Chunk", "func_150810_a", "getBlock");
		String getBlockDesc = TileInterfaceTranslator.getMapedMethodDesc("Chunk", "func_150810_a", "(III)Lnet/minecraft/block/Block;");
		String getBlockMetadata = TileInterfaceTranslator.getMapedMethodName("Chunk", "func_76628_c", "getBlockMetadata");
		String getBlockMetadataDesc = TileInterfaceTranslator.getMapedMethodDesc("Chunk", "func_76628_c", "(III)I");
		String func_150806_e = TileInterfaceTranslator.getMapedMethodName("Chunk", "func_150806_e", "func_150806_e");
		String func_150806_eDesc = TileInterfaceTranslator.getMapedMethodDesc("Chunk", "func_150806_e", "(III)Lnet/minecraft/tileentity/TileEntity;");

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for(MethodNode method : classNode.methods){	
			if(method.name.equals(getBlock) && method.desc.equals(getBlockDesc)){
				try{
					logger.info("**************************************************");
					logger.info("Patching getBlock");

					AbstractInsnNode targetNode = null;

					for(AbstractInsnNode node : method.instructions.toArray()){
						if(node.getOpcode() == Opcodes.ARETURN){
							targetNode = node.getPrevious().getPrevious();
							break;
						}
					}

					/*InsnList list = new InsnList();
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new VarInsnNode(Opcodes.ALOAD, 4));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.tileinterface.core.AsmHooks".replace(".", "/"), "getBlockAt", "(L" + chunk + ";IIIL" + TileInterfaceTranslator.getMapedClassName("block.Block").replace(".", "/") + ";)L" + TileInterfaceTranslator.getMapedClassName("block.Block").replace(".", "/") + ";", false));
					method.instructions.insert(targetNode, list);
					method.instructions.remove(targetNode.getNext());*/

					logger.info("Patching getBlock Completed");
					logger.info("**************************************************");
				}catch(Exception e){
					logger.error("Patching getBlock Failed With Exception: ", e);
					logger.info("**************************************************");
				}
			}

			if(method.name.equals(getBlockMetadata) && method.desc.equals(getBlockMetadataDesc)){
				try{
					logger.info("**************************************************");
					logger.info("Patching getBlockMetadata");
					
					AbstractInsnNode targetNode = null;

					boolean b = false;
					
					for(AbstractInsnNode node : method.instructions.toArray()){
						if(node.getOpcode() == Opcodes.IRETURN){
							method.instructions.set(node, new VarInsnNode(Opcodes.ISTORE, 5));
						}
						/*if(node.getOpcode() == Opcodes.IRETURN){
							if(!b){
								b = true;
							} else {
								targetNode = node.getPrevious();
								break;
							}
						}*/
					}

					method.instructions.add(new LabelNode());
					method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
					method.instructions.add(new VarInsnNode(Opcodes.ILOAD, 1));
					method.instructions.add(new VarInsnNode(Opcodes.ILOAD, 2));
					method.instructions.add(new VarInsnNode(Opcodes.ILOAD, 3));
					method.instructions.add(new VarInsnNode(Opcodes.ILOAD, 5));
					method.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.tileinterface.core.AsmHooks".replace(".", "/"), "getBlockMetadataAt", "(L" + chunk + ";IIII)I", false));
					method.instructions.add(new InsnNode(Opcodes.IRETURN));
					method.instructions.add(new LabelNode());
					
					InsnList list = new InsnList();


					logger.info("Patching getBlockMetadata Completed");
					logger.info("**************************************************");
				}catch(Exception e){
					logger.error("Patching getBlockMetadat Failed With Exception: ", e);
					logger.info("**************************************************");
				}
			}

			if(method.name.equals(func_150806_e) && method.desc.equals(func_150806_eDesc)){
				try{
					logger.info("**************************************************");
					logger.info("Patching func_150806_e");

					AbstractInsnNode targetNode = null;

					boolean b = false;

					for(AbstractInsnNode node : method.instructions.toArray()){
						if(node.getOpcode() == Opcodes.ARETURN){
							if(!b){
								b = true;
							} else {
								targetNode = node.getPrevious();
								break;
							}
						}
					}

					InsnList list = new InsnList();
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new VarInsnNode(Opcodes.ALOAD, 5));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.tileinterface.core.AsmHooks".replace(".", "/"), "getTileEntityAt", "(L" + chunk + ";IIIL" + TileInterfaceTranslator.getMapedClassName("tileentity.TileEntity").replace(".", "/") + ";)L" + TileInterfaceTranslator.getMapedClassName("tileentity.TileEntity").replace(".", "/") + ";", false));
					method.instructions.insert(targetNode, list);
					method.instructions.remove(targetNode);

					logger.info("Patching func_150806_e Completed");
					logger.info("**************************************************");
				}catch(Exception e){
					logger.error("Patching func_150806_e Failed With Exception: ", e);
					logger.info("**************************************************");
				}
			}

			/*if(method.name.equals(getTileEntity) && method.desc.equals(getTileEntityAtDesc)){
				try{
					logger.info("**************************************************");
					logger.info("Patching getTileEntity");



					logger.info("Patching getTileEntity Completed");
					logger.info("**************************************************");
				}catch(Exception e){
					logger.error("Patching getTileEntity Failed With Exception: ", e);
					logger.info("**************************************************");
				}
			}*/
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(writer);
		return writer.toByteArray();
	}
}
