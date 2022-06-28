package com.denzhn.galacticgold.populator;

import com.denzhn.galacticgold.exception.PopulatorException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public abstract class BasePopulator<S,T> implements Populator<S,T>{

    private final Class<T> targetClass;
    private final Class<S> sourceClass;

    protected BasePopulator(Class<S> sourceClass, Class<T> targetClass) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

    @Override
    public S createSourceInstance() {
        try {
            return this.sourceClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("PopulatorException -> {}", e.getMessage());
            throw new PopulatorException("Source Instance Creation Failed!", e);
        }
    }

    @Override
    public T createTargetInstance() {
        try {
            return this.targetClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("PopulatorException -> {}", e.getMessage());
            throw new PopulatorException("Target Instance Creation Failed!", e);
        }
    }
}
