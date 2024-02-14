package net.pierceth.pierceth_greatsword.network;

import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkManager {
    private static SimpleChannel INSTANCE;
    private static int ID;
    private static int id(){
        return ID++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(PiercethGreatsword.MODID, "message"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(CameraShake.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(CameraShake::new)
                .encoder(CameraShake::encode)
                .consumer(CameraShake::handle)
                .add();
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}