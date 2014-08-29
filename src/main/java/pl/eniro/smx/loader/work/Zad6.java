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
public class Zad6 extends RouteBuilder {

    private final static Log LOGGER = LogFactory.getLog(Zad6.class);

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
                .to("log:Zad6?level=ERROR&showAll=true&multiline=true")
                .stop();

        //6. Przeszukanie plików .txt w folderze in i skopiowanie do katalogu "Zorro" jeśli w pliku jest słowo "Zorro"
        //wpp. skopiować go do folderu "Other" (wskazówka1: choice)

        // @formatter:on
    }
}
