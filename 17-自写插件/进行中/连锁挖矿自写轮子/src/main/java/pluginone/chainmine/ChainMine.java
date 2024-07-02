package pluginone.chainmine;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChainMine extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[Jerry]连锁挖矿插件已启动");

        Bukkit.getPluginManager().registerEvents(new ChainListener(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[Jerry]连锁挖矿插件已关闭");

    }
}
