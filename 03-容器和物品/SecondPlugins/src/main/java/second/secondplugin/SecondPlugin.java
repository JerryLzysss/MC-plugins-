package second.secondplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class SecondPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginCommand("test").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(new HandlerPlugin(),this);
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
                Player p=(Player) sender;
                Inventory v=Bukkit.createInventory(null,54,"容器");
                ItemStack is=new ItemStack(Material.WOODEN_SWORD);
                ItemMeta im =is.getItemMeta();
                im.setDisplayName("测试");
                im.setLore(Arrays.asList("一个功能"));
                is.setItemMeta(im);
                v.setItem(11,is);
//                v.addItem(is);
                v.setItem(1,is);
                p.openInventory(v);
                return true;
            }
        }
        return false;
    }



}
