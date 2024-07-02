package second.secondplugin;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;




public final class SecondPlugin extends JavaPlugin {

        @Override
        public void onEnable() {
            // Plugin startup logic
                Bukkit.getPluginManager().registerEvents(new BossBars(),this);
                Bukkit.addRecipe(new Rec().setRecipes());
        }

        @Override
        public void onDisable() {
            // Plugin shutdown logic

                Bukkit.removeBossBar(new NamespacedKey(this,"bossbar"));
                Bukkit.resetRecipes();
        }







}
