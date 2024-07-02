package second.secondplugin;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent;
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
//                                player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
//                                Zombie zombie = (Zombie)player.getWorld().spawnEntity(player.getLocation(),EntityType.ZOMBIE);
//                                zombie.setCustomName("SB");
//                                zombie.setCustomNameVisible(true);
                                Location loc=player.getLocation();
                                ((CraftWorld)player.getWorld()).getHandle().addEntity(new EntityCustomCow(player.getLocation()), CreatureSpawnEvent.SpawnReason.CUSTOM);
//                                ((CraftWorld)player.getWorld()).getHandle().addEntity(new EntityCustomGiantZombie(player.getLocation()), CreatureSpawnEvent.SpawnReason.CUSTOM);
                                return true;
                        }
                }
                return false;
        }

//        public class EntityCustomGiantZombie extends EntityGiantZombie{
//
//                public EntityCustomGiantZombie(Location loc) {
//                        super(EntityTypes.GIANT, ((CraftWorld) loc.getWorld()).getHandle());
//                        setPosition(loc.getX(),loc.getY(),loc.getZ());
//                }
//        }
          public class EntityCustomCow extends EntityCow {

                 public EntityCustomCow(Location loc) {
                         super(EntityTypes.COW, ((CraftWorld) loc.getWorld()).getHandle());
                        setPosition(loc.getX(),loc.getY(),loc.getZ());
                 }
                 @Override
                 public void initPathfinder() {
                        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
                        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
                        this.goalSelector.a(8,new PathfinderGoalDiamonds(this));
                 }
                 public int food=0;

                 public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand)
                 {
                        ItemStack itemStack=entityhuman.b(enumhand);

                        if(itemStack.getItem()!=Items.DIAMOND)
                        return EnumInteractionResult.FAIL;
                        else if(itemStack.getItem() == Items.DIAMOND&&!this.isBaby()) {
                                 //钻石吃的数量+1
                                 food += 1;
                                System.out.println(itemStack.getItem());
                                this.a(entityhuman,itemStack);

                         }



                         return EnumInteractionResult.a(true);//判断是否是客户端
                 }

          }
        public class PathfinderGoalDiamonds extends PathfinderGoal {
                public EntityCustomCow cow;
                public int time;
                public PathfinderGoalDiamonds(EntityCustomCow cow) {
                        this.cow = cow;
                }
                @Override
                public boolean a() {
                        if(this.cow.food<6) return false;
                        else this.cow.food=0; return true;
                }

                @Override
                public boolean b() {
                        return this.time>=0;
                }

                @Override
                public  void c()
                {
                        this.time=60;

                };

                @Override
                public void e(){
                        this.time--;
                        this.cow.getBukkitEntity().getLocation().getBlock().setType(Material.DIAMOND_BLOCK);
                }
        }


}




