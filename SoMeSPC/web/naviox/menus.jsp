<div id="menu_tree" style="margin-top: 10px;">
	<%-- 
	Necess�rio informar o id do elemento li com o mesmo valor 
	do nome do m�dulo na URL (ap�s o /m/) para funcionar o javascript 
	de reselecionar o m�dulo no jstree 
--%>

	<ul>
		<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/painel.png"}' id="PainelControle"><a href="<%=request.getContextPath()%>/naviox/painel.jsp"> Painel de Controle</a></li>		
		<%--<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/planejamento.png"}' id="PlanoDeMedicaoIntegradoTaiga"><a href="<%=request.getContextPath()%>/naviox/wizard_taiga.jsp"> Novo Plano de Medi��o Integrado (Taiga)</a></li>--%>
		<%--<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/planejamento.png"}' id="PlanoDeMedicaoIntegradoSonarQube"><a href="<%=request.getContextPath()%>/naviox/wizard_sonarqube.jsp"> Novo Plano de Medi��o Integrado (SonarQube)</a></li>--%>
		<%--<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/planejamento.png"}' id="PlanoDeMedicaoIntegrado"><a href="<%=request.getContextPath()%>/naviox/wizard_integrator.jsp"> Novo Plano de Medi��o Integrado</a></li>--%>
		<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/planejamento.png"}' id="Wizard"><a href="<%=request.getContextPath()%>/naviox/wizard.jsp">Novo Plano de Medi��o Integrado</a></li>
		<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/cadastros.png"}' class="jstree-close">Cadastros

			<ul>
				<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' class="jstree-close">Organiza��o
					<ul>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="PapelRecursoHumano"><a href="<%=request.getContextPath()%>/m/PapelRecursoHumano"> Papel de Recurso Humano</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="RecursoHumano"><a href="<%=request.getContextPath()%>/m/RecursoHumano"> Recurso Humano</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="Equipe"><a href="<%=request.getContextPath()%>/m/Equipe"> Equipe</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="CriterioDeProjeto"><a href="<%=request.getContextPath()%>/m/CriterioDeProjeto"> Crit�rio de Caracteriza��o do Projeto</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="Projeto"><a href="<%=request.getContextPath()%>/m/Projeto"> Projeto</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="TipoDeProcessoPadrao"><a href="<%=request.getContextPath()%>/m/TipoDeProcessoPadrao"> Tipo de Processo Padr�o</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="ProcessoPadrao"><a href="<%=request.getContextPath()%>/m/ProcessoPadrao"> Processo Padr�o</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="AtividadePadrao"><a href="<%=request.getContextPath()%>/m/AtividadePadrao"> Atividade Padr�o</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="TipoDeProcessoProjeto"><a href="<%=request.getContextPath()%>/m/TipoDeProcessoProjeto"> Tipo de Processo de Projeto</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="ProcessoProjeto"><a href="<%=request.getContextPath()%>/m/ProcessoProjeto"> Processo de Projeto</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="AtividadeProjeto"><a href="<%=request.getContextPath()%>/m/AtividadeProjeto"> Atividade de Projeto</a></li>
<%-- 						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="OcorrenciaProcesso"><a href="<%=request.getContextPath()%>/m/OcorrenciaProcesso"> Ocorr�ncia de Processo</a></li> --%>
<%-- 						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="OcorrenciaAtividade"><a href="<%=request.getContextPath()%>/m/OcorrenciaAtividade"> Ocorr�ncia de Atividade</a></li> --%>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="TipoDeArtefato"><a href="<%=request.getContextPath()%>/m/TipoDeArtefato"> Tipo de Artefato</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="Artefato"><a href="<%=request.getContextPath()%>/m/Artefato"> Artefato</a></li>
<%-- 						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/organizacao.png"}' id="Procedimento"><a href="<%=request.getContextPath()%>/m/Procedimento"> Procedimento</a></li> --%>
					</ul>
				</li>
				<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' class="jstree-close">Medi��o
					<ul>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="EntidadeMensuravel"><a href="<%=request.getContextPath()%>/m/EntidadeMensuravel"> Entidade Mensur�vel</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="TipoDeEntidadeMensuravel"><a href="<%=request.getContextPath()%>/m/TipoDeEntidadeMensuravel"> Tipo de Entidade Mensur�vel</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="ElementoMensuravel"><a href="<%=request.getContextPath()%>/m/ElementoMensuravel"> Elemento Mensur�vel</a></li>
