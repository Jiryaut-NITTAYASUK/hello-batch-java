package com.example.hellobatch.job.racesend.item;

import com.example.hellobatch.model.Race;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class RacerSendProcessor implements ItemProcessor<Race, Race> {

    @Override
    public Race process(Race race) throws Exception {
//        System.out.println("Get Data");
        return race;
    }
}
