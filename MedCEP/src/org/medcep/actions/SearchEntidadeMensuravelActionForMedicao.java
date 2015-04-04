/*
    MedCEP - A powerful tool for measure
    
    Copyright (C) 2013 Ciro Xavier Maretto
    Copyright (C) 2015 Henrique N�spoli Castro, Vin�cius Soares Fonseca                          

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/lgpl.html>.    
 */
package org.medcep.actions;

import org.openxava.actions.*;

//import static org.openxava.jpa.XPersistence.*;

public class SearchEntidadeMensuravelActionForMedicao extends ReferenceSearchAction { 
	
	// adding collection elements list
	public void execute() throws Exception {
		
		super.execute(); 
		
		//TODO: Verificar a situa��o abaixo, agora que a medida possui mais de um tipo de entidade mensur�vel.
		/*
		String idMedidaPlanoDeMedicao = getPreviousView().getValueString("medidaPlanoDeMedicao.id");
		
		if(idMedidaPlanoDeMedicao != null && idMedidaPlanoDeMedicao.isEmpty() == false)
		{
			MedidaPlanoDeMedicao medidaPlanoDeMedicao = XPersistence.getManager().find(MedidaPlanoDeMedicao.class, idMedidaPlanoDeMedicao);
						
			String idTipoEntidade = medidaPlanoDeMedicao.getMedida().getTipoDeEntidadeMensuravel().getId();
			
			if(idTipoEntidade != null && idTipoEntidade.isEmpty() == false)
			{
				getTab().setBaseCondition( 
					"'" + idTipoEntidade + "' = ${tipoDeEntidadeMensuravel} "
					//+ "'" + idMedida + "' NOT IN (SELECT medida.id FROM ${medidaPlanoDeMedicao})" 
				);
			}
			return;
		}
		
		throw new Exception("A medi��o deve ser feita para Entidades que sejam do mesmo Tipo de Entidade Mensur�vel da Medida.");
		*/
	}//execute
	
}