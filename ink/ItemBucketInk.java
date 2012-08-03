package ink;

import net.minecraft.src.*;
import net.minecraft.src.forge.*;

public class ItemBucketInk extends ItemBucketMilk implements ITextureProvider, IEntityInteractHandler
{
//	private int texA;
//	private int texB;
	
	public ItemBucketInk(int par1/*, int tex1, int tex2*/)
	{
		super(par1);
//		texA = tex1;
//		texB = tex2;
	}
/*	
	public boolean func_46058_c()//texture mix
	{
		return true;
	}
	
	public int func_46057_a(int par1, int par2)
	{
		return par2 > 0 ? texB : texA;
	}
	
	public int getColorFromDamage(int par1, int par2)
	{
		return par2 > 0 ? 0x2f2f2f : 0xffffff;
	}
*/	
//forge
	public String getTextureFile()
	{
		return mod_Ink.inkTex;
	}
	
	@Override
	public boolean onEntityInteract(EntityPlayer player, Entity entity, boolean isAttack)
	{
		ItemStack is = player.inventory.getCurrentItem();
		if (is != null && is.itemID == Item.bucketEmpty.shiftedIndex && !isAttack)
		{
			if (entity instanceof EntitySquid)
			{
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(this));
				return false;
			}
		}
		return true;
	}
}