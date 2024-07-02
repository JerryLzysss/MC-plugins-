package second.secondplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
                                Inventory inv =player.getInventory();
                                inv.addItem(addEnchant());
//                                for(int i=0;i<inv.getContents().length;i++)
//                                System.out.println(inv.getContents()[i]);
                                return true;
                        }
                }
                return false;
        }
        public ItemStack addEnchant()
        {
                ItemStack sword=new ItemStack(Material.DIAMOND_SWORD);
                ItemMeta im=sword.getItemMeta();
                im.addEnchant(Enchantment.DAMAGE_ALL,10000,true);

                List<String> a=new ArrayList<>();
                a.add("Test");
                a.add("Test");
                im.setLore(a);
                im.addAttributeModifier(Attribute.GENERIC_ARMOR,new AttributeModifier(UUID.randomUUID(),"护甲",4.0,AttributeModifier.Operation.ADD_SCALAR));
                sword.setItemMeta(im);
                return sword;
        }
}




