package media.microbox.runes.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class Rune {

    private ItemStack rune;
    private Material material;
    private int amount;
    private String displayName;
    private String[] lore;

    private PotionEffectType potionEffect;
    private int duration;

    public Rune(Material material, int amount, String displayName, String[] lore, PotionEffectType potionEffect, int duration) {
        this.material = material;
        this.amount = amount;
        this.displayName = displayName;
        this.lore = lore;
        this.potionEffect = potionEffect;
        this.duration = duration;

        createRune();
    }

    private void createRune(){
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(Arrays.asList(lore));
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        rune = item;
    }

    public ItemStack getRune() {
        return rune;
    }

    public Material getMaterial() {
        return material;
    }

    public int getAmount() {
        return amount;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String[] getLore() {
        return lore;
    }

    public PotionEffectType getPotionEffect() {
        return potionEffect;
    }

    public int getDuration() {
        return duration;
    }
}
