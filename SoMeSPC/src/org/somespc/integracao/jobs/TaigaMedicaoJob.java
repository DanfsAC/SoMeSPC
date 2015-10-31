package org.somespc.integracao.jobs;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.openxava.jpa.XPersistence;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.somespc.integracao.SoMeSPCIntegrator;
import org.somespc.integracao.taiga.TaigaIntegrator;
import org.somespc.integracao.taiga.model.EstadoProjeto;
import org.somespc.integracao.taiga.model.EstadoSprint;
import org.somespc.integracao.taiga.model.Sprint;
import org.somespc.model.plano_de_medicao.PlanoDeMedicaoDoProjeto;

@DisallowConcurrentExecution
public class TaigaMedicaoJob extends MedicaoJob {

	@Override
	public void executarMedicao(JobExecutionContext context) throws JobExecutionException {
		EntityManager manager = XPersistence.createManager();

		try {
			JobDataMap dataMap = context.getMergedJobDataMap();

			String urlTaiga = dataMap.getString("urlTaiga");
			String usuarioTaiga = dataMap.getString("usuarioTaiga");
			String senhaTaiga = dataMap.getString("senhaTaiga");
			String apelidoProjeto = dataMap.getString("apelidoProjeto");
			String nomePlano = dataMap.getString("nomePlano");
			String nomeMedida = dataMap.getString("nomeMedida").replace("ME - " , "");
			String entidadeMedida = dataMap.getString("entidadeMedida");

			String query = String.format("SELECT p FROM PlanoDeMedicaoDoProjeto p WHERE p.nome='%s'", nomePlano);
			TypedQuery<PlanoDeMedicaoDoProjeto> typedQuery = manager.createQuery(query, PlanoDeMedicaoDoProjeto.class);
			PlanoDeMedicaoDoProjeto plano = typedQuery.getSingleResult();

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			TaigaIntegrator integrator = new TaigaIntegrator(urlTaiga, usuarioTaiga, senhaTaiga);

			String valorMedido = null;
			EstadoProjeto estado = integrator.obterEstadoProjetoTaiga(apelidoProjeto);

			if (nomeMedida.equalsIgnoreCase("Pontos de Est�ria Planejados para o Projeto")) {

				valorMedido = String.valueOf(estado.getTotalPontos());
				SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, entidadeMedida, valorMedido);

			} else if (nomeMedida.equalsIgnoreCase("Pontos de Est�ria Conclu�dos no Projeto")) {

				valorMedido = String.valueOf(estado.getPontosFechados());
				SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, entidadeMedida, valorMedido);

			} else if (nomeMedida.equalsIgnoreCase("Taxa de Conclus�o de Pontos de Est�ria no Projeto")) {

				float valor = estado.getPontosFechados() / estado.getTotalPontos();
				valorMedido = String.valueOf(valor);
				SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, entidadeMedida, valorMedido);

			} else if (nomeMedida.equalsIgnoreCase("N�mero de Sprints Planejadas para o Projeto")) {

				valorMedido = String.valueOf(estado.getTotalMilestones());
				SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, entidadeMedida, valorMedido);

			} else if (nomeMedida.equalsIgnoreCase("N�mero de Sprints Realizadas no Projeto")) {

				int sprintsConcluidas = 0;
				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
					if (estadoSprint.isConcluida()) {
						sprintsConcluidas++;
					}
				}

				valorMedido = String.valueOf(sprintsConcluidas);
				SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, entidadeMedida, valorMedido);

			} else if (nomeMedida.equalsIgnoreCase("Taxa de Conclus�o de Sprints no Projeto")) {

				int sprintsConcluidas = 0;
				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
					if (estadoSprint.isConcluida()) {
						sprintsConcluidas++;
					}
				}

				int sprintsPlanejadas = estado.getTotalMilestones();

				float taxaConclusaoSprints = sprintsConcluidas / sprintsPlanejadas;

				valorMedido = String.valueOf(taxaConclusaoSprints);
				SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, entidadeMedida, valorMedido);

			} else if (nomeMedida.equalsIgnoreCase("N�mero de Est�rias Planejadas para a Sprint")) {

				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
					valorMedido = String.valueOf(estadoSprint.getTotalEstorias());
					SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, sprint.getNome(), valorMedido);
				}

			} else if (nomeMedida.equalsIgnoreCase("N�mero de Est�rias Conclu�das na Sprint")) {

				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
					valorMedido = String.valueOf(estadoSprint.getEstoriasCompletadas());
					SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, sprint.getNome(), valorMedido);
				}	

			} else if (nomeMedida.equalsIgnoreCase("Taxa de Conclus�o de Est�rias na Sprint")) {

				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
					float taxaConclusaoEstorias = estadoSprint.getEstoriasCompletadas() / estadoSprint.getTotalEstorias();
					valorMedido = String.valueOf(taxaConclusaoEstorias);
					SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, sprint.getNome(), valorMedido);
				}			

			} else if (nomeMedida.equalsIgnoreCase("Pontos de Est�ria Planejados para a Sprint")) {

				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
					Map<String, Float> map = estadoSprint.getTotalPontos();

					float totalPontos = 0f;
					for (Float ponto : map.values()) {
						totalPontos += ponto;
					}

					valorMedido = String.valueOf(totalPontos);
					SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, sprint.getNome(), valorMedido);
				}	
				
			} else if (nomeMedida.equalsIgnoreCase("Pontos de Est�ria Conclu�dos na Sprint")) {

				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
		
					float totalPontosCompletados = 0f;
					for (Float ponto : estadoSprint.getPontosCompletados()) {
						totalPontosCompletados += ponto;
					}

					valorMedido = String.valueOf(totalPontosCompletados);
					SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, sprint.getNome(), valorMedido);
				}	

			} else if (nomeMedida.equalsIgnoreCase("Taxa de Conclus�o de Pontos de Est�rias na Sprint")) {

				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
		
					Map<String, Float> map = estadoSprint.getTotalPontos();

					float totalPontos = 0f;
					for (Float ponto : map.values()) {
						totalPontos += ponto;
					}

					float totalPontosCompletados = 0f;
					for (Float ponto : estadoSprint.getPontosCompletados()) {
						totalPontosCompletados += ponto;
					}

					float taxaConclusaoEstorias = totalPontos / totalPontosCompletados;
					valorMedido = String.valueOf(taxaConclusaoEstorias);
					SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, sprint.getNome(), valorMedido);
				}	
								
			} else if (nomeMedida.equalsIgnoreCase("N�mero de Tarefas Planejadas para a Sprint")) {

				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
					valorMedido = String.valueOf(estadoSprint.getTotalTarefas());
					SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, sprint.getNome(), valorMedido);
				}									

			} else if (nomeMedida.equalsIgnoreCase("N�mero de Tarefas Conclu�das na Sprint")) {

				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
					valorMedido = String.valueOf(estadoSprint.getTarefasCompletadas());
					SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, sprint.getNome(), valorMedido);
				}	
				
			} else if (nomeMedida.equalsIgnoreCase("Taxa de Conclus�o de Tarefas na Sprint")) {

				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
					float taxaConclusaoTarefas = estadoSprint.getTarefasCompletadas() / estadoSprint.getTotalTarefas();
					valorMedido = String.valueOf(taxaConclusaoTarefas);
					SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, sprint.getNome(), valorMedido);
				}	
				
			} else if (nomeMedida.equalsIgnoreCase("N�mero de Est�rias Conclu�das para o Projeto")) {

				int estoriasConcluidasProjeto = 0;
				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
					estoriasConcluidasProjeto += estadoSprint.getEstoriasCompletadas();
				}

				valorMedido = String.valueOf(estoriasConcluidasProjeto);
				SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, entidadeMedida, valorMedido);		
				
			} else if (nomeMedida.equalsIgnoreCase("M�dia de Est�rias Conclu�das por Sprint do Projeto")) {

				int estoriasConcluidasProjeto = 0;
				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
					estoriasConcluidasProjeto += estadoSprint.getEstoriasCompletadas();
				}
				
				float mediaEstoriasConcluidasPorSprint = estoriasConcluidasProjeto / estado.getTotalMilestones();

				valorMedido = String.valueOf(mediaEstoriasConcluidasPorSprint);
				SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, entidadeMedida, valorMedido);		

			} else if (nomeMedida.equalsIgnoreCase("Velocidade da Equipe no Projeto")) {

				valorMedido = String.valueOf(estado.getVelocidade());
				SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, entidadeMedida, valorMedido);

			} else if (nomeMedida.equalsIgnoreCase("N�mero de Doses de Iocaine na Sprint")) {

				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
					valorMedido = String.valueOf(estadoSprint.getDosesIocaine());
					SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, sprint.getNome(), valorMedido);
				}								

			} else if (nomeMedida.equalsIgnoreCase("Taxa de Doses de Iocaine na Sprint")) {

				List<Sprint> sprints = integrator.obterSprintsDoProjetoTaiga(apelidoProjeto);

				for (Sprint sprint : sprints) {
					EstadoSprint estadoSprint = integrator.obterEstadoSprintTaiga(apelidoProjeto, sprint.getApelido());
					float taxaDosesIocaine = estadoSprint.getDosesIocaine() / estadoSprint.getEstoriasCompletadas();
					valorMedido = String.valueOf(taxaDosesIocaine);
					SoMeSPCIntegrator.criarMedicao(plano, timestamp, nomeMedida, sprint.getNome(), valorMedido);
				}						
			
			} else {
				throw new Exception(String.format("Medida %s n�o encontrada.", nomeMedida));
			}

			System.out.println(String.format("Job %s (%s) executado com sucesso.",
					context.getTrigger().getKey().getName(), context.getTrigger().getKey().getGroup()));

		} catch (Exception ex) {
			System.err.println(
					String.format("Erro ao executar o job %s (%s): %s ", context.getTrigger().getKey().getName(),
							context.getTrigger().getKey().getGroup(), ex.getMessage()));

			ex.printStackTrace();
		} finally {
			manager.close();
		}
	}

}