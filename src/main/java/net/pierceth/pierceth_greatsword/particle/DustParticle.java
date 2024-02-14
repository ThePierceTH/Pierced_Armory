package net.pierceth.pierceth_greatsword.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DustParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    DustParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet spriteset, double dX, double dY, double dZ) {
        super(pLevel, pX, pY, pZ, dX, dY, dZ);

        //this.friction = 0.999F;
        this.xd = dX;
        this.yd = dY;
        this.zd = dZ;
        this.lifetime = 10;
        this.setSpriteFromAge(spriteset);
        sprites = spriteset;

        this.rCol = 1F;
        this.gCol = 1F;
        this.bCol = 1F;

        this.quadSize = 0.1F;
    }

    @Override
    public void tick() {
        super.tick();

        this.quadSize = this.quadSize + 0.05F;
        this.alpha = (-(age / (float) lifetime) * 0.5F + 0.5F);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new DustParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
