package pl.eniro.smx.loader.splitter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultMessage;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import pl.eniro.smx.loader.processor.PrintProcessor;

@Component
public class MySplitter {

	private final static Log LOGGER = LogFactory.getLog(PrintProcessor.class);

	public List<Message> splitMessage(@Body String body) {
        List<Message> answer = new ArrayList<Message>();
        
        if (StringUtils.isBlank(body)){
        	LOGGER.error("Message is with out body!!!");
        	return null;
        }
        
        String[] parts = body.split(",");
        for (String part : parts) {
            DefaultMessage message = new DefaultMessage();
            message.setHeader("TimeStamp", new Date());
            try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            message.setBody(part);
            answer.add(message);
        }
        return answer;
    }
}