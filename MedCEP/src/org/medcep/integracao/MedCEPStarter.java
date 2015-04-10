package org.medcep.integracao;

import java.util.*;

import javax.persistence.*;

import org.hibernate.exception.*;
import org.medcep.model.medicao.*;
import org.openxava.jpa.*;

/**
 * Classe para inicializar a MedCEP.
 * @author Vinicius
 *
 */
public class MedCEPStarter
{

    /**
     * Inicializa o banco de dados da MedCEP com informa��es b�sicas.
     * 
     * @throws Exception
     */
    public static void inicializarMedCEP() throws Exception
    {
	inicializarTiposElementosMensuraveis();
	inicializarTiposEntidadesMensuraveis();
	inicializarPeriodicidades();
    }

    private static void inicializarTiposElementosMensuraveis() throws Exception
    {
	//Configura os tipos de elementos mensur�veis.	
	EntityManager manager = XPersistence.createManager();

	TipoElementoMensuravel dm = new TipoElementoMensuravel();
	TipoElementoMensuravel im = new TipoElementoMensuravel();

	dm.setNome("Elemento Diretamente Mensur�vel");
	im.setNome("Elemento Indiretamente Mensur�vel");

	//Persiste.
	try
	{
	    manager.getTransaction().begin();

	    manager.persist(dm);
	    manager.persist(im);
	    manager.getTransaction().commit();
	}
	catch (Exception ex)
	{
	    if (ex.getCause() != null &&
		    ex.getCause().getCause() != null &&
		    ex.getCause().getCause() instanceof ConstraintViolationException)
	    {
		System.out.println("Tipos de Elementos Mensur�veis j� cadastrados.");
	    }
	    else
	    {
		throw ex;
	    }
	}
	finally
	{
	    manager.close();
	}
    }

    private static void inicializarTiposEntidadesMensuraveis() throws Exception
    {
	//Configura os tipos de entidades mensur�veis b�sicas.	
	EntityManager manager = XPersistence.createManager();

	TipoDeEntidadeMensuravel tipoProjeto = new TipoDeEntidadeMensuravel();
	TipoDeEntidadeMensuravel tipoPSPadrao = new TipoDeEntidadeMensuravel();
	TipoDeEntidadeMensuravel tipoAPadrao = new TipoDeEntidadeMensuravel();
	TipoDeEntidadeMensuravel tipoPSProjeto = new TipoDeEntidadeMensuravel();
	TipoDeEntidadeMensuravel tipoAProjeto = new TipoDeEntidadeMensuravel();
	TipoDeEntidadeMensuravel tipoOcorrenciaPS = new TipoDeEntidadeMensuravel();
	TipoDeEntidadeMensuravel tipoOcorrenciaAtividade = new TipoDeEntidadeMensuravel();
	TipoDeEntidadeMensuravel tipoTipoArtefato = new TipoDeEntidadeMensuravel();
	TipoDeEntidadeMensuravel tipoArtefato = new TipoDeEntidadeMensuravel();

	tipoProjeto.setNome("Projeto");
	tipoProjeto.setDescricao("Representa um novo Projeto de software.");

	tipoPSPadrao.setNome("Processo de Software Padr�o");
	tipoPSPadrao.setDescricao("Processo de Software Padr�o da organiza��o. Ex: Processo de Ger�ncia de Requisitos, Processo de Testes, Processo de Desenvolvimento de Software.");

	tipoAPadrao.setNome("Atividade Padr�o");
	tipoAPadrao.setDescricao("Atividade presente no Processo de Software Padr�o");

	tipoPSProjeto.setNome("Processo de Software de Projeto");
	tipoPSProjeto.setDescricao("Processo ajustado para um determinado projeto de acordo com um Processo de Software Padr�o. Ex: Processo de Testes do Projeto Sincap.");

	tipoAProjeto.setNome("Atividade de Projeto");
	tipoAProjeto.setDescricao("Atividade ajustada para um determinado projeto de acordo com uma Atividade Padr�o.");

	tipoOcorrenciaPS.setNome("Ocorr�ncia de Processo de Software");
	tipoOcorrenciaPS.setDescricao("Ocorr�ncia de uma determinada inst�ncia do Processo de Software de Projeto. Ex: Ocorr�ncia do Processo de Testes.");

	tipoOcorrenciaAtividade.setNome("Ocorr�ncia de Atividade");
	tipoOcorrenciaAtividade.setDescricao("Ocorr�ncia de uma determinada atividade do Processo de Software de Projeto.");

	tipoTipoArtefato.setNome("Tipo de Artefato");
	tipoTipoArtefato.setDescricao("Tipo de Artefato de software. Ex: Documento, Modelo, C�digo fonte, Planos, etc.");

	tipoArtefato.setNome("Artefato");
	tipoArtefato.setDescricao("Representa um novo artefato de software.");

	//Persiste.
	List<TipoDeEntidadeMensuravel> tiposParaPersistir = new ArrayList<TipoDeEntidadeMensuravel>();
	tiposParaPersistir.add(tipoProjeto);
	tiposParaPersistir.add(tipoPSPadrao);
	tiposParaPersistir.add(tipoAPadrao);
	tiposParaPersistir.add(tipoPSProjeto);
	tiposParaPersistir.add(tipoAProjeto);
	tiposParaPersistir.add(tipoOcorrenciaPS);
	tiposParaPersistir.add(tipoOcorrenciaAtividade);
	tiposParaPersistir.add(tipoTipoArtefato);
	tiposParaPersistir.add(tipoArtefato);

	for (TipoDeEntidadeMensuravel tipo : tiposParaPersistir)
	{
	    try
	    {
		manager.getTransaction().begin();
		manager.persist(tipo);
		manager.getTransaction().commit();
	    }
	    catch (Exception ex)
	    {
		if (ex.getCause() != null &&
			ex.getCause().getCause() != null &&
			ex.getCause().getCause() instanceof ConstraintViolationException)
		{
		    System.out.println(String.format("O Tipo de Entidades Mensur�vel %s j� existe.", tipo.getNome()));
		}
		else
		{
		    throw ex;
		}
	    }
	}

	manager.close();
    }

