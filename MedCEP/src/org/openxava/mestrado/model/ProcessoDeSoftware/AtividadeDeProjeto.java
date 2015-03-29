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

package org.openxava.mestrado.model.ProcessoDeSoftware;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.mestrado.calculators.*;
import org.openxava.mestrado.model.MedicaoDeSoftware.PlanejamentoDaMedicao.EntidadeMensuravel.*;


@Entity
@Views({
	@View(members="Atividade Projeto: [nome; tipoDeEntidadeMensuravel; baseadoEm]; Dados Complementares [dependeDe, adotaProcedimento; requer, produz; realizadoPor; elementoMensuravel;]"),
	@View(name="Simple", members="nome")
})
@Tabs({
	@Tab(properties="nome", defaultOrder="${nome} asc")	
})
public class AtividadeDeProjeto extends AtividadeInstanciada {
	 
	@ManyToOne
	private ProcessoDeProjeto processoDeProjeto;

	public ProcessoDeProjeto getProcessoDeProjeto() {
		return processoDeProjeto;
	}

	public void setProcessoDeProjeto(ProcessoDeProjeto processoDeProjeto) {
		this.processoDeProjeto = processoDeProjeto;
	}
	
	@ManyToOne
	@Required
	@Transient
	@DefaultValueCalculator(
		value=TipoDeEntidadeMensuravelCalculator.class,
		properties={
			 @PropertyValue(name="nomeEntidade", value="Atividade de Projeto")
			 }
	)
	private TipoDeEntidadeMensuravel tipoDeEntidadeMensuravel;
	
	public TipoDeEntidadeMensuravel getTipoDeEntidadeMensuravel() {
		return tipoDeEntidadeMensuravel;
	}

	public void setTipoDeEntidadeMensuravel(
			TipoDeEntidadeMensuravel tipoDeEntidadeMensuravel) {
		this.tipoDeEntidadeMensuravel = tipoDeEntidadeMensuravel;
	}
	
}
