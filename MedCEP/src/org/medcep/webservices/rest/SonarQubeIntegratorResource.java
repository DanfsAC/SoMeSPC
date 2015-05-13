package org.medcep.webservices.rest;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import org.medcep.integracao.sonarqube.*;
import org.medcep.integracao.sonarqube.model.*;
import org.medcep.integracao.taiga.*;
import org.medcep.integracao.taiga.model.*;
import org.medcep.model.medicao.*;
import org.medcep.model.medicao.planejamento.*;
import org.medcep.util.json.*;
import org.medcep.webservices.rest.dto.*;

@Path("SonarQubeIntegrator")
public class SonarQubeIntegratorResource
{
    @Path("/Projetos")
    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response obterProjetos(String urlSonar) throws Exception
    {
	Response response;

	if (urlSonar == null || urlSonar.isEmpty())
	{
	    response = Response.status(Status.BAD_REQUEST).build();
	}
	else
	{
	    SonarQubeIntegrator integrator = new SonarQubeIntegrator(urlSonar);
	    try
	    {
		List<Recurso> projetos = integrator.obterProjetos();
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

    @Path("/Metricas")
    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response obterMedidas(String urlSonar) throws Exception
    {
	Response response;

	if (urlSonar == null || urlSonar.isEmpty())
	{
	    response = Response.status(Status.BAD_REQUEST).build();
	}
	else
	{
	    SonarQubeIntegrator integrator = new SonarQubeIntegrator(urlSonar);
	    try
	    {
		List<Metrica> metricas = integrator.obterMetricas();
		Collections.sort(metricas);
		response = Response.ok().entity(metricas).build();
	    }
	    catch (Exception ex)
	    {
		ex.printStackTrace();
		response = Response.status(Status.BAD_REQUEST).build();
	    }
	}

	return response;
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

	List<MedidasTaiga> medidas = new ArrayList<MedidasTaiga>();

	for (String medida : planoDto.getNomesMedidas())
	{
	    MedidasTaiga medidaTaiga = MedidasTaiga.get(medida);
	    medidas.add(medidaTaiga);
	}

	TaigaIntegrator integrator = new TaigaIntegrator(planoDto.getTaigaLogin().getUrl(),
		planoDto.getTaigaLogin().getUsuario(), planoDto.getTaigaLogin().getSenha());

	List<Periodicidade> periodicidades = integrator.obterPeriodicidades();

	Periodicidade periodicidadeSelecionada = null;

	for (Periodicidade periodicidade : periodicidades)
	{
	    if (periodicidade.getNome().equalsIgnoreCase(planoDto.getNomePeriodicidade()))
		periodicidadeSelecionada = periodicidade;
	}

	Projeto projeto = integrator.obterProjetoTaiga(planoDto.getApelidoProjeto());

	PlanoDeMedicao plano = integrator.criarPlanoMedicaoProjetoMedCEP(medidas, periodicidadeSelecionada, projeto);
	
	JSONObject json = new JSONObject();
	json.append("nome", plano.getNome());
	
	return Response.ok().entity(json).build();
    }

}
