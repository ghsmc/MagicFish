package com.mcip.fish;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

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

        if (caught != null && rand.nextInt(100) < 75) {
            if (event.getCaught() instanceof Item) {
                Item c = (Item) event.getCaught();
                if (c.getItemStack().getType() == Material.COD) {
                    c.remove();
                    player.getInventory().addItem(getCod());
                }
                if (c.getItemStack().getType() == Material.SALMON) {
                    c.remove();
                    player.getInventory().addItem(getSalmon());
                }
                if (c.getItemStack().getType() == Material.TROPICAL_FISH) {
                    c.remove();
                    player.getInventory().addItem(getTropicalFish());
                }
                if (c.getItemStack().getType() == Material.PUFFERFISH) {
                    c.remove();
                    player.getInventory().addItem(getPufferfish());
                }
            }
        }
    }

    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent event) {
        Player player = (Player) event.getPlayer();

        if (event.getItem().getItemMeta().getLore().get(0).equals("God Cod is an enhanced ")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 2));
        }
        if (event.getItem().getItemMeta().getLore().get(0).equals("Speedy Salmon is an enhanced ")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 2));
        }
        if (event.getItem().getItemMeta().getLore().get(0).equals("The Scuba Fish is an enhanced ")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1200, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1200, 1));

        }
        if (event.getItem().getItemMeta().getLore().get(0).equals("Jumpy Puffer is an enhanced ")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 600, 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 600, 1));

        }
    }

    private ItemStack getCod() {
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("God Cod is an enhanced ");
        lore.add("fish that gives you ");
        lore.add("Regeneration II for 30 seconds!");
        ItemStack item = new ItemStack(Material.COD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "God Cod");
        item.addUnsafeEnchantment(Enchantment.LURE, 1);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    private ItemStack getSalmon() {
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("Speedy Salmon is an enhanced ");
        lore.add("fish that gives you ");
        lore.add("Speed II for 30 seconds!");
        ItemStack item = new ItemStack(Material.SALMON);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Speedy Salmon");
        item.addUnsafeEnchantment(Enchantment.LURE, 1);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    private ItemStack getTropicalFish() {
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("The Scuba Fish is an enhanced ");
        lore.add("fish that gives you ");
        lore.add("Under Water Breathing abilities for 1 minute!");
        ItemStack item = new ItemStack(Material.TROPICAL_FISH);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Scuba Fish");
        item.addUnsafeEnchantment(Enchantment.LURE, 1);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    private ItemStack getPufferfish() {
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("Jumpy Puffer is an enhanced ");
        lore.add("fish that gives you ");
        lore.add("JumpBoost II for 30 seconds!");
        ItemStack item = new ItemStack(Material.PUFFERFISH);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "The Jumpy Puffer");
        item.addUnsafeEnchantment(Enchantment.LURE, 1);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(
                    ChatColor.DARK_RED + "Error!" + ChatColor.RED + " You can only run this command as a player");
            return true;
        }

        Player player = (Player) sender;
        if (player.hasPermission("magicfish.use")) {
            if (args.length != 1) {
                sender.sendMessage(ChatColor.DARK_RED + "Error!" + ChatColor.RED
                        + " Incoorrect usage.   Usage: /magicfish <cod | tropicalfish | pufferfish | salmon>");
                return true;
            }

            if (!(args[0].equalsIgnoreCase("cod") || args[0].equalsIgnoreCase("tropicalfish")
                    || args[0].equalsIgnoreCase("pufferfish") || args[0].equalsIgnoreCase("salmon"))) {
                sender.sendMessage(ChatColor.DARK_RED + "The fish you entered is not an option! " + ChatColor.RED
                        + "The options are: <cod | tropicalfish | pufferfish | salmon>");
            }
            if (args[0].equalsIgnoreCase("cod")) {
                player.getInventory().addItem(getCod());
            }
            if (args[0].equalsIgnoreCase("salmon")) {
                player.getInventory().addItem(getSalmon());
            }
            if (args[0].equalsIgnoreCase("tropicalfish")) {
                player.getInventory().addItem(getTropicalFish());
            }
            if (args[0].equalsIgnoreCase("pufferfish")) {
                player.getInventory().addItem(getPufferfish());
            }
        }
        return true;
    }
}