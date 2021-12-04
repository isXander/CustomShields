package dev.isxander.customshields.registry

import dev.isxander.customshields.registry.entity.RainbowLightning
import dev.isxander.customshields.registry.items.*
import io.ejekta.kambrik.registration.KambrikAutoRegistrar
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.SpawnGroup

object CustomShieldsRegistry : KambrikAutoRegistrar {
    val SHIELD_SHIELD = "shield_shield" forItem ShieldShield()
    val PICKAXE_SHIELD = "pickaxe_shield" forItem PickaxeShield()
    val DIRT_SHIELD = "dirt_shield" forItem DirtShield()
    val TNT_SHIELD = "tnt_shield" forItem TNTShield()
    val LIGHTNING_SHIELD = "lightning_shield" forItem LightningShield()

    val RAINBOW_LIGHTNING = "rainbow_lightning" forEntityType
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, ::RainbowLightning).apply {
                disableSaving()
                dimensions(EntityDimensions.fixed(0f, 0f))
                trackRangeBlocks(16)
                trackedUpdateRate(Int.MAX_VALUE)
            }.build()
}
