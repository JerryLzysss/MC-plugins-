package second.secondplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SecondPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
    Bukkit.getPluginCommand("test").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[]args)
    {
        if(command.getName().equalsIgnoreCase("test"))
        {
            if((sender instanceof Player)==false)
            {
                sender.sendMessage("你必须是一名玩家!");
                return true;
            }
            else{
                Player player=(Player)sender;
                player.sendMessage("恭喜你!"+player.getName()+"测试成功");
                return true;
            }
        }
        return false;
    }

}
