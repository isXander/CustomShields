package dev.isxander.customshields.client

import dev.isxander.customshields.registry.CustomShieldsRegistry
import dev.isxander.customshields.registry.entity.render.RainbowLightningRenderer
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

object CustomShieldsClient : ClientModInitializer {
    override fun onInitializeClient() {
        EntityRendererRegistry.register(CustomShieldsRegistry.RAINBOW_LIGHTNING, ::RainbowLightningRenderer)
    }
}
