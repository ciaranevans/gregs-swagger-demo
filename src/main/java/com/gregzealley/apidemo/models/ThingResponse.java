package com.gregzealley.apidemo.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ThingResponse {

    private final List<String> thingItems;

    @JsonCreator
    public ThingResponse(@JsonProperty("thingsItems") final List<String> thingItems) {
        this.thingItems = thingItems;
    }

    public void addItem(final String item) {
        thingItems.add(item);
    }

    public List<String> getThingsItems() {

        return thingItems;
    }
}
