package org.flowerbed.repository.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pere5 on 17/06/14.
 */
public class StaticJSONExample {
    private String name = "Example";
    private String type = "to JSON serializable Java object";
    private List<String> someDataList = new ArrayList<>();
    private Map<Integer, String> someDataMap = new HashMap<>();

    public StaticJSONExample() {
        someDataList.add("Information");
        someDataList.add("regarding");
        someDataList.add("this");
        someDataList.add("very");
        someDataList.add("useful");
        someDataList.add("object.");
        someDataMap.put(1, "I");
        someDataMap.put(1, "am");
        someDataMap.put(1, "full");
        someDataMap.put(1, "of");
        someDataMap.put(1, "properties.");
    }

    public Map<Integer, String> getSomeDataMap() {
        return someDataMap;
    }

    public void setSomeDataMap(Map<Integer, String> someDataMap) {
        this.someDataMap = someDataMap;
    }

    public List<String> getSomeDataList() {
        return someDataList;
    }

    public void setSomeDataList(List<String> someDataList) {
        this.someDataList = someDataList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
