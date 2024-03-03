package net.pierceth.pierceth_greatsword.mixin.client;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public abstract class CameraMixin {

    @Shadow
    public abstract void move(double forwards, double up, double side);

    @Shadow
    protected abstract void setPosition(Vec3 vec3);

    @Shadow
    protected abstract double getMaxZoom(double p_90567_);

    @Shadow
    protected abstract void setRotation(float p_90573_, float p_90574_);

    @Shadow
    private float yRot;

    @Shadow
    private float xRot;

    @Shadow
    private boolean initialized;
}
