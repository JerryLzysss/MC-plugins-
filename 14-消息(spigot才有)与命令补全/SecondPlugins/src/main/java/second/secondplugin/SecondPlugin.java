package second.secondplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


public final class SecondPlugin extends JavaPlugin {

        @Override
        public void onEnable() {
            // Plugin startup logic
                Bukkit.getPluginCommand("test");
                Objects.requireNonNull(Bukkit.getPluginCommand("test")).setExecutor(this);
                Objects.requireNonNull(Bukkit.getPluginCommand("test")).setTabCompleter(this);

        }

        @Override
        public void onDisable() {
            // Plugin shutdown logic


        }


        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String label, String[]args)
        {
                if(sender instanceof Player)
                {
                        if(args.length>3) return null;
                        if(args.length==0||args.length==1)  return Collections.singletonList("请输入玩家名");
                        if(args.length==2) return Collections.singletonList("请输入奔跑时间");
                }
                return null;
        }
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[]args)
        {
                if(command.getName().equalsIgnoreCase("test"))
                {
                        if((sender instanceof Player)==false)
                        {
                                sender.sendMessage("你必须是一名玩家");
                                return true;
                        }
                        else{
                                for(int i=0;i<args.length;i++)
                                {
                                        System.out.println(args[i]);
                                }
                                return true;
                        }

                }
                return true;
        }
}




