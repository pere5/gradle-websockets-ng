package org.flowerbed.repository.plants;

/**
 * Created by pere5 on 11/06/14.
 */
import java.util.List;


public interface FlowerBed {

    List<Flower> getFlowerBed();

    void plantFlower(Flower flower);

}