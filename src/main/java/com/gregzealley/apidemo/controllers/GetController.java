package com.gregzealley.apidemo.controllers;

import com.gregzealley.apidemo.models.Thing;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class GetController {

    private final List<Thing> things =  Arrays.asList(new Thing("Hello"), new Thing("Goodbye"));

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Thing> getAll() {
        return things;
    }

    @GetMapping(value = "/getOneThing/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Thing getOneThing(@PathVariable final int id) {
        return things.get(id);
    }

}
