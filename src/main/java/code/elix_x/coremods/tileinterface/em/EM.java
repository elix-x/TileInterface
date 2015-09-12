package code.elix_x.coremods.tileinterface.em;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class EM {

	private static final Map<String, Class<? extends EM>> map = new HashMap<String, Class<? extends EM>>();

	private String name;

	public EM(String n) {
		if(!map.containsKey(n)){
			map.put(n, getClass());
		}
		name = n;
	}

	public static EM createFromNBT(NBTTagCompound nbt) throws Exception{
		try{
			EM em = map.get(nbt.getString("name")).newInstance();
			em.readFromNbt(nbt);
			return em;
		}catch (NullPointerException e){
			return null;
		}
	}

	public NBTTagCompound writeToNbt(NBTTagCompound nbt) {
		nbt.setString("name", name);
		return nbt;
	}

	public void readFromNbt(NBTTagCompound nbt){
		name = nbt.getString("name");
	}

}
