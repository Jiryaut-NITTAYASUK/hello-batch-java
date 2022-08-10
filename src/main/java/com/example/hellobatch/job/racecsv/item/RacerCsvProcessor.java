package com.example.hellobatch.job.racecsv.item;

import com.example.hellobatch.model.Race;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class RacerCsvProcessor implements ItemProcessor<Race, Race> {

    @Override
    public Race process(Race race) throws Exception {
        System.out.println("Add Data to Database");
        return race;
    }
}
