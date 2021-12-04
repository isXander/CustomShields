package dev.isxander.customshields.registry.items

import dev.isxander.customshields.registry.CustomShieldsRegistry
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ItemGroup
import net.minecraft.util.math.Vec3d
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class LightningShield : CustomShield(FabricItemSettings().maxDamage(336).group(ItemGroup.COMBAT)) {
    override fun onAttackBlocked(victim: LivingEntity, source: DamageSource) {
        val attacker = source.attacker ?: return
        val world = attacker.world

        val targetedLightning = CustomShieldsRegistry.RAINBOW_LIGHTNING.create(world) ?: return
        targetedLightning.setPosition(attacker.pos)
        world.spawnEntity(targetedLightning)

        for (i in 0 until 10) {
            val lightning = CustomShieldsRegistry.RAINBOW_LIGHTNING.create(world) ?: continue
            lightning.setPosition(getPositionInCircle(victim.pos, 10.0))
            world.spawnEntity(lightning)
        }

        super.onAttackBlocked(victim, source)
    }

    private fun getPositionInCircle(center: Vec3d, radius: Double): Vec3d {
        val r = radius * sqrt(Math.random())
        val theta = Math.random() * 2 * Math.PI

        val x = center.x + r * cos(theta)
        val z = center.z + r * sin(theta)

        return Vec3d(x, center.y, z)
    }
}
