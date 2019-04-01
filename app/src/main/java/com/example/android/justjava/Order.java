package com.example.android.justjava;

public class Order {
    private boolean hasCream;
    private boolean hasChocolate;
    private String ownerName;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isCream() {
        return hasCream;
    }

    public void setHasCream(boolean hasCream) {
        this.hasCream = hasCream;
    }

    public boolean isChocolate() {
        return hasChocolate;
    }

    public void setHasChocolate(boolean hasChocolate) {
        this.hasChocolate = hasChocolate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
