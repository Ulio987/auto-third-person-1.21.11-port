package agency.highlysuspect.autothirdperson.fabric;

import agency.highlysuspect.autothirdperson.TwentyOneElevenAutoThirdPerson;
import agency.highlysuspect.autothirdperson.config.ConfigSchema;
import agency.highlysuspect.autothirdperson.config.CookedConfig;
import agency.highlysuspect.crummyconfig.CrummyConfig2;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
// fabric-resource-loader-v1: v0 的 SimpleSynchronousResourceReloadListener / IdentifiableResourceReloadListener 已 @Deprecated
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
// 1.21.11: ResourceLocation 改名 Identifier（全代码库，工厂方法 fromNamespaceAndPath 不变）
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import org.lwjgl.glfw.GLFW;

public class FabricEntrypoint extends TwentyOneElevenAutoThirdPerson implements ClientModInitializer {
	private final KeyMapping TOGGLE_MOD = KeyBindingHelper.registerKeyBinding(new KeyMapping(
		"autothirdperson.toggle",
		InputConstants.Type.KEYSYM,
		GLFW.GLFW_KEY_UNKNOWN,
		// 1.21.11: 分类参数由字符串变为 KeyMapping.Category record
		KeyMapping.Category.MISC
	));
	
	@Override
	public void onInitializeClient() {
		init();
	}
	
	@Override
	public void init() {
		super.init();
		
		ClientTickEvents.START_CLIENT_TICK.register(__ -> tickClient());
		
		//Load it on F3+T
		ResourceLoader.get(PackType.CLIENT_RESOURCES).registerReloader(
			Identifier.fromNamespaceAndPath(MODID, "settings_reloader"),
			(ResourceManagerReloadListener) resourceManager -> refreshConfig()
		);
		
		//Load it on execution of client command
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
			dispatcher.register(
				ClientCommandManager.literal(MODID).then(
					ClientCommandManager.literal("reload").executes(s -> {
						refreshConfig();
						s.getSource().sendFeedback(Component.literal(NAME + " settings reloaded"));
						return 0;
					}))));
	}
	
	@Override
	public CookedConfig makeConfig(ConfigSchema s) {
		return new CrummyConfig2(s, FabricLoader.getInstance().getConfigDir().resolve(MODID + ".cfg"));
	}
	
	@Override
	public boolean modEnableToggleKeyPressed() {
		return TOGGLE_MOD.isDown();
	}
}
