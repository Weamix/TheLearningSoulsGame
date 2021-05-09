package lsg.armor;

import lsg.bags.Collectible;

public class DragonSlayerLeggings extends ArmorItem implements Collectible {
    public DragonSlayerLeggings() {
        super("Dragon Slayer Leggings", 10.2f);
    }

    @Override
    public int getWeight() {
        return 3;
    }
}
