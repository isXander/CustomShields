package dev.isxander.customshields.utils

import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

fun World.getBlockBelowOrNull(blockPos: BlockPos, limit: Int = this.topY, predicate: (BlockState) -> Boolean): BlockPos? {
    repeat(limit) {
        val blockPosBelow = blockPos.down(it)
        if (blockPosBelow.y < this.bottomY) return null

        val blockStateBelow = this.getBlockState(blockPosBelow)
        if (predicate(blockStateBelow)) {
            return blockPosBelow
        }
    }

    return null
}
