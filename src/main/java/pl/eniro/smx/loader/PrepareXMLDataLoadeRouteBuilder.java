package pl.eniro.smx.loader;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.config.BatchResequencerConfig;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    @Value("${cron}")
    private String cronExpression;

    @Value("${inputPath}")
    private String inputPath;

    @Value("${outPath}")
    private String outPath;

    @Value("${txtUploadPath}")
    private String txtUploadPath;

    @Value("${jpgUploadPath}")
    private String jpgUploadPath;

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


        //Processor
        from(String.format("file://%s?delete=true&include=procesor.*\\.txt", inputPath))
         .to("printProcessor")
         .end();

        //Filter
        from(String.format("file://%s?delete=true&include=cenzura.*\\.txt", inputPath))
         .filter()
         .method(PoliticalFilter.class, "isPolitical")
         //.python(....)
         .to(String.format("file://%scenzura",txtUploadPath))
        .end();

        //Censure
        from(String.format("file://%s?delete=true&include=cenzura.*\\.txt", txtUploadPath+ "cenzura"))
         .beanRef("censureProcessor")
         .to(String.format("file://%socenzurowane", txtUploadPath))
        .end();

        /*
    	//Splitter
    	from("quartz://eniro/myTimerSplitter?cron="+cronExpression)
        //from("timer://timer1?period=10s")
    	 .setBody(simple("hello1,hello2,hello3"))
    	 .to("direct:body")
        .end();

    	from("direct:body")
         .split()
         .method("mySplitter", "splitMessage")
         .to("direct:resultMessage")
         .to("printProcessor")
        .end();

    	//Resequencer
        from("direct:resultMessage")
         .loop(2)
         .resequence(header("TimeStamp"))
         .batch(new BatchResequencerConfig(10,10000L)).allowDuplicates()
         .reverse()
         .to("printProcessor")
        .end();
        */

        //Recipient list
        /*
    	from("quartz://eniro/myTimer?cron="+cronExpression)
         .setHeader("recipients", simple("direct:a,direct:b,direct:c")).setBody(simple("test"))
    	.to("direct:start");

        from("direct:a").delay(5000).to("mock:A");
    	from("direct:b").to("mock:B").setBody(constant("B"));
    	from("direct:c").to("mock:C").setBody(constant("C"));

        from("direct:start")
        .recipientList(header("recipients"), ",")
        .aggregationStrategy(new AggregationStrategy() {
                public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
                    if (oldExchange == null) {
                        return newExchange;
                    }

                    String body = oldExchange.getIn().getBody(String.class);
                    oldExchange.getIn().setBody(body + newExchange.getIn().getBody(String.class));
                    return oldExchange;
                }
            })
            .parallelProcessing().timeout(25000)
        // use end to indicate end of recipientList clause
        .end().to("printProcessor");
        */


        //Wire tap
        /*
        from("quartz://eniro/myTimer2?cron="+cronExpression).setBody(simple("wire tap"))
         // tap a new message and send it to direct:tap
         // the new message should be Bye World with 2 headers
           .wireTap("direct:tap")
            // create the new tap message body and headers
            .newExchangeBody(constant("Bye World"))
            .newExchangeHeader("id", constant(123))
            .newExchangeHeader("date", simple("${date:now:yyyyMMdd}"))
           .end()
        // here we continue routing the original messages
        .to("mock:result").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                String payload = exchange.getIn().getBody(String.class);
                LOGGER.info("original: " +  exchange.getIn().getBody());
           }}).end();

        // this is the tapped route
        from("direct:tap")
            .to("mock:tap").to("printProcessor");
        */

        // @formatter:on
        }
    }
