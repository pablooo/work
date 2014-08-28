package pl.eniro.smx.loader.processor;

import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Sample processor
 * @author PABLO
 *
 */
@Component
public class CensureProcessor {

    @Handler
	public String censure(@Body String content) {
		if (StringUtils.isNotBlank(content)) {
			content = new String(content).replaceAll("Donald", "XXX");
		}
		return content;
	}
}
