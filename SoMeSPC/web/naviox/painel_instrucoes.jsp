
<div id="painel3">

	<div class="panel panel-primary" style="margin-top: 20px;">

		<div class="panel-heading">
			<h1 class="panel-title text-center">
				<b>Instru��es</b>				
			</h1>
		</div>

		<div class="row" style="margin: 20px;">
		
			<div class="col-md-6" style="text-align: justify;">
				<label id="jobs">Agendamento de Medi��es</label>
				</br>
				<p>
					Na aba <i>Agendamento de Medi��es</i> s�o exibidos os <i>jobs de medi��o</i> criados automaticamente a partir dos Planos de Medi��o definidos. 
					<i>Um job de medi��o</i> � o agendamento de uma medi��o autom�tica que coleta dados do Taiga ou do SonarQube. 
					Os <i>jobs</i> s�o executados automaticamente de acordo com as medidas definidas no Plano de Medi��o e a periodicidade de medi��o para elas estabelecida.
				</p>	
				
				<p>Para cada <i>job</i> s�o exibidas as seguintes informa��es: Nome, �ltima Execu��o, Pr�xima Execu��o e Situa��o (Executando, Ativo, Pausado, Bloqueado, Completo e Erro).</p>

				<p>Selecionando-se os bot�es dispon�veis na coluna <i>Controles</i> � poss�vel executar, iniciar, pausar e excluir <i>jobs</i>.</p>
			</div>
			<div class="col-md-6" style="text-align: justify;">
				<label id="medi��o">An�lise de Medi��es</label>
				</br>
				<p> 
					Na aba <i>An�lise de Medi��es</i> � poss�vel visualizar medi��es realizadas. 
					Para selecionar os dados a serem apresentados, deve-se selecionar o objetivo de medi��o e a medida a serem considerados (campo <i>Selecione Objetivo de Medi��o e Medida</i>), 
					as entidades mensur�veis (campo <i>Selecione a Entidade Mensur�vel*</i>), as datas de in�cio e fim das medi��es (campos <i>Data In�cio e Data Fim</i>) 
					ou a quantidade de medi��es que se deseja visualizar (ser�o apresentadas as <i>n</i> �ltimas medi��es realizadas, sendo <i>n</i> a quantidade de medi��es selecionada**). 
				</p>
				<p>
					Ap�s a sele��o, deve-se clicar no bot�o <i>Atualizar</i> para que o gr�fico contendo os dados selecionados seja apresentado.
				</p>
				<p>
					*As entidades mensur�veis s�o apresentadas em ordem alfab�tica, agrupadas por tipo de entidade.
				</p>
				<p>
					** Para visualizar mais medi��es do que as exibidas no gr�fico pode-se navegar no componente de pagina��o exibido abaixo do gr�fico.
				</p>
			</div>
		</div>

	</div>

</div>

<link rel='stylesheet' href='<%=request.getContextPath()%>/naviox/style/painel_medicoes.css' />