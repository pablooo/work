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
public class Zad4 extends RouteBuilder {

    private final static Log LOGGER = LogFactory.getLog(Zad4.class);

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

        //4. wczytwanie plików *.zip z folderu examples większych niż 100B i przenoszenie do folderu big_zip, inne zaś do small_zip
        from(String.format("file://%s?delete=true&include=.*\\.zip", inputPath))
		.log("starting example 4.")
		  .choice()
            .when(simple("${file:size} > 1000"))
                .to(String.format("file://%s/big_zip", outPath))
                .otherwise().to(String.format("file://%s/small_zip", outPath))
            .endChoice()
		.log("ending example 4");

        // @formatter:on
    }
}
