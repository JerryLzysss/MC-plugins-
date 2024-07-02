package second.secondplugin;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class Rec {
    Plugin plugin =second.secondplugin.SecondPlugin.getPlugin(second.secondplugin.SecondPlugin.class);


    public ShapedRecipe  setRecipes() {
        ShapedRecipe recipes=new ShapedRecipe(new NamespacedKey(plugin,"test"),new ItemStack(Material.DIAMOND));
        recipes.shape("bab");
        recipes.setIngredient('b',Material.WOODEN_SWORD).setIngredient('a',Material.WOODEN_AXE);
        return recipes;
    }


}
