package dev.isxander.customshields.registry.items

import com.github.crimsondawn45.fabricshieldlib.lib.`object`.FabricShieldItem
import dev.isxander.customshields.utils.ItemSettings
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.Item
import net.minecraft.item.ShieldItem

abstract class CustomShield(settings: ItemSettings, cooldownTicks: Int, enchantability: Int, vararg repairItems: Item) : FabricShieldItem(settings, cooldownTicks, enchantability, *repairItems) {
    open fun onAttackBlocked(victim: LivingEntity, source: DamageSource) {}
}
