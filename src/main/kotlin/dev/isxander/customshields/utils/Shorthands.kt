package dev.isxander.customshields.utils

import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.item.Item
import java.awt.Color

typealias ItemSettings = Item.Settings
typealias EntityRendererContext = EntityRendererFactory.Context

val mc: MinecraftClient
    get() = MinecraftClient.getInstance()

fun getRandomColor(): Color {
    return Color((Math.random() * 0xFFFFFF).toInt())
}
