package pl.eniro.smx.loader.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * Sample processor
 * 
 * @author PABLO
 * 
 */
@Component
public class PrintProcessor implements Processor {

	private final static Log LOGGER = LogFactory.getLog(PrintProcessor.class);

	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		LOGGER.info("body: " + body);
	}
}