package io.github.spigotrce.paradiseclientprivate.mixin.inject;

import net.minecraft.class_2678;
import net.minecraft.class_634;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_634.class})
public abstract class ClientPlayNetworkHandlerMixin {
   @Inject(
      method = {"onGameJoin"},
      at = {@At("TAIL")}
   )
   private void onGameJoin(class_2678 packet, CallbackInfo info) {
   }
}
