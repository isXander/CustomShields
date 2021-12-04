package dev.isxander.customshields.registry.entity.render

import dev.isxander.customshields.registry.entity.RainbowLightning
import dev.isxander.customshields.utils.EntityRendererContext
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.LightningEntityRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.LightningEntity
import net.minecraft.screen.PlayerScreenHandler
import net.minecraft.util.Identifier
import net.minecraft.util.math.Matrix4f
import java.util.*

class RainbowLightningRenderer(context: EntityRendererContext) : EntityRenderer<RainbowLightning>(context) {
    override fun render(
        lightningEntity: RainbowLightning,
        f: Float,
        g: Float,
        matrixStack: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        i: Int
    ) {
        val fs = FloatArray(8)
        val gs = FloatArray(8)
        var h = 0.0f
        var j = 0.0f
        var random: Any = Random(lightningEntity.seed)
        for (k in 7 downTo 0) {
            fs[k] = h
            gs[k] = j
            h += ((random as Random).nextInt(11) - 5).toFloat()
            j += (random.nextInt(11) - 5).toFloat()
        }
        random = vertexConsumerProvider.getBuffer(RenderLayer.getLightning())
        val k = matrixStack.peek().positionMatrix
        for (l in 0..3) {
            val random2 = Random(lightningEntity.seed)
            for (m in 0..2) {
                var n = 7
                var o = 0
                if (m > 0) {
                    n = 7 - m
                }
                if (m > 0) {
                    o = n - 2
                }
                var p = fs[n] - h
                var q = gs[n] - j
                for (r in n downTo o) {
                    val s = p
                    val t = q
                    if (m == 0) {
                        p += (random2.nextInt(11) - 5).toFloat()
                        q += (random2.nextInt(11) - 5).toFloat()
                    } else {
                        p += (random2.nextInt(31) - 15).toFloat()
                        q += (random2.nextInt(31) - 15).toFloat()
                    }
                    var y = 0.1f + l.toFloat() * 0.2f
                    if (m == 0) {
                        y = (y.toDouble() * (r.toDouble() * 0.1 + 1.0)).toFloat()
                    }
                    var z = 0.1f + l.toFloat() * 0.2f
                    if (m == 0) {
                        z *= (r - 1).toFloat() * 0.1f + 1.0f
                    }

                    drawBranch(
                        k,
                        random, p, q, r, s, t, lightningEntity.red, lightningEntity.green, lightningEntity.blue, y, z, false, false, true, false
                    )
                    drawBranch(
                        k,
                        random, p, q, r, s, t, lightningEntity.red, lightningEntity.green, lightningEntity.blue, y, z, true, false, true, true
                    )
                    drawBranch(
                        k,
                        random, p, q, r, s, t, lightningEntity.red, lightningEntity.green, lightningEntity.blue, y, z, true, true, false, true
                    )
                    drawBranch(
                        k,
                        random, p, q, r, s, t, lightningEntity.red, lightningEntity.green, lightningEntity.blue, y, z, false, true, false, false
                    )
                }
            }
        }
    }

    private fun drawBranch(matrix: Matrix4f, buffer: VertexConsumer, x1: Float, z1: Float, y: Int, x2: Float, z2: Float, red: Float, green: Float, blue: Float, offset2: Float, offset1: Float, shiftEast1: Boolean, shiftSouth1: Boolean, shiftEast2: Boolean, shiftSouth2: Boolean) {
        buffer
            .vertex(
                matrix,
                x1 + if (shiftEast1) offset1 else -offset1,
                (y * 16).toFloat(),
                z1 + if (shiftSouth1) offset1 else -offset1
            )
            .color(red, green, blue, 0.3f)
            .next()

        buffer
            .vertex(
                matrix,
                x2 + if (shiftEast1) offset2 else -offset2,
                ((y + 1) * 16).toFloat(),
                z2 + if (shiftSouth1) offset2 else -offset2
            )
            .color(red, green, blue, 0.3f)
            .next()

        buffer
            .vertex(
                matrix,
                x2 + if (shiftEast2) offset2 else -offset2,
                ((y + 1) * 16).toFloat(),
                z2 + if (shiftSouth2) offset2 else -offset2
            )
            .color(red, green, blue, 0.3f)
            .next()

        buffer
            .vertex(
            matrix,
                x1 + if (shiftEast2) offset1 else -offset1,
                (y * 16).toFloat(),
                z1 + if (shiftSouth2) offset1 else -offset1
            )
            .color(red, green, blue, 0.3f)
            .next()
    }

    override fun getTexture(entity: RainbowLightning): Identifier = PlayerScreenHandler.BLOCK_ATLAS_TEXTURE
}
