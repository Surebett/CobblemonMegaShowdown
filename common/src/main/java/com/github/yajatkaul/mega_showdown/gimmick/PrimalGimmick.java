package com.github.yajatkaul.mega_showdown.gimmick;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.github.yajatkaul.mega_showdown.item.MegaShowdownItems;
import com.github.yajatkaul.mega_showdown.item.custom.form_change.FormChangeHeldItemHidden;
import net.minecraft.world.item.Item;

public class PrimalGimmick {
    public static void primalToggle(Pokemon pokemon) {
        Item itemStack = pokemon.heldItem().getItem();
        if (isPrimal(pokemon)) {
            if (itemStack instanceof FormChangeHeldItemHidden formChangeHeldItemHidden) {
                formChangeHeldItemHidden.revert(pokemon);
            }
        } else {
            if (itemStack instanceof FormChangeHeldItemHidden formChangeHeldItemHidden) {
                formChangeHeldItemHidden.apply(pokemon);
            }
        }
    }

    public static boolean isPrimal(Pokemon pokemon) {
        return pokemon.getAspects().contains("primal");
    }

    public static boolean canPrimal(Pokemon pokemon) {
        if (pokemon.getSpecies().getName().equals("Groudon")) {
            return pokemon.heldItem().is(MegaShowdownItems.RED_ORB.get());
        } else if (pokemon.getSpecies().getName().equals("Kyogre")) {
            return pokemon.heldItem().is(MegaShowdownItems.BLUE_ORB.get());
        }

        return false;
    }
}
