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
package org.somespc.webservices.rest;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import org.somespc.integracao.SoMeSPCIntegrator;
import org.somespc.integracao.taiga.*;
import org.somespc.integracao.taiga.model.*;
import org.somespc.model.definicao_operacional_de_medida.Periodicidade;
import org.somespc.model.plano_de_medicao.*;
import org.somespc.util.json.*;
import org.somespc.webservices.rest.dto.*;

@Path("TaigaIntegrator")
public class TaigaIntegratorResource
{
    @Path("/Projetos")
    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response obterProjetos(TaigaLoginDTO login) throws Exception
    {

	Response response;

	if (login == null || login.getUrl().isEmpty()
		|| login.getUsuario().isEmpty() || login.getSenha().isEmpty())
	{
	    response = Response.status(Status.BAD_REQUEST).build();
	}
	else
	{
	    TaigaIntegrator integrator = new TaigaIntegrator(login.getUrl(), login.getUsuario(), login.getSenha());
	    try
	    {
		List<Projeto> projetos = integrator.obterProjetosTaiga();
		response = Response.ok().entity(projetos).build();
	    }
	    catch (Exception ex)
	    {
		ex.printStackTrace();
		response = Response.status(Status.BAD_REQUEST).build();
	    }
	}

	return response;
    }

    @Path("/Medidas")
    @GET
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response obterMedidas() throws Exception
    {
	MedidasTaiga[] medidasTaiga = MedidasTaiga.PONTOS_ALOCADOS_PROJETO.getDeclaringClass().getEnumConstants();

	List<String> nomesMedidas = new ArrayList<String>();
	for (MedidasTaiga medida : medidasTaiga)
	{
	    nomesMedidas.add(medida.toString());
	}

	return Response.ok().entity(nomesMedidas).build();
    }
    
    
    @Path("/ItensPlanoDeMedicao")
    @GET
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response obterObjetivos() throws Exception
    {

	String OE = "OE � Melhorar o gerenciamento dos projetos de software da organiza��o";
	
	String OM_1 = "OM � Melhorar a ader�ncia ao planejamento de pontos de est�ria nos projetos";
	String OM_2 = "OM � Melhorar a ader�ncia ao planejamento do n�mero de sprints dos projetos";
	String OM_3 = "OM � Melhorar ader�ncia ao planejamento das sprints dos projetos";
	String OM_4 = "OM � Monitorar a produtividade das sprints dos projetos";
	String OM_5 = "OM � Monitorar quantidade de doses de locaine nas sprints";
	String OM_6 = "OM � Monitorar velocidade dos projetos";
    	
    List<ItemPlanoDeMedicaoDTO> itensPlanoDeMedicao = new ArrayList<ItemPlanoDeMedicaoDTO>();
	
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_1, "NI � Quantos pontos de est�ria foram planejados para o projeto?", "ME � Pontos de Est�ria Planejados para o Projeto (PEPP)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_1, "NI � Quantos pontos de est�ria foram alocados no projeto?", "ME � Pontos de Est�ria Alocados no Projeto"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_1, "NI � Quantos pontos de est�ria foram conclu�dos no projeto?", "ME � Pontos de Est�ria Conclu�dos no Projeto (PECP)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_1, "NI � Qual a taxa de conclus�o de pontos de est�ria no projeto?", "ME � Taxa de Conclus�o de Pontos de Est�ria no Projeto (TCPEP)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_2, "NI � Quantas sprints foram planejadas para o projeto?", "ME � N�mero de Sprints Planejadas para o Projeto (NSPP)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_2, "NI � Quantas sprints foram realizadas no projeto?", "ME � N�mero de Sprints Realizadas no Projeto (NSRP)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_2, "NI � Qual ataxa de conclus�o de sprintsnoprojeto?", " ME � Taxa de Conclus�o dedeSprintsno Projeto (TCSP)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_3, "NI � Quantas est�rias foram planejadas para a sprint?", "ME � N�mero de Est�rias Planejadas para a Sprint (NEPS)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_3, "NI � Quantas est�rias foram conclu�das na sprint?", "ME � N�mero de Est�rias Conclu�das na Sprint (NECS)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_3, "NI � Qual a taxa de conclus�o de est�rias na sprint?", "ME � Taxa de Conclus�o de Est�rias na Sprint (TCES)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_3, "NI � Quantos pontos de est�ria foram conclu�dos na sprint?", "ME � Pontos de Est�ria Conclu�dos na Sprint (PECS)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_3, "NI � Qual a taxa de conclus�o de pontos de est�rias na sprint?", "ME � Taxa de Conclus�o de Pontos de Est�rias na Sprint (TCPES)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_3, "NI � Quantas tarefas foram planejados para a sprint?", "ME � N�mero de Tarefas Planejadas para a Sprint (NTPS)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_3, "NI � Quantas tarefas foram conclu�das na sprint?", "ME � N�mero de Tarefas Conclu�das na Sprint (NTCS)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_3, "NI � Qual a taxa de conclus�o de tarefas na sprint?", "ME � Taxa de Conclus�o de Tarefas na Sprint (TCTS)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_3, "NI � Quantos pontos de est�ria foram planejados para a sprint?", "ME � Pontos de Est�ria Planejados para a Sprint (PEPS)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_4, "NI � Quantas sprints foram realizadas no projeto?", "ME � N�mero de Sprints Realizadas no Projeto (NSRP)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_4, "NI � Quantas est�rias foram conclu�das para o projeto?", "ME � N�mero de Est�rias Conclu�das para o Projeto (NECP)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_4, "NI � Qual o n�mero m�dio de est�rias conclu�das por sprint no projeto?", "ME � M�dia de Est�rias Conclu�das por Sprint do Projeto (MECSP)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_4, "NI � Quantas est�rias foram conclu�das para o projeto?", "ME � N�mero de Est�rias Conclu�das para o Projeto (NECP)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_4, "NI � Quantos pontos de est�ria foram conclu�dos no projeto?", "ME � Pontos de Est�ria Conclu�dos no Projeto (PECP)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_4, "NI � Qual o n�mero m�dio de pontos de est�rias conclu�dos por sprint no projeto? ", "ME � M�dia de Pontos de Est�rias Conclu�dos por Sprint do Projeto (MPECSP)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_5, "NI � Quantasdoses de Iocaine ocorreram na sprint", "ME � N�mero de Doses de Iocaine na Sprint (NDIS)"));
    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_5, "NI � Quantas tarefas foram realizadas na sprint?", "ME � N�mero de Tarefas Realizadas na Sprint (NTRS)"));
	itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_5, "NI � Qual a taxa de doses de Iocainena sprint?", "ME � Taxa de Doses de Iocaine na Sprint (TDIS)"));
	itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_6, "NI � Qual a velocidade do projeto?", "ME � Velocidade do Projeto (VP)"));

	return Response.ok().entity(itensPlanoDeMedicao).build();
    }

    /**
     * Cria plano projeto.
     * 
     * @param planoDto
     *            - Recebe um objeto que cont�m todas as entidades pertencentes ao plano de medi��o para a persistencia do mesmo.
     * @throws Exception
     */
    @Path("/Plano")
    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public synchronized Response criarPlanoMedicao(PlanoDTO planoDto) throws Exception
    {

	TaigaIntegrator integrator = new TaigaIntegrator(planoDto.getTaigaLogin().getUrl(),
		planoDto.getTaigaLogin().getUsuario(), planoDto.getTaigaLogin().getSenha());

	List<Periodicidade> periodicidades = SoMeSPCIntegrator.obterPeriodicidades();
	
	List<ItemPlanoDeMedicaoDTO> itens = planoDto.getItensPlanoDeMedicao();
	
	for (ItemPlanoDeMedicaoDTO item: itens){
		
		System.out.println(item.getMedida()+item.getNomeNecessidadeDeInformacao()+item.getNomeObjetivoDeMedicao()+item.getNomeObjetivoEstrategico());
	}

	Periodicidade periodicidadeSelecionada = null;

	for (Periodicidade periodicidade : periodicidades)
	{
	    if (periodicidade.getNome().equalsIgnoreCase(planoDto.getNomePeriodicidade()))
		periodicidadeSelecionada = periodicidade;
	}

	JSONObject json = new JSONObject();

	for (int i = 0; i < planoDto.getApelidosProjetos().size(); i++)
	{
	    String apelido = planoDto.getApelidosProjetos().get(i);
	    Projeto projeto = integrator.obterProjetoTaiga(apelido);
	    PlanoDeMedicao plano = integrator.criarPlanoMedicaoProjetoSoMeSPC(planoDto.getItensPlanoDeMedicao(), periodicidadeSelecionada, projeto);

	    json.append("Plano " + (i+1), plano.getNome());
	}

	return Response.ok().entity(json).build();
    }

    
    /**
     * Cria plano teste.
     * 
     * @param planoDto
     *            - Recebe um objeto que cont�m todas as entidades pertencentes ao plano de medi��o para a persistencia do mesmo.
     * @throws Exception
     */
