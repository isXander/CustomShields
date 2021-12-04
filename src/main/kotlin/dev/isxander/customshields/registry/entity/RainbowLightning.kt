package dev.isxander.customshields.registry.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.LightningEntity
import net.minecraft.world.World
import kotlin.random.Random

class RainbowLightning(
    entityType: EntityType<out RainbowLightning>,
    world: World,
    val red: Float = Random.nextFloat(),
    val green: Float = Random.nextFloat(),
    val blue: Float = Random.nextFloat()
) : LightningEntity(entityType, world)
