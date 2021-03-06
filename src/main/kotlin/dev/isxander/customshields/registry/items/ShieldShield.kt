package dev.isxander.customshields.registry.items

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ItemGroup
import net.minecraft.item.Items

class ShieldShield : CustomShield(FabricItemSettings().maxDamage(336).group(ItemGroup.COMBAT), 50, 28, Items.SHIELD) {
    override fun onAttackBlocked(victim: LivingEntity, source: DamageSource) {
        val attacker = source.attacker as? LivingEntity ?: return
        attacker.takeKnockback(5.0, victim.x - attacker.x, victim.z - attacker.z)
    }
}
