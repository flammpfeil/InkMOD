package ayamitsu.ink.common;

import ayamitsu.ink.*;

import net.minecraft.src.*;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class SquidInteractHook
{
	/**
	 * この仕組み以外に方法あったらいいな
	 */
	private boolean interact = false;
	
	@ForgeSubscribe
	public void onInteractEntity(EntityInteractEvent event)
	{
		Entity entity = event.target;
		EntityPlayer player = event.entityPlayer;
		
		if (entity instanceof EntitySquid)
		{
			ItemStack is = player.inventory.getCurrentItem();
			
			if (is != null && is.itemID == Item.bucketEmpty.shiftedIndex)
			{
				if (--is.stackSize <= 0)
				{
					player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(InkMod.bucketInk));
				}
				else if (!player.inventory.addItemStackToInventory(new ItemStack(InkMod.bucketInk)))
				{
					player.dropPlayerItem(new ItemStack(InkMod.bucketInk.shiftedIndex, 1, 0));
				}
				
				this.interact = true;
			}
		}
	}
	
	@ForgeSubscribe
	public void onFillBucket(FillBucketEvent event)
	{
		if (this.interact)
		{
			event.setCanceled(true);
		}
		
		this.interact = false;
	}
}