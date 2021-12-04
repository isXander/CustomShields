package dev.isxander.customshields.registry.items

import dev.isxander.customshields.registry.CustomShieldsRegistry
import dev.isxander.customshields.utils.mc
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class LightningShield : CustomShield(FabricItemSettings().maxDamage(336).group(ItemGroup.COMBAT), 1000, 30, Items.LIGHTNING_ROD) {
    override fun onAttackBlocked(victim: LivingEntity, source: DamageSource) {
        val attacker = source.attacker ?: return
        val world = attacker.world

        val targetedLightning = CustomShieldsRegistry.RAINBOW_LIGHTNING.create(world) ?: return
        targetedLightning.setPosition(attacker.pos)
        world.spawnEntity(targetedLightning)

        if (victim.isSneaking) {
            for (i in 0 until 10) {
                val lightning = CustomShieldsRegistry.RAINBOW_LIGHTNING.create(world) ?: continue
                lightning.setPosition(getPositionInCircle(victim.pos, 20.0))
                world.spawnEntity(lightning)
            }
        }

        super.onAttackBlocked(victim, source)
    }

    override fun usageTick(world: World, user: LivingEntity, stack: ItemStack, remainingUseTicks: Int) {
        if (!user.isSneaking) {
            val hitResult = user.raycast(100.0, mc.tickDelta, false)

            if (hitResult.type != HitResult.Type.MISS) {
                val targetedLightning = CustomShieldsRegistry.RAINBOW_LIGHTNING.create(world) ?: return
                targetedLightning.setPosition(hitResult.pos)
                world.spawnEntity(targetedLightning)
            }
        }

        super.usageTick(world, user, stack, remainingUseTicks)
    }

    private fun getPositionInCircle(center: Vec3d, radius: Double): Vec3d {
        val r = radius * sqrt(Math.random())
        val theta = Math.random() * 2 * Math.PI

        val x = center.x + r * cos(theta)
        val z = center.z + r * sin(theta)

        return Vec3d(x, center.y, z)
    }
}
