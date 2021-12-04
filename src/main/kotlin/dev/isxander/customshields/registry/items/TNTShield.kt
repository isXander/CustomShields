package dev.isxander.customshields.registry.items

import dev.isxander.customshields.utils.setVelocity
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class TNTShield : CustomShield(FabricItemSettings().maxDamage(336).group(ItemGroup.COMBAT), 200, 1, Items.TNT) {
    private var ticks = 0

    override fun usageTick(world: World, user: LivingEntity, stack: ItemStack, remainingUseTicks: Int) {
        if (ticks % 10 == 0) {
            val tnt = EntityType.TNT.create(world)

            if (tnt != null) {
                tnt.setPosition(user.pos)
                tnt.setVelocity(user, user.pitch, user.yaw, 0f, 1f, 1f)
                world.spawnEntity(tnt)
            }
        }
        ticks++

        super.usageTick(world, user, stack, remainingUseTicks)
    }

    override fun onStoppedUsing(stack: ItemStack?, world: World?, user: LivingEntity?, remainingUseTicks: Int) {
        println("STOP")
        ticks = 0
        super.onStoppedUsing(stack, world, user, remainingUseTicks)
    }
}
