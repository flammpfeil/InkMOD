package ayamitsu.ink.common;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBucketMilk;

public class ItemBucketInk extends ItemBucketMilk {

	public ItemBucketInk(int id) {
		super(id);
	}

	public void func_94581_a(IconRegister par1IconRegister)
	{
		this.iconIndex = par1IconRegister.func_94245_a("ayamitsu/ink:bucketink");
	}

}
