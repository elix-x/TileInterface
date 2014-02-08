package redgear.tileinterface;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import redgear.core.mod.ModUtils;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "RedGear|TileInterface", name = "Tile Interface", version = "@ModVersion@", dependencies = "required-after:RedGear|Core;")
public class TileInterface extends ModUtils {

	@Instance("RedGear|TileInterface")
	public static ModUtils inst;
	public static Block interfaceBlock;

	private static int interfaceId;

	public TileInterface() {
		super(2740, 24560);
	}

	@Override
	public void PreInit(FMLPreInitializationEvent event) {
		interfaceId = getBlockId("interface");
	}

	@Override
	public void Init(FMLInitializationEvent event) {
		interfaceBlock = new BlockInterface(interfaceId).setHardness(5.0F).setResistance(10.0F)
				.setStepSound(Block.soundMetalFootstep).setUnlocalizedName("RedGear.TileInterface");
		;

		LanguageRegistry.addName(interfaceBlock, "Tile Interface");
		GameRegistry.registerBlock(interfaceBlock, "TileInterface");
		GameRegistry.registerTileEntity(TileEntityInterface.class, "TileInterface");
		GameRegistry.addShapedRecipe(new ItemStack(interfaceBlock), new Object[] {"LQL", "QEQ", "LQL", 'L',
				new ItemStack(Item.dyePowder, 1, 4), 'Q', Block.blockNetherQuartz, 'E', Item.enderPearl });

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
