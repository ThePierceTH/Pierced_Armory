package net.pierceth.pierceth_greatsword.network;

import net.pierceth.pierceth_greatsword.client.CameraEngine;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CameraShake {
    private final int time;
    private final float strength;
    private final float frequency;
    public CameraShake(int time, float strength, float frequency){
        this.time = time;
        this.strength = strength;
        this.frequency = frequency;
    }
    public CameraShake(FriendlyByteBuf buf){
        this.time = buf.readInt();
        this.strength = buf.readFloat();
        this.frequency = buf.readFloat();
    }
    public void encode(FriendlyByteBuf buf){
        buf.writeInt(this.time);
        buf.writeFloat(this.strength);
        buf.writeFloat(this.frequency);
    }

    public void handle(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() -> CameraEngine.getInstance().shakeCamera(this.time,this.strength,this.frequency));
        context.get().setPacketHandled(true);
    }
}