package dev.isxander.customshields.mixins;

import dev.isxander.customshields.registry.items.CustomShield;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @Inject(method = "blockedByShield", at = @At("RETURN"))
    public void onDamage(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            LivingEntity self = (LivingEntity) (Object) this;

            Item item = self.getActiveItem().getItem();
            if (item instanceof CustomShield shield) {
                shield.onAttackBlocked(self, source);
            }
        }
    }
}
