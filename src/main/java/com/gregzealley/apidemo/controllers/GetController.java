package com.gregzealley.apidemo.controllers;

import com.gregzealley.apidemo.models.ThingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class GetController {

    final ThingResponse thingResponse;

    @Autowired
    public GetController(final ThingResponse thingResponse) {
        this.thingResponse = thingResponse;

        this.thingResponse.addItem("Hello");
        this.thingResponse.addItem("Goodbye");
    }

    @GetMapping(value = "/getAllThings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ThingResponse getAllThings() {
        final List<String> things = thingResponse.getThingsItems();

        return new ThingResponse(things);
    }

    @GetMapping(value = "/getOneThing/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getOneThing(@PathVariable final int id) {
        return thingResponse.getThingsItems().get(id);
    }

}
