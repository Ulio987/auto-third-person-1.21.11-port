package agency.highlysuspect.autothirdperson.fabric.mixin;

import agency.highlysuspect.autothirdperson.AutoThirdPerson;
import agency.highlysuspect.autothirdperson.TwentyOneElevenAutoThirdPerson;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
	@Inject(
		method = "startRiding",
		at = @At("TAIL")
	)
	// 1.21.11: startRiding 由 2 参变 3 参 (Entity, boolean force, boolean playSound)，handler 参数必须同步
	private void autoThirdPerson$onStartRiding(Entity vehicle, boolean force, boolean playSound, CallbackInfoReturnable<Boolean> cir) {
		AutoThirdPerson atp = AutoThirdPerson.instance;
		
		atp.mount(new TwentyOneElevenAutoThirdPerson.EntityVehicle(vehicle));
	}
	
	@Inject(
		method = "removeVehicle",
		at = @At("HEAD")
	)
	private void autoThirdPerson$onStopRiding(CallbackInfo ci) {
		@SuppressWarnings("ConstantConditions")
		Entity vehicle = ((Entity) (Object) this).getVehicle();
		if(vehicle != null) {
			AutoThirdPerson atp = AutoThirdPerson.instance;
			
			atp.dismount(new TwentyOneElevenAutoThirdPerson.EntityVehicle(vehicle));
		}
	}
}
