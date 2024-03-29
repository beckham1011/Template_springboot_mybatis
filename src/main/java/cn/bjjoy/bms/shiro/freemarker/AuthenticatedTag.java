package cn.bjjoy.bms.shiro.freemarker;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;


/**
 * JSP tag that renders the tag body only if the current user has executed a <b>successful</b> authentication attempt
 * <em>during their current session</em>.
 *
 * <p>This is more restrictive than the {@link UserTag}, which only
 * ensures the current user is known to the system, either via a current login or from Remember Me services,
 * which only makes the assumption that the current user is who they say they are, and does not guarantee it like
 * this tag does.
 *
 * <p>The logically opposite tag of this one is the {@link NotAuthenticatedTag}
 *
 * <p>Equivalent to {@link org.apache.shiro.web.tags.AuthenticatedTag}</p>
 *
 * @since 0.2
 */

@SuppressWarnings("rawtypes")
public class AuthenticatedTag extends SecureTag {

	private static final Logger log = LogManager.getLogger();
	
    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        if (getSubject() != null && getSubject().isAuthenticated()) {
            if (log.isDebugEnabled()) {
                log.debug("Subject exists and is authenticated.  Tag body will be evaluated.");
            }

            renderBody(env, body);
        } else {
            if (log.isDebugEnabled()) {
                log.debug("Subject does not exist or is not authenticated.  Tag body will not be evaluated.");
            }
        }
    }
}
