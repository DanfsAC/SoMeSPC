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

import org.openxava.actions.*;

public class GoAddProcessoDeProjetoAction extends GoAddElementsToCollectionAction
{
    public void execute() throws Exception
    {
	super.execute();

	Integer id = getPreviousView().getValueInt("id");
	Integer idProcessoPadrao = getPreviousView().getValueInt("baseadoEm.id");
	
	getCollectionElementView().setTitle("Adicionar Atividade de Projeto");

	getTab().setBaseCondition(idProcessoPadrao + " IN (SELECT p.id from ProcessoProjeto p "
		+ "JOIN p.atividadeProjeto a WHERE a.id = ${baseadoEm.id})"
		+ " AND ${id} NOT IN "
		+ "(SELECT aa.id from ProcessoPadrao pp JOIN pp.atividadePadrao aa WHERE pp.id = " + id + ")");
    }

}