//    @Path("/Plano_Teste")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
//    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
//    public synchronized Response criarPlanoTeste(PlanoTesteDTO planoDto) throws Exception
//    {
//
//	TaigaIntegrator integrator = new TaigaIntegrator(planoDto.getTaigaLogin().getUrl(),
//		planoDto.getTaigaLogin().getUsuario(), planoDto.getTaigaLogin().getSenha());
//
//	List<Periodicidade> periodicidades = integrator.obterPeriodicidades();
//
//	Periodicidade periodicidadeSelecionada = null;
//
//	for (Periodicidade periodicidade : periodicidades)
//	{
//	    if (periodicidade.getNome().equalsIgnoreCase(planoDto.getNomePeriodicidade()))
//		periodicidadeSelecionada = periodicidade;
//	}
//
//	JSONObject json = new JSONObject();
//
//	for (int i = 0; i < planoDto.getApelidosProjetos().size(); i++)
//	{
//	    String apelido = planoDto.getApelidosProjetos().get(i);
//	    Projeto projeto = integrator.obterProjetoTaiga(apelido);
//	    PlanoDeMedicao plano = integrator.criarPlanoMedicaoProjetoSoMeSPCTeste(periodicidadeSelecionada, projeto);
//
//	    json.append("Plano " + (i+1), plano.getNome());
//	}
//
//	return Response.ok().entity(json).build();
//    }
}
