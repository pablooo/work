package pl.eniro.smx.loader.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CounterProcessor {

    static Logger LOG = LoggerFactory.getLogger(CounterProcessor.class);

    private long counter = 0;

    public void nextMessage() {
        counter++;
        status();
    }

    public void status() {
        LOG.info("counter={}", counter);
    }
}