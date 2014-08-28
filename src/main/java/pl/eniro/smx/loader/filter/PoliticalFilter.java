package pl.eniro.smx.loader.filter;

import org.apache.camel.Body;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 
 * @author PABLO
 *
 */
@Component
public class PoliticalFilter {

	public boolean isPolitical(@Body String content) {
		if (StringUtils.isNotBlank(content) && content.contains("Donald")) {
			return true;
		}
		return false;
	}
}
