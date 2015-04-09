package org.medcep.integracao.taiga;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.*;

import org.medcep.util.json.*;

public class TaigaIntegrator
{
    private final String urlTaiga;
    private final AuthInfo authInfo;
    private static Client client;

    public TaigaIntegrator(String urlTaiga, String usuario, String senha)
    {
	if (urlTaiga.endsWith("/"))
	{
	    urlTaiga = urlTaiga.substring(0, urlTaiga.length() - 1);
	}

	this.urlTaiga = urlTaiga + "/api/v1/";
	this.authInfo = new AuthInfo(usuario, senha);
	client = ClientBuilder.newClient();
    }

    /**
     * Busca o token de autentica��o do Taiga.
     */
    public String obterAuthToken()
    {	
	WebTarget target = client.target(this.urlTaiga).path("auth");
				
	Response response = target
		.request(MediaType.APPLICATION_JSON_TYPE)
		.post(Entity.entity(authInfo, MediaType.APPLICATION_JSON));

	if (response.getStatus() != Status.OK.getStatusCode())
	{
	    throw new RuntimeException("Erro ao obter o token de autentica��o do Taiga. HTTP Code: " + response.getStatus());
	}

	JSONObject json = new JSONObject(response.readEntity(String.class));	
	return json.get("auth_token").toString();	
    }

    /**
     * Obtem os dados do projeto Taiga.
     * @param nomeProjeto - Nome do projeto a ser buscado.
     * @return JSON com os dados do projeto.
     */
    public String obterProjeto(String nomeProjeto)
    {
	//Resolve o ID do projeto.
	WebTarget target = client.target(this.urlTaiga).path("resolver").queryParam("project", nomeProjeto.toLowerCase());
	
	Response response = target
		.request(MediaType.APPLICATION_JSON_TYPE)
		.header("Authorization", String.format("Bearer %s", obterAuthToken()))
		.get();

	if (response.getStatus() != Status.OK.getStatusCode())
	{
	    throw new RuntimeException(String.format("Erro ao obter o ID do projeto %s pela API Resolver. HTTP Code: %s", nomeProjeto, response.getStatus()));
	}
	
	JSONObject json = new JSONObject(response.readEntity(String.class));
	int idProjeto = json.getInt("project");
	
	//Busca informa��es do projeto.
	target = client.target(this.urlTaiga).path("projects/" + idProjeto + "/stats");
	
	response = target
		.request(MediaType.APPLICATION_JSON_TYPE)
		.header("Authorization", String.format("Bearer %s", obterAuthToken()))
		.get();

	if (response.getStatus() != Status.OK.getStatusCode())
	{
	    throw new RuntimeException(String.format("Erro ao obter o projeto %s. HTTP Code: %s", nomeProjeto, response.getStatus()));
	}
	
	json = new JSONObject(response.readEntity(String.class));	
	return json.toString();	
    }

    /**
     * Classe para mapear as informa��es de autentica��o com o Taiga.
     * @author Vinicius
     *
     */
    @XmlRootElement
    public class AuthInfo
    {
	//TODO: Avaliar implementa��o do tipo Github.
	private String type = "normal";
	private String username;
	private String password;

	public AuthInfo()
	{
	    
	}
	
	public AuthInfo(String username, String password)
	{
	    this.username = username;
	    this.password = password;
	}
	
	public String getType()
	{
	    return type;
	}

	public String getUsername()
	{
	    return username;
	}

	public void setUsername(String username)
	{
	    this.username = username;
	}

	public String getPassword()
	{
	    return password;
	}

	public void setPassword(String password)
	{
	    this.password = password;
	}

    }

}
