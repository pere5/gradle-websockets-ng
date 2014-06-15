package org.flowerbed.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.flowerbed.repository.plants.Flower;
import org.flowerbed.workers.Gardener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FlowerBedController {

    private static Logger logger = Logger.getLogger(FlowerBedController.class);

    private final Gardener gardener;

    @Autowired
    public FlowerBedController(Gardener gardener) {
        this.gardener = gardener;
    }

    @RequestMapping(value = "plantflower", method = RequestMethod.POST)
    public @ResponseBody void plantFlower(@RequestBody(required=true) Flower flower, int x, int y) {
        gardener.plant(flower, x, y);
        logger.info(flower + " x: " + Integer.toString(x) + " y: " + Integer.toString(y));
    }

    @RequestMapping(value = "weedflower", method = RequestMethod.POST)
    public @ResponseBody void weedFlower(int x, int y) {
        gardener.weed(x, y);
        logger.info(" x: " + Integer.toString(x) + " y: " + Integer.toString(y) + "got weeded.");
    }

    @RequestMapping(value = "flowerbed.html", method = RequestMethod.GET)
    public String flowerbed(Model model, @RequestParam(value="name", required=false, defaultValue="Dood!") String name) {
        Map<String, Object> attributesMap = new HashMap<>();
        attributesMap.put("name", name);
        model.addAllAttributes(attributesMap);
        return "flowerbed";
    }
}