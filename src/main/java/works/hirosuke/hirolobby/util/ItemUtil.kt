package works.hirosuke.hirolobby.util

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

object ItemUtil {

    fun create(material: Material, name: String? = null, lore: List<String>? = null, enchantments: Map<Enchantment, Int>? = null, amount: Int? = null): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta!!
        meta.setDisplayName(name)
        meta.lore = lore
        if (amount != null) {
            item.amount = amount
        }
        enchantments?.forEach {
            meta.addEnchant(it.key, it.value, true)
        }

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        meta.addItemFlags(ItemFlag.HIDE_DYE)

        item.itemMeta = meta
        return item
    }
}