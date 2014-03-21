package redgear.tileinterface;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import redgear.core.mod.ModUtils;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "redgear_tileinterface", name = "Tile Interface", version = "@ModVersion@", dependencies = "required-after:redgear_core;")
public class TileInterface extends ModUtils {

	@Instance("redgear_tileinterface")
	public static ModUtils inst;
	public static Block interfaceBlock;

	@Override
	public void PreInit(FMLPreInitializationEvent event) {
		interfaceBlock = new BlockInterface().setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal)
				.setBlockName("RedGear.TileInterface");
		;

		GameRegistry.registerBlock(interfaceBlock, "TileInterface");
		GameRegistry.registerTileEntity(TileEntityInterface.class, "TileInterface");
		GameRegistry.addShapedRecipe(new ItemStack(interfaceBlock), new Object[] {"LQL", "QEQ", "LQL", 'L',
				new ItemStack(Items.dye, 1, 4), 'Q', Blocks.quartz_block, 'E', Items.ender_pearl });

	}

	@Override
	public void Init(FMLInitializationEvent event) {

	}

	@Override
	protected void PostInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	@EventHandler
	public void PreInitialization(FMLPreInitializationEvent event) {
		super.PreInitialization(event);
	}

	@Override
	@EventHandler
	public void Initialization(FMLInitializationEvent event) {
		super.Initialization(event);
	}

	@Override
	@EventHandler
	public void PostInitialization(FMLPostInitializationEvent event) {
		super.PostInitialization(event);
	}

}
