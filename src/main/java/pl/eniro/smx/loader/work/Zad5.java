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
public class Zad5 extends RouteBuilder {

    private final static Log LOGGER = LogFactory.getLog(Zad5.class);

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
                .to("log:Zad5?level=ERROR&showAll=true&multiline=true")
                .stop();

        //5. wypisanie nazw plików w folderze in (i jego podfolderach) w odstępie 20s i bez ingerencji w pliki (nie przenosimy ich)
         from(String.format("file://%s?delay=20000&recursive=true&noop=true", inputPath))
          .log("${header.CamelFileName}");

        // @formatter:on
    }
}
