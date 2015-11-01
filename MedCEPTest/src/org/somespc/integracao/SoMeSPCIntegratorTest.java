package org.somespc.integracao;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.somespc.inicializacao.SoMeSPCStarter;
import org.somespc.integracao.sonarqube.SonarQubeIntegrator;
import org.somespc.integracao.sonarqube.model.Recurso;
import org.somespc.integracao.taiga.TaigaIntegrator;
import org.somespc.integracao.taiga.model.Projeto;
import org.somespc.model.definicao_operacional_de_medida.Periodicidade;
import org.somespc.model.plano_de_medicao.PlanoDeMedicao;
import org.somespc.webservices.rest.dto.ItemPlanoDeMedicaoDTO;
import org.somespc.webservices.rest.dto.SonarLoginDTO;
import org.somespc.webservices.rest.dto.TaigaLoginDTO;

public class SoMeSPCIntegratorTest {
	
	private TaigaIntegrator taigaIntegrator;
	private SonarQubeIntegrator sonarIntegrator;

	@Before
	public void init() throws Exception {
		SoMeSPCStarter.inicializarSoMeSPC();
		taigaIntegrator = new TaigaIntegrator("https://api.taiga.io/", "vinnysoft", "teste123");
		sonarIntegrator = new SonarQubeIntegrator("http://localhost:9000/");
	}
	
	@Test
	public void testCriarPlanoMedicaoConjunto() throws Exception {
		
		SonarLoginDTO sonarLogin = new SonarLoginDTO();
		sonarLogin.setUrl("http://localhost:9000/");
		
		TaigaLoginDTO taigaLogin = new TaigaLoginDTO();
		taigaLogin.setUrl("https://api.taiga.io/");
		taigaLogin.setUsuario("vinnysoft");
		taigaLogin.setSenha("teste123");
		
		List<Periodicidade> periodicidades = SoMeSPCIntegrator.obterPeriodicidades();		
		Periodicidade periodicidadeSelecionada = null;

		for (Periodicidade periodicidade : periodicidades) {
			if (periodicidade.getNome().equalsIgnoreCase("Por Hora"))
				periodicidadeSelecionada = periodicidade;
		}
		String OE = "Melhorar o gerenciamento dos projetos de software da organiza��o";
		
		String OM_1 = "Melhorar a ader�ncia ao planejamento de pontos de est�ria nos projetos";
		String OM_7 = "Monitorar a qualidade do c�digo fonte produzido";
    	
	    List<ItemPlanoDeMedicaoDTO> itensPlanoDeMedicao = new ArrayList<ItemPlanoDeMedicaoDTO>();
	    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_1, "Quantos pontos de est�ria foram planejados para o projeto?", "Pontos de Est�ria Planejados para o Projeto", "Taiga"));
	    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_1, "Quantos pontos de est�ria foram conclu�dos no projeto?", "Pontos de Est�ria Conclu�dos no Projeto", "Taiga"));
	    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_1, "Qual a taxa de conclus�o de pontos de est�ria no projeto?", "Taxa de Conclus�o de Pontos de Est�ria no Projeto", "Taiga"));
	    itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_7, "Qual a complexidade ciclom�tica m�dia por m�todo?", "M�dia da Complexidade Ciclom�tica por M�todo", "SonarQube"));
		itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_7, "Qual a taxa de duplica��o de c�digo?", "Taxa de Duplica��o de C�digo", "SonarQube"));
		itensPlanoDeMedicao.add(new ItemPlanoDeMedicaoDTO(OE, OM_7, "Qual o percentual da d�vida t�cnica?", "Percentual da D�vida T�cnica", "SonarQube"));
	
		Recurso projetoSonar = sonarIntegrator.obterRecurso("SoMeSPC");
		Projeto projeto = taigaIntegrator.obterProjetoTaiga("almereyda-jon30");
				
		PlanoDeMedicao plano = SoMeSPCIntegrator.criarPlanoMedicaoProjetoTaigaSonarQubeSoMeSPC(itensPlanoDeMedicao, 
				periodicidadeSelecionada, taigaLogin, projeto, sonarLogin, Arrays.asList(projetoSonar));
		
		assertNotNull(plano);
				
		//dump(plano);		
	}

		
}
