package com.fernandoperez;

import java.util.Objects;

public class Game {
    private String name;
    private String hash;

    public Game(String hash, String name) {
        this.hash = hash;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHash() {
        return hash;
    }
}
