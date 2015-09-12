package code.elix_x.coremods.tileinterface;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import code.elix_x.coremods.tileinterface.blocks.BlockEnderInterface;
import code.elix_x.coremods.tileinterface.blocks.BlockEnderQuantumInterface;
import code.elix_x.coremods.tileinterface.blocks.BlockInterface;
import code.elix_x.coremods.tileinterface.blocks.BlockQuantumInterface;
import code.elix_x.coremods.tileinterface.blocks.BlockQuantumStorage;
import code.elix_x.coremods.tileinterface.blocks.TileEntityEnderInterface;
import code.elix_x.coremods.tileinterface.items.ItemBlockEnderInterface;
import code.elix_x.coremods.tileinterface.items.ItemBlockEnderQuantumInterface;
import code.elix_x.coremods.tileinterface.materials.MaterialQuantum;
import code.elix_x.coremods.tileinterface.tileentities.TileEntityEnderQuantumInterface;
import code.elix_x.coremods.tileinterface.tileentities.TileEntityInterface;
import code.elix_x.coremods.tileinterface.tileentities.TileEntityQuantumInterface;
import code.elix_x.coremods.tileinterface.tileentities.TileEntityQuantumStorage;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = TileInterfaceBase.MODID, name = TileInterfaceBase.NAME, version = TileInterfaceBase.VERSION)
public class TileInterfaceBase {

	public static final String MODID = "tileinterface";
	public static final String NAME = "Tile Interface";
	public static final String VERSION = "2.0";
	
	public static Material materialQuantum;
	
	public static Block interfaceBlock;
	public static Block enderInterfaceBlock;
	public static Block quantumInterfaceBlock;
	public static Block enderQuantumInterfaceBlock;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		materialQuantum = new MaterialQuantum();
		
		interfaceBlock = new BlockInterface();
		GameRegistry.registerBlock(interfaceBlock, "tileinterface");
		GameRegistry.registerTileEntity(TileEntityInterface.class, "tileinterface");
		GameRegistry.addShapedRecipe(new ItemStack(interfaceBlock), new Object[] {"LQL", "QEQ", "LQL", 'L', new ItemStack(Items.dye, 1, 4), 'Q', Blocks.quartz_block, 'E', Items.ender_pearl });
		
		enderInterfaceBlock = new BlockEnderInterface();
		GameRegistry.registerBlock(enderInterfaceBlock, ItemBlockEnderInterface.class, "endertileinterface");
		GameRegistry.registerTileEntity(TileEntityEnderInterface.class, "endertileinterface");
		GameRegistry.addShapedRecipe(new ItemStack(enderInterfaceBlock), new Object[] {"LQL", "QEQ", "LQL", 'Q', Items.ender_pearl, 'L', Blocks.quartz_block, 'E', interfaceBlock});

		quantumInterfaceBlock = new BlockQuantumInterface();
		GameRegistry.registerBlock(quantumInterfaceBlock, "quantumtileinterface");
		GameRegistry.registerTileEntity(TileEntityQuantumInterface.class, "quantumtileinterface");
		GameRegistry.addShapedRecipe(new ItemStack(quantumInterfaceBlock, 4), new Object[] {"LQL", "QEQ", "LQL", 'Q', Items.ender_pearl, 'L', Blocks.quartz_block, 'E', Items.nether_star});
		
		enderQuantumInterfaceBlock = new BlockEnderQuantumInterface();
		GameRegistry.registerBlock(enderQuantumInterfaceBlock, ItemBlockEnderQuantumInterface.class, "enderquantumtileinterface");
		GameRegistry.registerTileEntity(TileEntityEnderQuantumInterface.class, "enderquantumtileinterface");
		GameRegistry.addShapedRecipe(new ItemStack(enderQuantumInterfaceBlock), new Object[] {"LQL", "QEQ", "LQL", 'Q', Items.nether_star, 'L', Blocks.quartz_block, 'E', quantumInterfaceBlock});
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}

}
