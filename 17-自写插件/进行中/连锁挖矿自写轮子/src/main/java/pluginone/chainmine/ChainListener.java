package pluginone.chainmine;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.*;


public class ChainListener implements Listener {
    public int[] ArrX =new int[]{0,0,0,0,1,-1};
    public int[] ArrY=new int[]{1,-1,0,0,0,0};
    public int[] ArrZ=new int[]{0,0,1,-1,0,0};

    public String[] mt=new String[]{"Material.DIAMOND_PICKAXE","Material.GOLDEN_PICKAXE","Material.IRON_PICKAXE","Material.STONE_PICKAXE"};
    public String[] mt2=new String[]{"Material.DIAMOND_ORE","Material.GOLD_ORE","Material.IRON_ORE","Material.COAL_ORE"};

    public String[]mat=new String[]{"钻石矿","金矿","银矿","煤矿"};


    @EventHandler
    public boolean Mine(BlockBreakEvent e)
    {
        System.out.println(e.getPlayer());
        Player player =e.getPlayer();
        PlayerInventory inv=player.getInventory();
        ItemStack im=inv.getItemInMainHand();

        Block DestoryBlock=e.getBlock();



        Map<Enchantment,Integer>p=im.getEnchantments();
        Material DestoryMaterial=DestoryBlock.getType();
        World world=e.getBlock().getWorld();
        Location DestoryBlockPosition=e.getBlock().getLocation();
        boolean Acc=false;

        for (Map.Entry<Enchantment, Integer> entry : p.entrySet()) {
            Enchantment key = entry.getKey();
            String keyS=key.toString();
            if(keyS.equals("Enchantment[minecraft:silk_touch, SILK_TOUCH]"))
            Acc=true;
        }

//       获得不到下界合金矿的Material..
//        if(DestoryBlock.getType().equals(Material.NETHER_GOLD_ORE))
//        {
//            System.out.println("这是下界合金矿!");
//        }
        if(check2(DestoryMaterial,mt2[0])&&(check(im,mt[0])||check(im,mt[2])))
        {
            if(Acc==true) NearCal(player,DestoryBlockPosition,world,Material.DIAMOND_ORE,true);
            else NearCal(player,DestoryBlockPosition,world,Material.DIAMOND_ORE,false);

        }
        else if(check2(DestoryMaterial,mt2[1])&&(check(im,mt[0])||check(im,mt[1])||check(im,mt[2])))
        {
            if(Acc==true) NearCal(player,DestoryBlockPosition,world,Material.GOLD_ORE,true);
            else NearCal(player,DestoryBlockPosition,world,Material.GOLD_ORE,true);


        }
        else if(check2(DestoryMaterial,mt2[2])&&(check(im,mt[0])||check(im,mt[1])||check(im,mt[2])||check(im,mt[3])))
        {
            if(Acc==true) NearCal(player,DestoryBlockPosition,world,Material.IRON_ORE,true);
            else NearCal(player,DestoryBlockPosition,world,Material.IRON_ORE,true);

        }
        else if(check2(DestoryMaterial,mt2[3])&&(check(im,mt[0])||check(im,mt[1])||check(im,mt[2])||check(im,mt[3]))){

            if(Acc==true) NearCal(player,DestoryBlockPosition,world,Material.COAL_ORE,true);
            else NearCal(player,DestoryBlockPosition,world,Material.COAL_ORE,false);

        }
        return true;
    }
    public boolean check(ItemStack x,String y)
    {
        String XS="Material."+x.getType();
        return  XS.equals(y);
    }
    public boolean check2(Material x,String y)
    {
        String XS="Material."+x.toString();
        return  XS.equals(y);
    }
    public void NearCal(Player player, Location loc, World world, Material material,Boolean acc)
    {

        PlayerInventory inv=player.getInventory();
        ItemStack im=inv.getItemInMainHand();
        Damageable dmg= (Damageable) im.getItemMeta();
        Set<Location> mp= new HashSet<>();
        Queue<Location>q= new LinkedList<>();
        q.add(loc);
        mp.add(loc);
        while(q.size()!=0) {
            for (int i = 0; i < 6; i++)
            {

                Location nowLoc=q.peek();

                Location nextLoc=new Location(world, nowLoc.getX()+ArrX[i], nowLoc.getY()+ArrY[i], nowLoc.getZ()+ArrZ[i]);

                if(nextLoc.getBlock().getType().equals(material)&&mp.contains(nextLoc)==false)
                {
                    mp.add(nextLoc);
                    q.add(nextLoc);

                }
            }
            q.remove();
        }
        int maxnum=0;
        if(im.getType().equals(Material.DIAMOND_PICKAXE))
        {
            maxnum=1562;
        }
        else if(im.getType().equals(Material.GOLDEN_PICKAXE))
        {
            maxnum=33;
        }
        else if(im.getType().equals(Material.IRON_PICKAXE))
        {
            maxnum=251;
        }
        else if(im.getType().equals(Material.STONE_PICKAXE))
        {
            maxnum=60;
        }
        int number=0;
        for(Location mploc: mp)
        {

            if(dmg.getDamage()+1<=maxnum) {

                dmg.setDamage(dmg.getDamage()+1);
                im.setItemMeta((ItemMeta) dmg);
                mploc.getBlock().setType(Material.AIR);
                number++;
            }
            else{
                number++;
                im.setAmount(-1);
                break;
            }

        }
        mp.clear();
        if(check2(material,mt2[0])) player.sendMessage("[Jerry]本次掉落物为:§b "+mat[0]+" "+number+" §f个");
        else if(check2(material,mt2[1])) player.sendMessage("[Jerry]本次掉落物为:§6 "+mat[1]+" "+number+" §f个");
        else if(check2(material,mt2[2])) player.sendMessage("[Jerry]本次掉落物为:§7 "+mat[2]+" "+number+" §f个");
        else if(check2(material,mt2[3])) player.sendMessage("[Jerry]本次掉落物为:§8 "+mat[3]+" "+number+" §f个");
        if(acc==false)
        {
            if(check2(material,mt2[0]))
                material=material.DIAMOND;
            if(check2(material,mt2[3]))
                material=material.COAL;
        }
        if(number!=0) {
            ItemStack is = new ItemStack(material, number);
            world.dropItem(loc, is);
        }

    }


}
