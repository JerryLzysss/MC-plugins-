package second.secondplugin;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


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
                                //添加药水效果
                                //player.addPotionEffect(new PotionEffect(PotionEffectType.POISON,100,1));
                                //添加音效
                                //player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT,1,1);
                               //player.spawnParticle(Particle.CLOUD,player.getLocation(),10);
                                return true;
                        }
                }
                return false;
        }




}




