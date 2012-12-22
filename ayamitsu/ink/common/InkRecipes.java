package ayamitsu.ink.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import ayamitsu.ink.InkMod;
import cpw.mods.fml.common.registry.GameRegistry;

public final class InkRecipes
{
	private static InkRecipes instance = new InkRecipes();
	private boolean hasInit = false;
	private final ItemStack bucketInk = new ItemStack(InkMod.bucketInk.shiftedIndex, 1, 0);
	private List inkRecipes;

	public static InkRecipes getInstance()
	{
		return instance;
	}

	public void init()
	{
		if (hasInit)
		{
			return;
		}

		hasInit = true;
		inkRecipes = new ArrayList();
		addRecipes();
	}

	private void addRecipes()
	{
		List list = CraftingManager.getInstance().getRecipeList();

		for (Iterator iterator = list.iterator(); iterator.hasNext();)
		{
			Object recipe = iterator.next();

			if (recipe instanceof ShapelessRecipes)
			{
				addShapelessRecipe((ShapelessRecipes)recipe);
			}
			else if (recipe instanceof ShapedRecipes)
			{
				addShapedRecipe((ShapedRecipes)recipe);
			}
		}

		for (Iterator iterator = inkRecipes.iterator(); iterator.hasNext();)
		{
			GameRegistry.addRecipe((IRecipe)iterator.next());
		}
	}

	private void addShapedRecipe(ShapedRecipes recipe)
	{
		ItemStack result = recipe.getRecipeOutput();

		if (result == null)
		{
			return;
		}

		boolean flag = false;
		ItemStack[] arrayis = new ItemStack[recipe.recipeItems.length];

		for (int i = 0; i < recipe.recipeItems.length; i++)
		{
			ItemStack is = recipe.recipeItems[i];

			if (isInkSuc(is))
			{
				arrayis[i] = bucketInk;
				flag = true;
				continue;
			}

			arrayis[i] = recipe.recipeItems[i];
		}

		if (flag)
		{
			inkRecipes.add(new ShapedRecipes(recipe.recipeWidth, recipe.recipeHeight, arrayis, result));
			//System.out.println("addShaped");
		}
	}

	private void addShapelessRecipe(ShapelessRecipes recipe)
	{
		ItemStack result = recipe.getRecipeOutput();

		if (result == null)
		{
			return;
		}

		boolean flag = false;
		List list = new ArrayList(recipe.recipeItems);

		for (int i = 0; i < list.size(); i++)
		{
			ItemStack is = (ItemStack)list.get(i);

			if (isInkSuc(is))
			{
				list.set(i, bucketInk);
				flag = true;
			}
		}

		if (flag)
		{
			inkRecipes.add(new ShapelessRecipes(result, list));
			//System.out.println("addShapeless");
		}
	}

	private boolean isInkSuc(ItemStack is)
	{
		return is != null && is.itemID == Item.dyePowder.shiftedIndex && is.getItemDamage() == 0;
	}
}