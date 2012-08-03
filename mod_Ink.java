package net.minecraft.src;

import ink.*;
import net.minecraft.src.forge.*;

public class mod_Ink extends BaseMod
{
	public static ItemBucketInk ink;
	@MLProp(info = "Ink ID")
	public static int inkID = 12300;
	public static String inkTex;
	
	public String getVersion() {
		return "0.0.1";
	}
	
	public void load()
	{
		ink = (ItemBucketInk)new ItemBucketInk(inkID/*, 124,125*/).setItemName("Bucket Ink").setIconCoord(0, 0).setContainerItem(Item.bucketEmpty);
		inkTex = "/ink/bucketink.png";//from 統合テクスチャMOD
		ModLoader.addName(ink, "Bucket Ink");
		MinecraftForgeClient.preloadTexture(inkTex);
		MinecraftForge.registerEntityInteractHandler(ink);
		//recipe
		ModLoader.addShapelessRecipe(new ItemStack(35, 1, 15),//black cloth
			new Object[] {
				new ItemStack(ink, 1), new ItemStack(Block.cloth, 1)
			});
		ModLoader.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 8),//gray dye
			new Object[] {
				new ItemStack(ink, 1), new ItemStack(Item.dyePowder, 1, 15)
			});
	}
}