package dev.isxander.customshields.registry.items

import dev.isxander.customshields.utils.ItemSettings
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ShieldItem

abstract class CustomShield(settings: ItemSettings) : ShieldItem(settings) {
    open fun onAttackBlocked(victim: LivingEntity, source: DamageSource) {}
}
