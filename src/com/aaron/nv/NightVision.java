package com.aaron.nv;

import java.io.File; 

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVision extends JavaPlugin
  implements Listener
{
	
  public void onEnable()
  {
	Bukkit.getConsoleSender().sendMessage("§7[§aNightVision§7]§b Has been Enabled");
	File file = new File(getDataFolder() + "config.yml");
    if(!file.exists()){
        saveDefaultConfig();
    }
  }
  
  public void onDisable()
  {
	Bukkit.getConsoleSender().sendMessage("§7[§aNightVision§7]§4 has been Disabled!");
	reloadConfig();
  }
 
  public boolean nightToggled = false;
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
	  Player p = (Player)sender;
	  if (cmd.getName().equalsIgnoreCase("Nightvision"))
		  if (p.hasPermission("donator.nv"))
		  {
			  {
      if (!nightToggled) {
    	  nightToggled = true;
    	  	p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000000, 0));  
    	  	p.sendMessage(getConfig().getString("EnableMessage").replace("&", "§"));
    	  	
    	  	return true;
      } 
      else if (nightToggled){
    		nightToggled = false;  
    	  		for (PotionEffect effect : p.getActivePotionEffects())
    	  			p.removePotionEffect(effect.getType());
    	  			p.sendMessage(getConfig().getString("DisableMessage").replace("&", "§"));
    	  			
    	  			return true;
    	  }
      }
    }
	return false;
  }
}