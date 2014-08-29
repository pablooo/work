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

    private final static Log LOGGER = LogFactory.getLog(Zad2.class);

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
                .to("log:Zad2?level=ERROR&showAll=true&multiline=true")
                .stop();

        //2. zabezpieczenie przed kopiowaniem nie pełnych plików na FTP uzywajac file:
        from(String.format("file://%s?readLock=changed&readLockCheckInterval=2000&readLockTimeout=2000", inputPath))
	     .log("starting example 2.")
	     .to(String.format("file://%s", outPath))
	     .log("ending example 2");

        // @formatter:on
    }
}
