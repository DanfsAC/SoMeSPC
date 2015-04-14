/*
 * MedCEP - A powerful tool for measure
 * 
 * Copyright (C) 2013 Ciro Xavier Maretto
 * Copyright (C) 2015 Henrique N�spoli Castro, Vin�cius Soares Fonseca
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/lgpl.html>.
 */
package org.medcep.actions;

import org.medcep.model.medicao.planejamento.*;
import org.openxava.actions.*;
import org.openxava.jpa.*;

// import static org.openxava.jpa.XPersistence.*;

public class SearchDefinicaoOperacionalMedidaForMedidaPlanoMedicaoAction extends ReferenceSearchAction
{

    // adding collection elements list
    public void execute() throws Exception
    {

	super.execute();

	Integer idMedidaPlanoDeMedicao = getPreviousView().getValueInt("medida.id");

	if (idMedidaPlanoDeMedicao != null && idMedidaPlanoDeMedicao != 0)
	{
	    MedidaPlanoDeMedicao medidaPlanoDeMedicao = XPersistence.getManager().find(MedidaPlanoDeMedicao.class, idMedidaPlanoDeMedicao);

	    Integer idMedida = medidaPlanoDeMedicao.getMedida().getId();

	    if (idMedida != null && idMedida == 0)
	    {
		getTab().setBaseCondition(idMedida + " IN (SELECT id FROM ${medida}) ");
	    }
	    return;
	}

	throw new Exception("Para selecionar a Defini��o Operacional selecione primeiramente o Plano de Medi��o e a Medida do plano. Retorne a tela anterior de cadastro.");

    }

}