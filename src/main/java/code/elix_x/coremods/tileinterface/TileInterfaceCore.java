package code.elix_x.coremods.tileinterface;

import java.io.File;
import java.util.Map;

import code.elix_x.coremods.tileinterface.core.TileInterfaceTransformer;
import code.elix_x.coremods.tileinterface.core.TileInterfaceTranslator;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.Name;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@Name(value = "keysoverhaul")
@TransformerExclusions(value = "code.elix_x.coremods")
@MCVersion(value = "1.7.10")
public final class TileInterfaceCore implements IFMLLoadingPlugin{

	//Dfml.coreMods.load=code.elix_x.coremods.tileinterface.TileInterfaceCore
	
	public static final String Transformer = TileInterfaceTransformer.class.getName();
	
	public static final String[] transformers = new String[]{Transformer};
	
	public static File mcDir;
	
	@Override
	public String[] getASMTransformerClass() {
		return transformers;
//		return new String[]{};
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return TileInterfaceTranslator.class.getName();
	}

	@Override
	public void injectData(Map<String, Object> data) {
		mcDir = (File) data.get("mcLocation");
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}
