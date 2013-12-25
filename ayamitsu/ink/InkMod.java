package ayamitsu.ink;

import java.util.logging.Level;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import ayamitsu.ink.common.ItemBucketInk;
import ayamitsu.ink.common.SquidInteractHook;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(
	modid  = "InkMod",
	name = "InkMod",
	version = "@VERSION@"
)
@NetworkMod(
	clientSideRequired = true,
	serverSideRequired = true
)
public class InkMod
{
	@Mod.Instance("InkMod")
	public static InkMod instance;

	public static Item bucketInk;
	public static int bucketInkId = 12300;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		try
		{
			config.load();
			this.bucketInkId = config.getItem("Ink Bucket", bucketInkId).getInt();
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

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		this.bucketInk = (new ItemBucketInk(this.bucketInkId)).setUnlocalizedName("ayamitsu.ink.bucketink").setTextureName("ink:bucketink").setContainerItem(Item.bucketEmpty);
		LanguageRegistry.instance().addNameForObject(this.bucketInk, "en_US", "Ink Bucket");
		LanguageRegistry.instance().addNameForObject(this.bucketInk, "ja_JP", "イカ墨バケツ");
		MinecraftForge.EVENT_BUS.register(new SquidInteractHook());
		OreDictionary.registerOre("dyeBlack", new ItemStack(this.bucketInk.itemID, 1, 0));
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		if(FluidRegistry.isFluidRegistered("myst.ink.black")){
			Fluid mystInk = FluidRegistry.getFluid("myst.ink.black");
			FluidContainerRegistry.registerFluidContainer(mystInk, new ItemStack(bucketInk), FluidContainerRegistry.EMPTY_BUCKET);
		}
	}

}
