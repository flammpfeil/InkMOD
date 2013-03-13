package ayamitsu.ink;

import java.util.logging.Level;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import ayamitsu.ink.common.ItemBucketInk;
import ayamitsu.ink.common.SquidInteractHook;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(
	modid  = "InkMod",
	name = "InkMod",
	version = "1.0.0"
)
@NetworkMod(
	clientSideRequired = true,
	serverSideRequired = false
)
public class InkMod
{
	@Mod.Instance("InkMod")
	public static InkMod instance;

	public static Item bucketInk;
	public static int bucketInkId;

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		try
		{
			config.load();
			this.bucketInkId = config.getItem("Ink Bucket", 12300).getInt(12300);
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
		this.bucketInk = (new ItemBucketInk(this.bucketInkId - 256)).setUnlocalizedName("bucketInk").setContainerItem(Item.bucketEmpty);
		LanguageRegistry.instance().addNameForObject(this.bucketInk, "en_US", "Ink Bucket");
		LanguageRegistry.instance().addNameForObject(this.bucketInk, "ja_JP", "イカ墨バケツ");
		MinecraftForge.EVENT_BUS.register(new SquidInteractHook());
		OreDictionary.registerOre("dyeBlack", new ItemStack(this.bucketInk.itemID, 1, 0));
	}

}