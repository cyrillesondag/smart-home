package fr.enova.smart.home.brms;

import org.drools.core.common.InternalFactHandle;
import org.kie.api.runtime.rule.ConsequenceExceptionHandler;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.Match;
import org.kie.api.runtime.rule.RuleRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RhsConsequenceExceptionHandler implements ConsequenceExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(RhsConsequenceExceptionHandler.class);

	@Override
	public void handleException(Match match, RuleRuntime workingMemory, Exception exception) {
		StringBuilder sb = new StringBuilder(512)
				.append(exception.getClass().getName())
				.append(" dans le RHS de la regle '")
				.append(match.getRule().getName())
				.append("' du package ")
				.append(match.getRule().getPackageName());
		if (match.getObjects() != null && !match.getObjects().isEmpty()) {
			sb.append(" pour les faits: ");
			for (Object o : match.getObjects()) {
				sb.append(o).append(" ");
			}
		}
		LOG.error(sb.toString(), exception);

		if (match.getFactHandles() != null && !match.getFactHandles().isEmpty()) {
			for (FactHandle factHandle : match.getFactHandles()) {
				if (factHandle instanceof InternalFactHandle && ((InternalFactHandle) factHandle).getEntryPoint() != null) {
					// On ne supprime que les FactHandle inseres en WM, pas les FactHandle generes pas Drools
					// (comme par exemple les FactHandle resultant des from et from collect)
					Object fact = ((InternalFactHandle) factHandle).getObject();
					try {
						workingMemory.delete(factHandle);
					} catch (RuntimeException e) {
						LOG.error("Une erreur est survenue durant la suppression du fait {} ({})", fact, factHandle.toExternalForm(), e);
					}
				}
			}
		}
	}

}
