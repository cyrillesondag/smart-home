package fr.enova.smart.home.brms;

import org.kie.api.event.rule.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Listener de l'agenda drools. A utiliser pour tracer les events MatchCreatedEvent 
 * BeforeMatchFiredEvent, AfterMatchFiredEvent et MatchCancelledEvent.
 * 
 */
public class DebugDroolsAgendaEventListener extends DefaultAgendaEventListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(DebugDroolsAgendaEventListener.class);
	
	private static final String MATCH = "MATCH";
	private static final String BEFORE_FIRED = "BEFORE FIRED";
	private static final String AFTER_FIRED = "AFTER FIRED";
	private static final String CANCELLED = "CANCEL";
	private static final int FACT_LOG_LIMIT = 10;
	private final String sessionName;
	
	public DebugDroolsAgendaEventListener(String sessionName) {
		this.sessionName = sessionName;
	}
	
	/**
	 * Trace une règle et les faits lorsqu'elle est activée. Les faits traces sont ceux qui
	 * satisfont le LHS
	 */
	public void matchCreated(MatchCreatedEvent ev) {
		logRule(MATCH, ev);
	}

	/**
	 * Trace une règle et les faits avant l'éxecution du RHS
	 */
	public void beforeMatchFired(BeforeMatchFiredEvent ev) {
		logRule(BEFORE_FIRED, ev);
	}

	/**
	 * Trace une règle et les faits apres l'éxecution du RHS
	 */
	public void afterMatchFired(AfterMatchFiredEvent ev) {
		logRule(AFTER_FIRED, ev);
	}

	/**
	 * Trace une règle annulée. Ceci survient lorsque la règle est incoherente ou qu'une action 
	 * sur la working memory a causé son annulation.
	 */
	public void matchCancelled(MatchCancelledEvent ev) {
		logRule(CANCELLED, ev);
	}
	
	private void logRule(final String agendaEvent, final MatchEvent ev) {
		if (LOG.isDebugEnabled()) {
			StringBuilder buf = new StringBuilder(512)
					.append(sessionName).append(" - ")
					.append(agendaEvent)
					.append(" rule ")
					.append(ev.getMatch().getRule().getName())
					.append(" for facts: ");
			int i = 0;
			for (Object o : ev.getMatch().getObjects()) {
				if (i++ >= FACT_LOG_LIMIT) {
					buf.append(" ... ");
					break;
				}
				if (o instanceof Collection) {
					buf.append("[");
					for (Object collObj : ((Collection) o)) {
						if (i++ == FACT_LOG_LIMIT) {
							buf.append(" ... ");
							break;
						}
						buf.append(collObj).append(" ");
					}
					buf.append("]");
				} else {
					buf.append(o);
				}
				buf.append(" ");
			}
			LOG.debug(buf.toString());
		}
	}
	
}
