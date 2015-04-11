package org.medcep.inicializacao;

import java.util.*;

import javax.persistence.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.hibernate.exception.*;
import org.medcep.model.medicao.*;
import org.openxava.jpa.*;

/**
 * Classe para inicializar a MedCEP.
 * 
 * @author Vinicius
 *
 */
public class MedCEPStarter extends HttpServlet
{

    private static final long serialVersionUID = -718214408262760803L;

    public void init() throws ServletException
    {
	try
	{
	    MedCEPStarter.inicializarMedCEP();
	}
	catch (Exception e)
	{
	    System.out.println("Erro ao inicializar os dados b�sicos da MedCEP.");
	    e.printStackTrace();
	}
    }

    /**
     * Inicializa o banco de dados da MedCEP com informa��es b�sicas.
     * 
     * @throws Exception
     */
    public static void inicializarMedCEP() throws Exception
    {
	inicializarTiposElementosMensuraveis();
	inicializarTiposMedidas();
	inicializarTiposEntidadesMensuraveis();
	inicializarPeriodicidades();
	inicializarEscalas();
	inicializarUnidadesMedida();
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

    private static void inicializarTiposMedidas() throws Exception
    {
	//Configura os tipos de elementos mensur�veis.	
	EntityManager manager = XPersistence.createManager();

	TipoMedida mb = new TipoMedida();
	TipoMedida md = new TipoMedida();

	mb.setNome("Medida Base");
	md.setNome("Medida Derivada");

	//Persiste.
	try
	{
	    manager.getTransaction().begin();

	    manager.persist(mb);
	    manager.persist(md);
	    manager.getTransaction().commit();
	}
	catch (Exception ex)
	{
	    if (ex.getCause() != null &&
		    ex.getCause().getCause() != null &&
		    ex.getCause().getCause() instanceof ConstraintViolationException)
	    {
		System.out.println("Tipos de Medidas j� cadastrados.");
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

    private static void inicializarEscalas() throws Exception
    {
	//Configura as escalas.	
	EntityManager manager = XPersistence.createManager();

	TipoEscala tipoIntervalar = new TipoEscala();
	TipoEscala tipoOrdinal = new TipoEscala();
	TipoEscala tipoRacional = new TipoEscala();

	tipoIntervalar.setNome("Intervalar");
	tipoOrdinal.setNome("Ordinal");
	tipoRacional.setNome("Racional");

	//Persiste.
	List<TipoEscala> tiposParaPersistir = new ArrayList<TipoEscala>();
	tiposParaPersistir.add(tipoIntervalar);
	tiposParaPersistir.add(tipoOrdinal);
	tiposParaPersistir.add(tipoRacional);

	for (TipoEscala tipo : tiposParaPersistir)
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
		    System.out.println(String.format("O Tipo de Escala %s j� existe.", tipo.getNome()));
		}
		else
		{
		    throw ex;
		}
	    }
	}

	Escala escalaPercentual = new Escala();
	escalaPercentual.setNome("Percentual");
	escalaPercentual.setTipoEscala(tipoRacional);

	List<ValorDeEscala> valoresPercentuais = new ArrayList<ValorDeEscala>();
	ValorDeEscala valorPercentual = new ValorDeEscala();
	valorPercentual.setNumerico(true);	
	valorPercentual.setValor("N�meros racionais de 0 a 100");
	valoresPercentuais.add(valorPercentual);
	
	List<Escala> escalasPercentuais = new ArrayList<Escala>();	
	escalaPercentual.setValorDeEscala(valoresPercentuais);
	escalasPercentuais.add(escalaPercentual);
	
	Escala escalaNumerosRacionais = new Escala();
	escalaNumerosRacionais.setNome("N�meros Racionais");
	escalaNumerosRacionais.setTipoEscala(tipoRacional);

	List<ValorDeEscala> valoresRacionais = new ArrayList<ValorDeEscala>();
	ValorDeEscala valorRacional = new ValorDeEscala();
	valorRacional.setNumerico(true);
	valorRacional.setValor("N�meros racionais");
	valoresRacionais.add(valorRacional);
	
	List<Escala> escalasRacionais = new ArrayList<Escala>();	
	escalaNumerosRacionais.setValorDeEscala(valoresRacionais);
	escalasRacionais.add(escalaNumerosRacionais);

	try
	{
	    manager.getTransaction().begin();
	    manager.persist(escalaPercentual);
	    manager.persist(escalaNumerosRacionais);
	    manager.persist(valorPercentual);
	    manager.persist(valorRacional);
	    manager.getTransaction().commit();
	}
	catch (Exception ex)
	{
	    if (ex.getCause() != null &&
		    ex.getCause().getCause() != null &&
		    ex.getCause().getCause() instanceof ConstraintViolationException)
	    {
		System.out.println("As escalas j� existem.");
	    }
	    else
	    {
		throw ex;
	    }
	}
	
	manager.close();
    }
    
    private static void inicializarUnidadesMedida() throws Exception
    {
	//Configura as periodicidades.	
	EntityManager manager = XPersistence.createManager();

	UnidadeDeMedida pontosEstoria = new UnidadeDeMedida();
	
	pontosEstoria.setNome("Pontos de Est�ria");
	pontosEstoria.setDescricao("Pontos de Est�ria do Scrum.");

	//Persiste.
	List<UnidadeDeMedida> unidadesParaPersistir = new ArrayList<UnidadeDeMedida>();
	unidadesParaPersistir.add(pontosEstoria);
	

	for (UnidadeDeMedida u : unidadesParaPersistir)
	{
	    try
	    {
		manager.getTransaction().begin();
		manager.persist(u);
		manager.getTransaction().commit();
	    }
	    catch (Exception ex)
	    {
		if (ex.getCause() != null &&
			ex.getCause().getCause() != null &&
			ex.getCause().getCause() instanceof ConstraintViolationException)
		{
		    System.out.println(String.format("A Unidade de Medida %s j� existe.", u.getNome()));
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
