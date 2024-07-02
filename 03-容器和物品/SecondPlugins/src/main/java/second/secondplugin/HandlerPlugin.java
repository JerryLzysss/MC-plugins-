package second.secondplugin;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class HandlerPlugin implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e)
    {


        Player p=(Player)e.getWhoClicked();
        if(e.getView().getTitle().equals("容器")) {
            System.out.println(p.getName()+e.getSlot());
            p.sendMessage("恭喜你!完成测试!");
            p.closeInventory();
            e.setCancelled(true);
        }
    }
}