<%-- 						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="TipoElementoMensuravel"><a href="<%=request.getContextPath()%>/m/TipoElementoMensuravel"> Tipo de Elemento Mensur�vel</a></li> --%>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="ObjetivoEstrategico"><a href="<%=request.getContextPath()%>/m/ObjetivoEstrategico"> Objetivo Estrat�gico</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="ObjetivoDeSoftware"><a href="<%=request.getContextPath()%>/m/ObjetivoDeSoftware"> Objetivo de Software</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="ObjetivoDeMedicao"><a href="<%=request.getContextPath()%>/m/ObjetivoDeMedicao"> Objetivo de Medi��o</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="NecessidadeDeInformacao"><a href="<%=request.getContextPath()%>/m/NecessidadeDeInformacao"> Necessidade de Informa��o</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="Escala"><a href="<%=request.getContextPath()%>/m/Escala"> Escala</a></li>
<%-- 						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="TipoEscala"><a href="<%=request.getContextPath()%>/m/TipoEscala"> Tipo de Escala</a></li> --%>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="ValorDeEscala"><a href="<%=request.getContextPath()%>/m/ValorDeEscala"> Valor de Escala</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="UnidadeDeMedida"><a href="<%=request.getContextPath()%>/m/UnidadeDeMedida"> Unidade de Medida</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="Medida"><a href="<%=request.getContextPath()%>/m/Medida"> Medida</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="ProcedimentoDeMedicao"><a href="<%=request.getContextPath()%>/m/ProcedimentoDeMedicao"> Procedimento de Medi��o</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="ProcedimentoDeAnaliseDeMedicao"><a href="<%=request.getContextPath()%>/m/ProcedimentoDeAnaliseDeMedicao"> Procedimento de An�lise</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="MetodoAnalitico"><a href="<%=request.getContextPath()%>/m/MetodoAnalitico"> M�todo Anal�tico</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="DefinicaoOperacionalDeMedida"><a href="<%=request.getContextPath()%>/m/DefinicaoOperacionalDeMedida"> Defini��o Operacional de Medida</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="Periodicidade"><a href="<%=request.getContextPath()%>/m/Periodicidade"> Periodicidade</a></li>
<%-- 						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/medicao.png"}' id="TipoMedida"><a href="<%=request.getContextPath()%>/m/TipoMedida"> Tipo de Medida</a></li> --%>
						
						
						
						
						
					</ul>
				</li>
			</ul>
		</li>
		<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/planejamento.png"}' class="jstree-close">Planejar a Medi��o
			<ul>
				<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/planejamento.png"}' id="PlanoDeMedicaoDaOrganizacao"><a href="<%=request.getContextPath()%>/m/PlanoDeMedicaoDaOrganizacao"> Elaborar Plano de Medi��o da Organiza��o</a></li>
				<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/planejamento.png"}' id="PlanoDeMedicaoDoProjeto"><a href="<%=request.getContextPath()%>/m/PlanoDeMedicaoDoProjeto"> Elaborar Plano de Medi��o do Projeto</a></li>
			</ul>
		</li>
		<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/executar-medicoes.png"}' class="jstree-close">Executar Medi��es
			<ul>
				<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/executar-medicoes.png"}' id="Medicao"><a href="<%=request.getContextPath()%>/m/Medicao"> Registrar Medi��o</a></li>
			</ul>
		</li>
		<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/analisar-medicoes.png"}' class="jstree-close">Analisar Medi��es
			<ul>
				<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/analisar-medicoes.png"}' id="AnaliseDeMedicao"><a href="<%=request.getContextPath()%>/m/AnaliseDeMedicao">Realizar An�lise Tradicional de Medi��es</a></li>
			</ul>
			<ul>
				<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/analisar-medicoes.png"}' class="jstree-close">Analisar Processos
					<ul>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/analisar-medicoes.png"}' id="DesempenhoDeProcessoEspecificado"><a href="<%=request.getContextPath()%>/m/DesempenhoDeProcessoEspecificado"> Registrar Desempenho Especificado para Processo</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/analisar-medicoes.png"}' id="AnaliseDeComportamentoDeProcesso"><a href="<%=request.getContextPath()%>/m/AnaliseDeComportamentoDeProcesso"> Analisar Comportamento de Processo</a></li>
						<li data-jstree='{"icon":"<%=request.getContextPath()%>/naviox/images/analisar-medicoes.png"}' id="ModeloDeDesempenhoDeProcesso"><a href="<%=request.getContextPath()%>/m/ModeloDeDesempenhoDeProcesso"> Modelo de Desempenho de Processo</a></li>
					</ul>
				</li>
			</ul>
		</li>
	</ul>
</div>