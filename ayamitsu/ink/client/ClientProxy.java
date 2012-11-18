package ayamitsu.ink.client;

import ayamitsu.ink.InkMod;
import ayamitsu.ink.common.CommonProxy;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
	public void load()
	{
		MinecraftForgeClient.preloadTexture(InkMod.terrain);
	}
}