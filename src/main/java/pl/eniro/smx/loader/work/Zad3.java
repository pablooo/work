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
public class Zad3 extends RouteBuilder {

    private final static Log LOGGER = LogFactory.getLog(Zad3.class);

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
                .to("log:Zad1?level=ERROR&showAll=true&multiline=true")
                .stop();

        //3. przenoszenie plików *.txt z folderu examples i kopiowanie do:
        //folderu txt i backup_txt (tu ze zmieniona nazwą przedrostek backup -> backup_*.txt)
        //przy kopiowaniu wypisać oryginalną nazwę pliku na konsoli

        // @formatter:on
    }
}
