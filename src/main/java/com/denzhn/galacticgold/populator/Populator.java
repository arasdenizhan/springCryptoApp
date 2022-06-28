package com.denzhn.galacticgold.populator;

public interface Populator<S,T> {
    T populate(S source);
    S revertPopulate(T t);
    S createSourceInstance();
    T createTargetInstance();
}
