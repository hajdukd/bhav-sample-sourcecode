package com.hajdukda.bhavsamplesourcecode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Optional.ofNullable;


@RestController
public class TestController {

    private static final Map<Key, String> LUCKY_FOR_TODAY = new ConcurrentHashMap<>();

    @GetMapping("/current-date")
    public String currentDate() {
        return new Date().toString();
    }

    @GetMapping("/my-lucky-number/{name}")
    public String luckyNumber(@PathVariable("name") final String name) {
        final String date = Key.formatDate(LocalDate.now());
        final Key key = new Key(name, date);
        return ofNullable(LUCKY_FOR_TODAY.get(key)).orElseGet(()-> {
            final Integer randomInt = new Random().ints(0, 100)
                .findFirst()
                .getAsInt();
            LUCKY_FOR_TODAY.put(key, randomInt.toString());
            return randomInt.toString();
        });
    }
}
