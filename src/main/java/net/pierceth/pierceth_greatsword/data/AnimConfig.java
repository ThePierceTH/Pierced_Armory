package net.pierceth.pierceth_greatsword.data;

public enum AnimConfig {
    LIGHT_COMBO, LEFT_LIGHT_COMBO, RIGHT_LIGHT_COMBO, BACK_LIGHT_COMBO, HEAVY_COMBO, LEFT_HEAVY_COMBO, RIGHT_HEAVY_COMBO, BACK_HEAVY_COMBO, AIR_COMBO, DASH_COMBO;

    private int nAttacks;

    public AnimConfig setComboSize(int nAttacks) {
        this.nAttacks = nAttacks;
        return this;
    }

    public int getComboSize() {
        return nAttacks;
    }
}
