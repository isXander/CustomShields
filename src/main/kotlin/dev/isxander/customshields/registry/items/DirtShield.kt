package dev.isxander.customshields.registry.items

import dev.isxander.customshields.utils.getBlockBelowOrNull
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Blocks
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.world.World

class DirtShield : CustomShield(FabricItemSettings().maxDamage(50).group(ItemGroup.COMBAT), 15, 3, Items.DIRT) {
    private var ticks = 0

    override fun usageTick(world: World, user: LivingEntity, stack: ItemStack, remainingUseTicks: Int) {
        if (ticks % 10 == 0) {
            val ground = world.getBlockBelowOrNull(user.blockPos, 5) { !it.isAir }?.up()
            if (ground != null) {
                if (user.isOnGround) {
                    user.setVelocity(user.velocity.x, 0.5, user.velocity.z)
                }

                if (user.blockPos == ground) {
                    world.setBlockState(ground.down(), Blocks.DIRT.defaultState)
                } else {
                    world.setBlockState(ground, Blocks.DIRT.defaultState)
                }

                stack.damage(1, user) { user.sendToolBreakStatus(user.activeHand) }
            }
        }
        ticks++

        super.usageTick(world, user, stack, remainingUseTicks)
    }

    override fun onStoppedUsing(stack: ItemStack?, world: World?, user: LivingEntity?, remainingUseTicks: Int) {
        ticks = 0

        super.onStoppedUsing(stack, world, user, remainingUseTicks)
    }
}
