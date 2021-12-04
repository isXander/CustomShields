package dev.isxander.customshields.registry.items

import dev.isxander.customshields.utils.mc
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.world.World

class PickaxeShield : CustomShield(FabricItemSettings().maxDamage(100).group(ItemGroup.COMBAT), 100, 15, Items.WOODEN_PICKAXE) {
    override fun usageTick(world: World, user: LivingEntity, stack: ItemStack, remainingUseTicks: Int) {
        val hitResult = user.raycast(100.0, mc.tickDelta, false)

        if (hitResult is BlockHitResult) {
            world.breakBlock(hitResult.blockPos, true, user)
            stack.damage(1, user) { it.sendToolBreakStatus(user.activeHand) }
        }

        super.usageTick(world, user, stack, remainingUseTicks)
    }
}
