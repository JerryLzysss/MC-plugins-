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

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public final class SecondPlugin extends JavaPlugin {

        @Override
        public void onEnable() {
            // Plugin startup logic
                Bukkit.getPluginManager().registerEvents(new PlayerJoin(),this);


        }

        @Override
        public void onDisable() {
            // Plugin shutdown logic


        }

        public void Profile(){
                //兼容为1.18.1+,也就是说1.16.5（我用的）无法使用这些。
//                PlayerProfile profile = Bukkit.getServer().createPlayerProfile(UUID.randomUUID(), "<玩家名>");
//                PlayerTextures textures = profile.getTextures();
//                try {
//                        textures.setSkin(new URL("http://textures.minecraft.net/texture/b3fbd454b599df593f57101bfca34e67d292a8861213d2202bb575da7fd091ac"));
//                } catch(Exception e) {
//                        e.printStackTrace();
//                        sender.sendMessage("发生异常：" + e.getMessage());
//                }

        }
}




