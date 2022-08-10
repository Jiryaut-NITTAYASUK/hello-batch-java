package com.example.hellobatch.job.racesend.item;

import com.example.hellobatch.model.Race;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RacerSendWriter implements ItemWriter<Race> {
    @Override
    public void write(List<? extends Race> list) throws Exception {
        for(Race race :list)
            System.out.println(race.getPilot());
    }
}
