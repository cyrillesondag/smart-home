package fr.enova.smart.home.brms;

import org.drools.core.common.EventFactHandle;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilisé pour tracer l'insertion, la suppression ou la mise à jour d'un fait en working memory.
 *
 */
public class DebugDroolsRuleRuntimeEventListener implements RuleRuntimeEventListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(DebugDroolsRuleRuntimeEventListener.class);
	
	private static final String INSERTED 		= "INSERT";
	private static final String DELETED 		= "DELETE";
	private static final String DELETED_BY_RULE = "DELETE BY RULE";
	private static final String EXPIRATION  	= "EXPIRATION";
	private static final String UPDATED 		= "UPDATE";
	private final String sessionName;
	
	public DebugDroolsRuleRuntimeEventListener(String sessionName) {
		this.sessionName = sessionName;
	}

	public void objectInserted(ObjectInsertedEvent event) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(buildMessage(INSERTED, event.getObject()));
		}
	}

	public void objectUpdated(ObjectUpdatedEvent event) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(buildMessage(UPDATED, event.getObject()));
		}
	}

	public void objectDeleted(ObjectDeletedEvent event) {
		if (LOG.isDebugEnabled()) {
			if (event.getRule() != null) {
				LOG.debug(buildMessage(DELETED_BY_RULE, event.getOldObject()) + " {}", event.getRule().getName());
			} else if (event.getFactHandle() instanceof EventFactHandle && ((EventFactHandle) event.getFactHandle()).isExpired()) {
				LOG.debug(buildMessage(EXPIRATION, event.getOldObject()));
			} else {
				LOG.debug(buildMessage(DELETED, event.getOldObject()));
			}
		}
	}


	
	private String buildMessage(String action, Object object) {
		StringBuffer buffer = new StringBuffer(512)
			.append(sessionName).append(" - ")
			.append(action).append(" ")
			.append(object.toString());
		return buffer.toString();
	}
}
