package net.pierceth.pierceth_greatsword.client.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pierceth.pierceth_greatsword.Constants;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;

public class PierceTHParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Constants.MOD_ID);

    //public static final RegistryObject<SimpleParticleType> CUSTOM_TRAIL = PARTICLES.register("custom_trail", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> DUST = PARTICLES.register("dust", () -> new SimpleParticleType(true));
}
