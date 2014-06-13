package org.flowerbed.repository.plants;

/**
 * Created by pere5 on 13/06/14.
 */
public class EmptySpot implements Spot {
    private boolean empty = true;

    public EmptySpot() {}

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
