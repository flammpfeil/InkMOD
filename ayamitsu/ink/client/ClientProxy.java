package ayamitsu.ink.client;

import net.minecraftforge.client.MinecraftForgeClient;
import ayamitsu.ink.InkMod;
import ayamitsu.ink.common.CommonProxy;

public class ClientProxy extends CommonProxy
{
	public void load()
	{
		MinecraftForgeClient.preloadTexture(InkMod.terrain);
	}
}