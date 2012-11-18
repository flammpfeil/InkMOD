package ayamitsu.ink;

import ayamitsu.ink.common.*;

import net.minecraft.src.*;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.OreDictionary;

import java.util.logging.Level;
import java.util.BitSet;

@Mod(
	modid  = "InkMod",
	name = "InkMod",
	version = "1.0.0"
)
@NetworkMod(
	clientSideRequired = true,
	serverSideRequired = false,
	channels = "ink"
)
public class InkMod
{
	@Mod.Instance("InkMod")
	public static InkMod instance;
	
	@SidedProxy(clientSide = "ayamitsu.ink.client.ClientProxy", serverSide = "ayamitsu.ink.common.CommonProxy")
	public static CommonProxy proxy;
	
	public static Item bucketInk;
	public static int bucketInkId;
	public static final String terrain = "/ayamitsu/ink/terrain.png";
	
	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		try
		{
			config.load();
			int def = 12300;
			this.bucketInkId = config.getItem("Ink Bucket", def).getInt(def);
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Error Massage");
		}
		finally
		{
			config.save();
		}
	}
	
	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		this.bucketInk = (new ItemBucketMilk(this.bucketInkId)).setIconIndex(0).setItemName("bucketInk").setContainerItem(Item.bucketEmpty);
		this.bucketInk.setTextureFile(terrain);
		LanguageRegistry.instance().addNameForObject(this.bucketInk, "en_US", "Ink Bucket");
		MinecraftForge.EVENT_BUS.register(new SquidInteractHook());
		OreDictionary.registerOre("dyeBlack", new ItemStack(this.bucketInk.shiftedIndex, 1, 0));
	}
	
	@Mod.PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		InkRecipes.getInstance().init();
	}
}