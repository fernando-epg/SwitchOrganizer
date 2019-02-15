package com.fernandoperez;

import java.util.Objects;

public class Game {
    private String name;
    private String hash;

    public Game(String hash) {
        this.hash = hash;
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
