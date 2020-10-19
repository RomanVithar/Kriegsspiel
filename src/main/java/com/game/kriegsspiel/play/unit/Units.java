package com.game.kriegsspiel.play.unit;

import java.awt.*;

public abstract class Units {
    protected String playerName;
    protected UnitType unitType;
    protected Point position;
    protected int quantity;
    protected double strong;
    protected double armor;
    protected double lengthMove;

    public double getLengthMove() {
        return lengthMove;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Point getPosition() {
        return position;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getStrong() {
        return strong;
    }

    public double getArmor() {
        return armor;
    }

    public UnitType getUnitType() {
        return unitType;
    }
}
