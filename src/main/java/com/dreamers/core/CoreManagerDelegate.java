package com.dreamers.core;

public interface CoreManagerDelegate extends CoreManagerDataSource {

    default void willDealCards() {
    }

    default void didDealCards() {
    }

    default void willReadMove() {
    }

    default void didReadMove() {
    }

    default void willPerformMove() {
    }

    default void didPerformMove() {
    }
}
