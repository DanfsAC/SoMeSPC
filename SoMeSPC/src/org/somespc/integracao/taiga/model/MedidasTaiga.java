/*
 * SoMeSPC - powerful tool for measurement
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
package org.somespc.integracao.taiga.model;

import java.util.HashMap;
import java.util.Map;

public enum MedidasTaiga
{
    //OM - Monitorar a conclus�o de pontos de est�ria nos projetos - DESEMPENHO
			
		    PONTOS_ESTORIA_PLANEJADOS_PROJETO("Pontos de Est�ria Planejados para o Projeto"),
		    PONTOS_ESTORIA_CONCLUIDOS_PROJETO("Pontos de Est�ria Conclu�dos no Projeto"),
		    TAXA_CONCLUSAO_PONTOS_ESTORIA_PROJETO("Taxa de Conclus�o de Pontos de Est�ria no Projeto"),
		    
	//OM - Monitorar a realiza��o de sprints nos projetos TAMANHO
		    
		   	NUMERO_SPRINTS_PLANEJADAS_PROJETO("N�mero de Sprints Planejadas para o Projeto"),
		   	NUMERO_SPRINTS_REALIZADAS_PROJETO("N�mero de Sprints Realizadas no Projeto"),
		   	TAXA_CONCLUSAO_SPRINTS_PROJETO("Taxa de Conclus�o de Sprints no Projeto"),
		   	
    //OM - Monitorar desempenho na sprint - DESEMPENHO
    		
    		NUMERO_ESTORIAS_PLANEJADAS_SPRINT("N�mero de Est�rias Planejadas para a Sprint"),
    		NUMERO_ESTORIAS_CONCLUIDAS_SPRINT("N�mero de Est�rias Conclu�das na Sprint"),
    		TAXA_CONCLUSAO_ESTORIAS_SPRINT("Taxa de Conclus�o de Est�rias na Sprint"),
    		PONTOS_ESTORIAS_PLANEJADOS_SPRINT("Pontos de Est�ria Planejados para a Sprint"),
    		PONTOS_ESTORIAS_CONCLUIDAS_SPRINT("Pontos de Est�ria Conclu�dos na Sprint"),
    		TAXA_CONCLUSAO_PONTOS_ESTORIAS_SPRINT("Taxa de Conclus�o de Pontos de Est�rias na Sprint"),
    		NUMERO_TAREFAS_PLANEJADAS_SPRINT("N�mero de Tarefas Planejadas para a Sprint"),
    		NUMERO_TAREFAS_CONCLUIDAS_SPRINT("N�mero de Tarefas Conclu�das na Sprint"),
    		TAXA_CONCLUSAO_TAREFAS_SPRINT("Taxa de Conclus�o de Tarefas na Sprint"),
 
    //OM - Monitorar desempenho no projeto - DESEMPENHO
   	     	
    		NUMERO_ESTORIAS_CONCLUIDAS_PROJETO("N�mero de Est�rias Conclu�das para o Projeto"),
    		MEDIA_ESTORIAS_CONCLUIDAS_SPRINT_PROJETO("M�dia de Est�rias Conclu�das por Sprint do Projeto"),
  
    //OM - Monitorar o desempenho dos membros da equipe no projeto
    
    		NUMERO_TAREFAS_ATRIBUIDAS_MEMBRO("N�mero de Tarefas Atribu�das a Membro do Projeto"),
    	    NUMERO_TAREFAS_CONCLUIDAS_MEMBRO("N�mero de Tarefas Conclu�das pelo Membro do Projeto"),
    	    TAXA_CONCLUSAO_TAREFAS_MEMBRO("Taxa de Conclus�o de Tarefas de Membro do Projeto"),
    	    NUMERO_PONTOS_ESTORIA_ATRIBUIDOS_MEMBRO("N�mero de Pontos de Est�ria Atribu�dos a Membro do Projeto"),
    	    NUMERO_PONTOS_ESTORIA_CONCLUIDOS_MEMBRO("N�mero de Pontos de Est�ria Conclu�dos pelo Membro do Projeto"),
    	    TAXA_CONCLUSAO_PONTOS_ESTORIA_MEMBRO("Taxa de Conclus�o de Pontos de Est�ria de Membro do Projeto"),
    	    NUMERO_DOSES_IOCAINE_ATRIBUIDAS_MEMBRO("N�mero de Doses de Iocaine Atribu�das a Membro do Projeto"),
    	    TAXA_DOSES_IOCAINE_MEMBRO("Taxa de Doses de Iocaine de Membro do Projeto"),
	
	//OM - Monitorar quantidade de doses de locaine nas sprints -TAMANHO

			NUMERO_IOCAINE_SPRINT("N�mero de Doses de Iocaine na Sprint"),		
			TAXA_IOCAINE_SPRINT("Taxa de Doses de Iocaine na Sprint"),

	//OM - Monitorar velocidade da equipe no projeto - DESEMPENHO
	
			VELOCIDADE_EQUIPE_PROJETO("Velocidade da Equipe no Projeto");

	private final String name;
	private final String descricao;
	private static final Map<String, MedidasTaiga> lookup = new HashMap<String, MedidasTaiga>();

	static {
		for (MedidasTaiga d : MedidasTaiga.values()){
			lookup.put(d.getDescricao(), d);
		}			
	}
	
	private MedidasTaiga(String s) {
		name = s;
		this.descricao = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	public String toString() {
		return name;
	}

	public String getDescricao() {
		return descricao;
	}

	public static MedidasTaiga get(String descricao) {
		return lookup.get(descricao);
	}

};