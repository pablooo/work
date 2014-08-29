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
                .to("log:Zad3?level=ERROR&showAll=true&multiline=true")
                .stop();

        //3. przenoszenie plików *.txt z folderu examples i kopiowanie do:
        //folderu txt i backup_txt (tu ze zmieniona nazwą przedrostek backup -> backup_*.txt)
        //przy kopiowaniu wypisać oryginalną nazwę pliku na konsoli
        from(String.format("file://%s?delete=true&include=.*\\.txt", inputPath))
         .log("starting example 3.")
         .to(String.format("file://%s/txt", outPath))
         .log("${header.CamelFileName}")
         .setHeader("CamelFileName", simple("backup_${header.CamelFileName}"))
         .to(String.format("file://%s/backup_txt", outPath))
         .log("ending example 3");

        // @formatter:on
    }
}
