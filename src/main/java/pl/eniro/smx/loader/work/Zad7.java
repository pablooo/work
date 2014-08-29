package pl.eniro.smx.loader.work;

import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: pcieslinski
 */
@Component
public class Zad7 extends RouteBuilder {

    private final static Log LOGGER = LogFactory.getLog(Zad7.class);

    @Value("${inputPath}")
    private String inputPath;

    @Value("${outPath}")
    private String outPath;

    @Value("${txtUploadPath}")
    private String txtUploadPath;

    @Value("${jpgUploadPath}")
    private String jpgUploadPath;

    @Value("${cron}")
    private String cronExpression;

    @Override
    public void configure() throws Exception {
        // @formatter:off
        onException(IllegalStateException.class, RuntimeException.class, Exception.class)
                .handled(true)
                .to("log:Zad7?level=ERROR&showAll=true&multiline=true")
                .stop();

        //7. Dodaj do kolejki "pkp" co 1s jakiś komunikat i utwórz monitoring ilości elementów w kolejce (nazwa z parametru) co 5sek
        //7' Rozmnoz komunikaty x2 :D


        // @formatter:on
    }
}
