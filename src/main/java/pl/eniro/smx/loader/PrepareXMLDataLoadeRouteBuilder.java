package pl.eniro.smx.loader;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.config.BatchResequencerConfig;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import pl.eniro.smx.loader.filter.PoliticalFilter;
import pl.eniro.smx.loader.processor.PrintProcessor;

/**
 * Preparing and backuping XML file to loading, route definition
 *
 * @author pabloc
 *
 */
public class PrepareXMLDataLoadeRouteBuilder extends RouteBuilder {

    private final static Log LOGGER = LogFactory.getLog(PrintProcessor.class);

    @Value("${inputPath}")
    private String inputPath;

    @Value("${outPath}")
    private String outPath;

    @Value("${txtUploadPath}")
    private String txtUploadPath;

    @Value("${jpgUploadPath}")
    private String jpgUploadPath;

    @Value("${sendMailTo}")
    private String email;

    @Value("${cron}")
    private String cronExpression;

    //InteliJ
    //Code style -> General-> Enable formatter markers in comments
    // @formatter:off

    // Eclipse preferences: Java > Code Style > Formatter.
    // Click on "Edit" button, "Off/On Tags", check off
    // "Enable Off/On tags".
	@Override
	public void configure() throws Exception {
	
	// @formatter:off
        onException(IllegalStateException.class, RuntimeException.class, Exception.class)
                .handled(true)
                .to("log:PrepareXMLDataLoadeRouteBuilder?level=ERROR&showAll=true&multiline=true")
                .stop();

 
        // @formatter:on
        }
    }
