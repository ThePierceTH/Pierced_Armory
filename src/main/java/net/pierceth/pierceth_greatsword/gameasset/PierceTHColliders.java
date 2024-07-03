package net.pierceth.pierceth_greatsword.gameasset;

import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.MultiOBBCollider;

public class PierceTHColliders {

    public static final Collider BAHAMUT = new MultiOBBCollider(3, 0.4D, 0.4D, 1.8D, 0D, 0D, -1.0D);
    public static final Collider PONGUARD = new MultiOBBCollider(3, 0.2, 0.2, 1.8, 0.0, 0.0, -1.2D);
    public static final Collider CARVER = new MultiOBBCollider(3, 0.2, 0.2, 1.5, 0.0, 0.0, -1.2D);
}
