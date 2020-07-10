package com.mcip.fish;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class Fish extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Magic Fish Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Magic Fish Plugin Disabled!");
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        Entity caught = event.getCaught();
        Random rand = new Random();

        if (caught != null && rand.nextInt(100) < 15) {
            if (event.getCaught().getType() == EntityType.COD) {
                event.getCaught().remove();
                player.getInventory().addItem(getCod());
            }
            if (event.getCaught().getType() == EntityType.SALMON) {
                event.getCaught().remove();
                player.getInventory().addItem(getSalmon());
            }
            if (event.getCaught().getType() == EntityType.TROPICAL_FISH) {
                event.getCaught().remove();
                player.getInventory().addItem(getTropicalFish());
            }
            if (event.getCaught().getType() == EntityType.PUFFERFISH) {
                event.getCaught().remove();
                player.getInventory().addItem(getPufferfish());
            }
        }
    }

    @EventHandler
    public void onPlayerEat(FoodLevelChangeEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            Location loc = player.getLocation();
            if (event.getItem().getItemMeta().getLore()
                    .equals("God Cod is an enhanced fish that gives you Regeneration II for 30 seconds!")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 50));
            }
            if (event.getItem().getItemMeta().getLore()
                    .equals("Speedy Salmon is an enhanced fish that gives you Speed II for 30 seconds!")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 50));
            }
            if (event.getItem().getItemMeta().getLore()
                    .equals("Scuba Fish is an enhanced fish that gives you Under Water Breathing!")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 60, 50));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 60, 50));

            }
            if (event.getItem().getItemMeta().getLore().equals(
                    "The Jumpy Puffer is a enhanced fish that gives you Jump Boost II, but it also gives you Nausea!")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1200, 50));
                player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 1200, 50));

            }
        }

    }

    private ItemStack getCod() {
        ArrayList<String> lore = new ArrayList();
        lore.add("God Cod is an enhanced fish that gives you Regeneration II for 30 seconds!");
        ItemStack item = new ItemStack(Material.COD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "God Cod");
        meta.setLore(lore);
        item.addUnsafeEnchantment(Enchantment.LUCK, 1);
        item.setItemMeta(meta);

        return item;
    }

    private ItemStack getSalmon() {
        ArrayList<String> lore = new ArrayList();
        lore.add("Speedy Salmon is an enhanced fish that gives you Speed II for 30 seconds!");
        ItemStack item = new ItemStack(Material.SALMON);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Speedy Salmon");
        meta.setLore(lore);
        item.addUnsafeEnchantment(Enchantment.LUCK, 1);
        item.setItemMeta(meta);

        return item;
    }

    private ItemStack getTropicalFish() {
        ArrayList<String> lore = new ArrayList();
        lore.add("Scuba Fish is an enhanced fish that gives you Under Water Breathing!");
        ItemStack item = new ItemStack(Material.COD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "God Cod");
        meta.setLore(lore);
        item.addUnsafeEnchantment(Enchantment.LUCK, 1);
        item.setItemMeta(meta);

        return item;
    }

    private ItemStack getPufferfish() {
        ArrayList<String> lore = new ArrayList();
        lore.add("The Jumpy Puffer is a enhanced fish that gives you Jump Boost II, but it also gives you Nausea!");
        ItemStack item = new ItemStack(Material.COD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "The Jumpy Puffer");
        meta.setLore(lore);
        item.addUnsafeEnchantment(Enchantment.LUCK, 1);
        item.setItemMeta(meta);

        return item;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Error!" + ChatColor.RED
                    + " You can only run this command as a player");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            sender.sendMessage(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Error!" + ChatColor.RED
                    + " Incoorrect usage. Usage: /magicfish <cod | tropicalfish | pufferfish | salmon>");
            return true;
        }
        // needs permission if statement
        if (args[0].equalsIgnoreCase("cod")) {
            player.getInventory().addItem(getCod());
        }
        if (args[0].equalsIgnoreCase("salmon")) {
            player.getInventory().addItem(getCod());
        }
        if (args[0].equalsIgnoreCase("tropicalfish")) {
            player.getInventory().addItem(getCod());
        }
        if (args[0].equalsIgnoreCase("salmon")) {
            player.getInventory().addItem(getCod());
        }

        return true;
    }

}
