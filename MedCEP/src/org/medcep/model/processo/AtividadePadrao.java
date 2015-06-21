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

package org.medcep.model.processo;

import java.util.*;

import javax.persistence.*;

import org.medcep.calculators.*;
import org.medcep.model.medicao.*;
import org.medcep.validators.*;
import org.openxava.annotations.*;
import org.openxava.jpa.XPersistence;

/**
 * Atividade Analisar Dados de Monitoramento do Projeto; Atividade Homologar Especifica��o de Requisitos
 */
@Entity
@Views({
	@View(members = "nome; descricao; requerTipoDeArtefato; produzTipoDeArtefato; dependeDe;"),
	@View(name = "Simple", members = "nome")
})
@Tab(properties = "nome", defaultOrder = "${nome} asc")
@EntityValidator(
	value = AtividadePadraoValidator.class,
	properties = {
		@PropertyValue(name = "tipoDeEntidadeMensuravel")
	})
public class AtividadePadrao extends EntidadeMensuravel
{

    @OneToMany(mappedBy = "momentoDeMedicao")
    private Collection<DefinicaoOperacionalDeMedida> momentoDeMedicao;

    @OneToMany(mappedBy = "momentoDeAnaliseDeMedicao")
    private Collection<DefinicaoOperacionalDeMedida> momentoDeAnaliseDeMedicao;

    @OneToMany(mappedBy = "baseadoEm")
    private Collection<AtividadeProjeto> atividadeProjeto;

    @ManyToOne
    @Transient
    @DefaultValueCalculator(
	    value = TipoDeEntidadeMensuravelCalculator.class,
	    properties = {
		    @PropertyValue(name = "nomeEntidade", value = "Atividade Padr�o")
	    })
    public TipoDeEntidadeMensuravel getTipoDeEntidadeMensuravel()
    {
	return tipoDeEntidadeMensuravel;
    }

    public void setTipoDeEntidadeMensuravel(
	    TipoDeEntidadeMensuravel tipoDeEntidadeMensuravel)
    {
	this.tipoDeEntidadeMensuravel = tipoDeEntidadeMensuravel;
    }

    @JoinTable(name = "AtividadePadrao_dependeDe_AtividadePadrao",
	    joinColumns = {
		    @JoinColumn(name = "atividade1", referencedColumnName = "id", nullable = false)
	    },
	    inverseJoinColumns = {
		    @JoinColumn(name = "atividade2", referencedColumnName = "id", nullable = false)
	    })
    @ManyToMany(fetch = FetchType.LAZY)
    @ListProperties("nome")
    private Collection<AtividadePadrao> dependeDe;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
	    name = "AtividadePadrao_produz_TipoArtefato"
	    , joinColumns = {
		    @JoinColumn(name = "atividadePadrao_id")
	    }
	    , inverseJoinColumns = {
		    @JoinColumn(name = "tipoArtefato_id")
	    })
    @ListProperties("nome")
    private Collection<TipoDeArtefato> produzTipoDeArtefato;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
	    name = "AtividadePadrao_requer_TipoArtefato"
	    , joinColumns = {
		    @JoinColumn(name = "atividadePadrao_id")
	    }
	    , inverseJoinColumns = {
		    @JoinColumn(name = "tipoArtefato_id")
	    })
    @ListProperties("nome")
    private Collection<TipoDeArtefato> requerTipoDeArtefato;

    public Collection<AtividadeProjeto> getAtividadeProjeto()
    {
	return atividadeProjeto;
    }

    public void setAtividadeProjeto(
	    Collection<AtividadeProjeto> atividadeProjeto)
    {
	this.atividadeProjeto = atividadeProjeto;
    }

    public Collection<TipoDeArtefato> getProduzTipoDeArtefato()
    {
	return produzTipoDeArtefato;
    }

    public void setProduzTipoDeArtefato(
	    Collection<TipoDeArtefato> produzTipoDeArtefato)
    {
	this.produzTipoDeArtefato = produzTipoDeArtefato;
    }

    public Collection<TipoDeArtefato> getRequerTipoDeArtefato()
    {
	return requerTipoDeArtefato;
    }

    public void setRequerTipoDeArtefato(
	    Collection<TipoDeArtefato> requerTipoDeArtefato)
    {
	this.requerTipoDeArtefato = requerTipoDeArtefato;
    }

    public Collection<DefinicaoOperacionalDeMedida> getMomentoDeMedicao()
    {
	return momentoDeMedicao;
    }

    public void setMomentoDeMedicao(
	    Collection<DefinicaoOperacionalDeMedida> momentoDeMedicao)
    {
	this.momentoDeMedicao = momentoDeMedicao;
    }

    public Collection<DefinicaoOperacionalDeMedida> getMomentoDeAnaliseDeMedicao()
    {
	return momentoDeAnaliseDeMedicao;
    }

    public void setMomentoDeAnaliseDeMedicao(
	    Collection<DefinicaoOperacionalDeMedida> momentoDeAnaliseDeMedicao)
    {
	this.momentoDeAnaliseDeMedicao = momentoDeAnaliseDeMedicao;
    }

    @PreCreate
    @PreUpdate
    public void ajustaElementosMensuraveis()
    {
	if(tipoDeEntidadeMensuravel != null){
    	
    	String nomeEntidade = "Atividade Padr�o";
    	Query query = XPersistence.getManager().createQuery("from TipoDeEntidadeMensuravel t where t.nome = '" + nomeEntidade + "'");
    	TipoDeEntidadeMensuravel tipoDeEntidadeMensuravel = (TipoDeEntidadeMensuravel) query.getSingleResult();
    	
    	this.setTipoDeEntidadeMensuravel(tipoDeEntidadeMensuravel);
    }
	if (elementoMensuravel == null)
	    elementoMensuravel = new ArrayList<ElementoMensuravel>();

	if (tipoDeEntidadeMensuravel != null && tipoDeEntidadeMensuravel.getElementoMensuravel() != null)
	{
	    boolean add;
	    for (ElementoMensuravel elemTipo : tipoDeEntidadeMensuravel.getElementoMensuravel())
	    {
		add = true;
		for (ElementoMensuravel elem : elementoMensuravel)
		{
		    if (elem.getNome().compareTo(elemTipo.getNome()) == 0)
		    {
			add = false;
			break;
		    }
		}
		if (add)
		    elementoMensuravel.add(elemTipo);
	    }//elemTipo
	}
    }//ajusta

    public Collection<AtividadePadrao> getDependeDe()
    {
	return dependeDe;
    }

    public void setDependeDe(Collection<AtividadePadrao> dependeDe)
    {
	this.dependeDe = dependeDe;
    }

}
