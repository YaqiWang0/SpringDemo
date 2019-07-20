package com.spring.annotation;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class RandomFortuneService implements FortuneService {

    //create an array of strings
    private List<String> list= Arrays.asList(
            "Beware of the wolf in sheep's clothing",
            "Diligence is the mother of good luck",
            "The journey is the reward");

    private Random random=new Random();






    @Override
    public String getFortune() {

        //pick a random fortune
        int index=random.nextInt(list.size());
        String fortune=list.get(index);
        return fortune;

    }
}
