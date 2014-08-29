package pl.eniro.smx.loader.work;

import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.eniro.smx.loader.processor.PrintProcessor;

/**
 * Preparing and backuping XML file to loading, route definition
 *
 * @author pabloc
 *
 */
@Component
public class Zad2 extends RouteBuilder {

    private final static Log LOGGER = LogFactory.getLog(PrintProcessor.class);

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

        //TODO
        //2. zabezpieczenie przed kopiowaniem nie pełnych plików na FTP uzywajac file:

        // @formatter:on
        }
}
