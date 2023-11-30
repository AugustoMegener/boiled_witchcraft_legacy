package org.kitowashere.boiled_witchcraft.core.blood;

import java.util.function.Function;

public interface ITitanBloodHandler {

    int getAmount();

    void set(int value);

    default void sub(int amount) {
        set(getAmount() - amount);
    }

    default void add(int amount) {
        set(getAmount() + amount);
    }

    default void spendBlood(Function<Integer, Integer> spendAction) {
        sub(spendAction.apply(getAmount()));
    }

    default void absorbSource(ITitanBloodHandler source) {
        add(source.getAmount());
        source.sub(source.getAmount());
    }
}
