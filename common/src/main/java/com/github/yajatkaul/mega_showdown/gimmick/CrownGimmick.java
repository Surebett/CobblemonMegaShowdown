package com.github.yajatkaul.mega_showdown.gimmick;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.github.yajatkaul.mega_showdown.item.MegaShowdownItems;
import com.github.yajatkaul.mega_showdown.item.custom.form_change.FormChangeHeldItemHidden;
import net.minecraft.world.item.Item;

public class CrownGimmick {
    public static void crownToggle(Pokemon pokemon) {
        Item itemStack = pokemon.heldItem().getItem();
        if (isCrowned(pokemon)) {
            if (itemStack instanceof FormChangeHeldItemHidden formChangeHeldItemHidden) {
                formChangeHeldItemHidden.revert(pokemon);
            }
        } else {
            if (itemStack instanceof FormChangeHeldItemHidden formChangeHeldItemHidden) {
                formChangeHeldItemHidden.apply(pokemon);
            }
        }
    }

    public static boolean isCrowned(Pokemon pokemon) {
        return pokemon.getAspects().contains("crowned");
    }

    public static boolean canCrown(Pokemon pokemon) {
        if (pokemon.getSpecies().getName().equals("Zacian")) {
            return pokemon.heldItem().is(MegaShowdownItems.RUSTED_SWORD.get());
        } else if (pokemon.getSpecies().getName().equals("Zamazenta")) {
            return pokemon.heldItem().is(MegaShowdownItems.RUSTED_SHIELD.get());
        }

        return false;
    }
}
