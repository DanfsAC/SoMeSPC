package org.somespc.actions;

import org.openxava.actions.GoAddElementsToCollectionAction;

public class GoAddProcessoDeProjetoActionSimple extends GoAddElementsToCollectionAction{
	
	public void execute() throws Exception {
		super.execute();
		getCollectionElementView().setTitle("Adicionar Subprocesso a cole��o");
		getTab().setBaseCondition(
			"${id} IN (SELECT id FROM ProcessoProjeto)"
		);
	}

}