    private static void inicializarPeriodicidades() throws Exception
    {
	//Configura as periodicidades.	
	EntityManager manager = XPersistence.createManager();

	Periodicidade cadaOcorrencia = new Periodicidade();
	Periodicidade diaria = new Periodicidade();
	Periodicidade semanal = new Periodicidade();
	Periodicidade quinzenal = new Periodicidade();
	Periodicidade mensal = new Periodicidade();
	Periodicidade trimestral = new Periodicidade();
	Periodicidade semestral = new Periodicidade();
	Periodicidade anual = new Periodicidade();

	cadaOcorrencia.setNome("Em cada ocorr�ncia da atividade");
	cadaOcorrencia.setDescricao("Em cada ocorr�ncia da atividade");

	diaria.setNome("Di�ria");
	diaria.setDescricao("Di�ria");

	semanal.setNome("Semanal");
	semanal.setDescricao("Semanal");

	quinzenal.setNome("Quinzenal");
	quinzenal.setDescricao("Quinzenal");

	mensal.setNome("Mensal");
	mensal.setDescricao("Mensal");

	trimestral.setNome("Trimestral");
	trimestral.setDescricao("Trimestral");

	semestral.setNome("Semestral");
	semestral.setDescricao("Semestral");

	anual.setNome("Anual");
	anual.setDescricao("Anual");

	//Persiste.
	List<Periodicidade> periodicidadesParaPersistir = new ArrayList<Periodicidade>();
	periodicidadesParaPersistir.add(cadaOcorrencia);
	periodicidadesParaPersistir.add(diaria);
	periodicidadesParaPersistir.add(semanal);
	periodicidadesParaPersistir.add(quinzenal);
	periodicidadesParaPersistir.add(mensal);
	periodicidadesParaPersistir.add(trimestral);
	periodicidadesParaPersistir.add(semestral);
	periodicidadesParaPersistir.add(anual);

	for (Periodicidade p : periodicidadesParaPersistir)
	{
	    try
	    {
		manager.getTransaction().begin();
		manager.persist(p);
		manager.getTransaction().commit();
	    }
	    catch (Exception ex)
	    {
		if (ex.getCause() != null &&
			ex.getCause().getCause() != null &&
			ex.getCause().getCause() instanceof ConstraintViolationException)
		{
		    System.out.println(String.format("A Periodicidade %s j� existe.", p.getNome()));
		}
		else
		{
		    throw ex;
		}
	    }
	}

	manager.close();
    }

}
