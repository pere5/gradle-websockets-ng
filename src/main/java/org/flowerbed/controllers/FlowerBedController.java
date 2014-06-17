package org.flowerbed.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.flowerbed.repository.example.StaticJSONExample;
import org.flowerbed.repository.plants.Flower;
import org.flowerbed.repository.plants.Spot;
import org.flowerbed.workers.Gardener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class FlowerBedController {

    private static Logger logger = Logger.getLogger(FlowerBedController.class);

    private final Gardener gardener;

    @Autowired
    public FlowerBedController(Gardener gardener) {
        this.gardener = gardener;
    }

    @RequestMapping(value = "someJSONData", method = RequestMethod.GET)
    public @ResponseBody StaticJSONExample someData(HttpSession session) {
        logger.info("Getting a static JSON");
        return new StaticJSONExample();
    }

    @RequestMapping(value = "plantFlower", method = RequestMethod.POST)
    public @ResponseBody List<List<Spot>> plantFlower(HttpSession session,
                                                      @RequestBody(required=true) Flower flower,
                                                      @RequestParam(required=true) int x,
                                                      @RequestParam(required=true) int y) {
        gardener.plant(flower, x, y);
        logger.info("Planting flower: " + flower + " x: " + Integer.toString(x) + " y: " + Integer.toString(y));
        return gardener.getFlowerBed();
    }

    @RequestMapping(value = "plantFlowerAnywhere", method = RequestMethod.POST)
    public @ResponseBody List<List<Spot>> plantFlowerAnywhere(HttpSession session,
                                                              @RequestBody(required=true) Flower flower) {
        gardener.plantAnywhere(flower);
        logger.info("Planting flower: " + flower);
        return gardener.getFlowerBed();
    }

    @RequestMapping(value = "weedFlowerAnywhere", method = RequestMethod.POST)
    public @ResponseBody List<List<Spot>> weedFlowerAnywhere(HttpSession session) {
        gardener.weedFlowerAnywhere();
        logger.info("A flower got weeded.");
        return gardener.getFlowerBed();
    }

    @RequestMapping(value = "weedFlower", method = RequestMethod.POST)
    public @ResponseBody List<List<Spot>> weedFlower(HttpSession session,
                                                     @RequestParam(required=true) int x,
                                                     @RequestParam(required=true) int y) {
        gardener.weed(x, y);
        logger.info(" x: " + Integer.toString(x) + " y: " + Integer.toString(y) + "got weeded.");
        return gardener.getFlowerBed();
    }

    @RequestMapping(value = "flowerbed.html", method = RequestMethod.GET)
    public String flowerbed(HttpSession session,
                            Model model,
                            @RequestParam(value="name", required=false, defaultValue="Dood!") String name) {
        Map<String, Object> attributesMap = new HashMap<>();
        attributesMap.put("name", name);
        model.addAllAttributes(attributesMap);
        return "flowerbed";
    }
}