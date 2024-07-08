package net.pierceth.pierceth_greatsword.common.data;

public class AnimConfig {
    private int nAttacks;
    private AnimType type;

    public AnimConfig(AnimType type, int nAttacks) {
        this.type = type;
        this.nAttacks = nAttacks;
    }

    public int getComboSize() {
        return nAttacks;
    }

    public AnimType getType() {
        return type;
    }
}
