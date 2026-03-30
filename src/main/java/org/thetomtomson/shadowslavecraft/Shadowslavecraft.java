package org.thetomtomson.shadowslavecraft;

import com.mojang.logging.LogUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.slf4j.Logger;
import org.thetomtomson.shadowslavecraft.data.ModDataAttachments;
import org.thetomtomson.shadowslavecraft.data.player.custom.SpellPlayerData;
import org.thetomtomson.shadowslavecraft.keyinput.custom.ModKeyMappings;
import org.thetomtomson.shadowslavecraft.networking.SyncSpellDataPayload;
import org.thetomtomson.shadowslavecraft.screen.ModMenuTypes;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Shadowslavecraft.MODID)
public class Shadowslavecraft {
    public static final String MODID = "shadowslavecraft";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Shadowslavecraft(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ModKeyMappings.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    private void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ModKeyMappings.OPEN_GUI_KEY);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
    public class ClientPacketHandler {
        public static void handleSync(final SyncSpellDataPayload payload, final net.neoforged.neoforge.network.handling.IPayloadContext context) {
            context.enqueueWork(() -> {
                net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
                if (mc.player != null) {
                    // Pobieramy lokalne dane i ładujemy do nich NBT z serwera
                    mc.player.getData(ModDataAttachments.SPELL_DATA).load(payload.dataTag(), mc.level.registryAccess()); //
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            syncPlayerData(serverPlayer);
        }
    }

    public static void syncPlayerData(ServerPlayer player) {
        SpellPlayerData data = player.getData(ModDataAttachments.SPELL_DATA);
        CompoundTag nbt = data.save(new CompoundTag(), player.registryAccess()); //

        // Wysyłamy pakiet (używając PacketDistributor)
        PacketDistributor.sendToPlayer(player, new SyncSpellDataPayload(nbt));
    }

    @SuppressWarnings("removal")
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
