package net.pierceth.pierceth_greatsword.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.pierceth.pierceth_greatsword.Constants;
import net.pierceth.pierceth_greatsword.client.model.MidasShoulderGuardModel;
import net.pierceth.pierceth_greatsword.common.init.VOSItems;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

public class MidasShoulderGuardLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID,"textures/item/midas_shoulder_guard.png");
    public final MidasShoulderGuardModel<T> shoulderGuardModel;

    public MidasShoulderGuardLayer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
        this.shoulderGuardModel = new MidasShoulderGuardModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MidasShoulderGuardModel.MIDAS_SHOULDER_GUARD));
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        ItemStack itemStack = pLivingEntity.getItemBySlot(EquipmentSlot.MAINHAND);
        PlayerPatch<?> playerPatch = EpicFightCapabilities.getEntityPatch(pLivingEntity, PlayerPatch.class);
        if (this.shouldRender(itemStack, pLivingEntity)) {
            ResourceLocation resourceLocation = this.getShouldGuardTexture(itemStack, pLivingEntity);
            pPoseStack.pushPose();
            this.getParentModel().copyPropertiesTo(this.shoulderGuardModel);
            if (!playerPatch.isBattleMode()) this.copyArmRotations();
            VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(pBuffer, RenderType.armorCutoutNoCull(resourceLocation), false, itemStack.hasFoil());
            this.shoulderGuardModel.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            pPoseStack.popPose();
        }
    }

    public boolean shouldRender(ItemStack itemStack, T entity) {
        return itemStack.is(VOSItems.MIDAS_GAUNTLET.get());
    }

    public void copyArmRotations() {
        HumanoidModel<?> humanoidModel = this.getParentModel();
        this.shoulderGuardModel.main.x = humanoidModel.rightArm.x - 1.3F;
        this.shoulderGuardModel.main.y = humanoidModel.rightArm.y - 0.5F;
        this.shoulderGuardModel.main.z = humanoidModel.rightArm.z + 0.5F;
        this.shoulderGuardModel.main.xRot = humanoidModel.rightArm.xRot + 85 * Mth.DEG_TO_RAD;
        this.shoulderGuardModel.main.yRot = humanoidModel.rightArm.yRot;
        this.shoulderGuardModel.main.zRot = humanoidModel.rightArm.zRot;
    }

    public ResourceLocation getShouldGuardTexture(ItemStack itemStack, T entity) {
        return TEXTURE;
    }
}
