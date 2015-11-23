
<div id="painel3">

	<div class="panel panel-primary" style="margin-top: 20px;">

		<div class="panel-heading">
			<h1 class="panel-title text-center">
				<b>Instru��es</b>
			</h1>
		</div>

		<div class="row" style="margin: 20px;">
		
			<div class="col-md-6" style="text-align: justify;">
				<label id="jobs">O que s�o jobs de medi��o?</label>
				</br>
				<p> O job de medi��o � um agendamento de execu��o de uma medi��o para determinada ferramenta, Taiga ou SonarQube.</p>
				<p> As informa��es que ele possui s�o: <i>Nome, Ultima Execu��o, Pr�x. Execu��o</i> e <i>Situa��o (Executando, Ativo, Pausado, Bloqueado, Completo e Erro)</i>. </p>
				<p> Os jobs s�o executados de acordo com a periodicidade selecionado durante a cria��o do plano de medi��o.</p>
				<p> Atrav�s da aba <b>Agendamento de Medi��es</b> � poss�vel executar, iniciar, pausar e excluir o Job escolhido.</p>
			</div>
			<div class="col-md-6">
				<label id="medi��o">Como visualizar as medi��es?</label>
				</br>
				<p> As medi��es s�o exibidas atrav�s de um gr�fico de linhas. Para visualizar as medi��es, escolha a aba <b>An�lise de Medi��es</b> e siga os seguintes passos: </p>
				<p>
					<ul>
						<li>Selecione a(s) entidade(s) mensur�vel(is). O nome da entidade segue o padr�o: Nome da Entidade Mensur�vel (Nome do Projeto) [Tipo de Entidade Mensur�vel];</li>
						<br/>
						<li>Selecione a medida (s�o listadas apenas as medidas que possuem medi��es para a(s) entidade(s) selecionada(s));</li>
						<br/>
						<li>Selecione a data de in�cio e data de fim das medi��es (usando o bot�o de calend�rio);</li>
						<br/>
						<li>Selecione a quantidade de medi��es;</li>
						<br/>
						<li>O gr�fico para an�lise das medi��es ser� exibido abaixo.</li>					
					</ul>	  
				</p>
			</div>
		</div>

	</div>

</div>

<link rel='stylesheet' href='<%=request.getContextPath()%>/naviox/style/painel_medicoes.css' />