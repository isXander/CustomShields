package dev.isxander.customshields.utils

import dev.isxander.customshields.mixins.AccessorEntity
import net.minecraft.entity.Entity
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d

fun Entity.setVelocity(x: Double, y: Double, z: Double, speed: Float, divergence: Float) {
    val random = (this as AccessorEntity).random

    val vec3d = Vec3d(x, y, z).normalize().add(
        random.nextGaussian() * 0.0075 * divergence.toDouble(),
        random.nextGaussian() * 0.0075 * divergence.toDouble(),
        random.nextGaussian() * 0.0075 * divergence.toDouble()
    ).multiply(speed.toDouble())
    velocity = vec3d
    val d = vec3d.horizontalLength()
    yaw = (MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875).toFloat()
    pitch = (MathHelper.atan2(vec3d.y, d) * 57.2957763671875).toFloat()
    prevYaw = yaw
    prevPitch = pitch
}

fun Entity.setVelocity(shooter: Entity, pitch: Float, yaw: Float, roll: Float, speed: Float, divergence: Float) {
    val f = -MathHelper.sin(yaw * (Math.PI.toFloat() / 180)) * MathHelper.cos(pitch * (Math.PI.toFloat() / 180))
    val g = -MathHelper.sin((pitch + roll) * (Math.PI.toFloat() / 180))
    val h = MathHelper.cos(yaw * (Math.PI.toFloat() / 180)) * MathHelper.cos(pitch * (Math.PI.toFloat() / 180))
    this.setVelocity(f.toDouble(), g.toDouble(), h.toDouble(), speed, divergence)
    val vec3d = shooter.velocity
    velocity = velocity.add(vec3d.x, if (shooter.isOnGround) 0.0 else vec3d.y, vec3d.z)
}
