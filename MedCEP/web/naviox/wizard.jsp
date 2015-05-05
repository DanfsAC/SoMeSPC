
<%
    Servlets.setCharacterEncoding(request, response);
%>

<%@include file="../xava/imports.jsp"%>

<%@page import="org.openxava.web.servlets.Servlets"%>
<%@page import="org.openxava.util.Locales"%>
<%@page import="com.openxava.naviox.web.NaviOXStyle"%>

<jsp:useBean id="context" class="org.openxava.controller.ModuleContext" scope="session" />
<jsp:useBean id="modules" class="com.openxava.naviox.Modules" scope="session" />

<%
    String oxVersion = org.openxava.controller.ModuleManager
					.getVersion();
%>
<!doctype html>
<html lang="pt-br">
<head>

<style>
input {
	width: 300px;
	height: 20px;
	vertical-align: middle;
}
</style>

<meta charset="utf-8">
<link rel="icon" href="<%=request.getContextPath()%>/naviox/images/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/naviox/images/favicon.ico" type="image/x-icon" />


<link href="style/jquery-ui.css" rel="stylesheet">
<%--
<link href="style/normalize.css" rel="stylesheet">
<link href="style/main.css" rel="stylesheet">
 --%>
<link href="style/jquery.steps.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/naviox/style/naviox.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src='<%=request.getContextPath()%>/xava/js/dwr-engine.js?ox=<%=oxVersion%>'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Modules.js?ox=<%=oxVersion%>'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Folders.js?ox=<%=oxVersion%>'></script>
<script type='text/javascript' src='js/angular/shared/angular.min.js'></script>
<script type='text/javascript' src='js/angular/shared/angular-route.min.js'></script>
<script type='text/javascript' src='js/angular/shared/angular-resource.min.js'></script>

<title>Novo Plano de Medi��o Integrado - MedCEP</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body ng-app="MedCEPWizardApp" <%=NaviOXStyle.getBodyClass(request)%> style="font-size: 12px; line-height: 15px;">

	<div id="main_navigation" style="box-sizing: content-box !important;">
		<jsp:include page="mainNavigation.jsp" />
	</div>
	<table width="100%">
		<tr>
			<td id="modules_list">
				<div id="modules_list_popup">
					<img id="modules_list_corner" src="<%=request.getContextPath()%>/naviox/images/corner.png" />
					<div id="modules_list_outbox">
						<table id="modules_list_box">
							<tr id="modules_list_content">
								<td><jsp:include page="modulesMenu.jsp" /></td>
							</tr>
						</table>
					</div>
				</div>
			</td>

			<td valign="top"><jsp:include page="menus.jsp" /></td>

			<td valign="top">
				<div class="module-wrapper">
					<div id="medcep-wizard" ng-controller="MainController">
						<h2>Conex�o</h2>
						<fieldset>
							<form name="loginForm" novalidate>
								<br /> <label for="url"><strong>URL *</strong></label> <input type="url" name="url" ng-model="login.url" required> <span style="color: red" ng-show="loginForm.url.$dirty && loginForm.url.$invalid"> <span ng-show="loginForm.url.$error.required">Digite a URL.</span> <span ng-show="loginForm.url.$error.url">Endere�o de URL inv�lido.</span>
								</span> <br /> <br /> <label for="usuario"><strong>Usu�rio *</strong></label> <input type="text" name="usuario" ng-model="login.usuario" required> <span style="color: red" ng-show="loginForm.usuario.$dirty && loginForm.usuario.$invalid"> <span ng-show="loginForm.usuario.$error.required">Digite o Nome do Usu�rio.</span>
								</span> <br /> <br /> <label for="password"><strong>Senha *</strong></label> <input type="password" name="password" ng-model="login.senha" required> <span style="color: red" ng-show="loginForm.password.$dirty && loginForm.password.$invalid"> <span ng-show="loginForm.password.$error.required">Digite a Senha.</span>
								</span> <br /> <br />
								<p>(*) Campos obrigat�rios</p>
							</form>
						</fieldset>

						<h2>Projetos</h2>
						<fieldset>
							<form name="formProjetos">
								<div id="projetos" class="row bg-wizard">
									<div class="radio">
										<label ng-repeat="projeto in projetos">
											<div class="col-md-12">
												<input type="radio" ng-model="$parent.projeto_selected" ng-value="projeto" />
												<div>
													<b>Projeto: {{projeto.nome}} ({{projeto.apelido}})</b>
												</div>
												<div>{{projeto.descricao}}</div>
											</div>
										</label>
									</div>
								</div>
							</form>
						</fieldset>

						<h2>Coleta</h2>
						<fieldset style="overflow: scroll;">
							<label for="selectPeriodicidades">Periodicidades:</label> <select class="form-control" id="selectPeriodicidades" ng-model="periodicidade_selected" ng-options="periodicidades[periodicidades.indexOf(p)].nome for p in periodicidades">
							</select> <br />
							<div class="row">
								<b>Medidas:</b> <br />
								<br />
								<div id="medidas" class="row bg-wizard" ng-repeat="medida in medidas">
									<div class="col-md-12">
										<label class="checkbox" for="{{medida}}"><input type="checkbox" ng-model="medida_selected[medida]" name="group" id="{{medida}}" /> <span style="vertical-align: middle !important; padding-top: 5px !important;"><b>{{medida}}</b></span> </label>
									</div>
								</div>
								<br />
								<br />
							</div>
						</fieldset>

						<h2>Confirma��o</h2>
						<fieldset>

							<p><b>Projeto:</b> {{projeto_selected.nome}}</p>
							<p><b>Periodicidade:</b> {{periodicidade_selected.nome}}</p>
							<p>
								<b>Medida(s):</b> <span ng-repeat="medida in medida_selected"> {{medida}} </span>|
							</p>

						</fieldset>
					</div>
				</div>
			</td>
		</tr>
	</table>

	<!-- Modules -->
	<script type="text/javascript" src="js/angular/app.js"></script>

	<!-- Controllers -->
	<script type="text/javascript" src="js/angular/controllers/MainController.js"></script>

	<!-- Services -->
	<script type="text/javascript" src="js/angular/services/TaigaIntegratorService.js"></script>

	<!-- JQuery -->
	<script type='text/javascript' src="js/jquery.js"></script>
	<script type='text/javascript' src="js/jquery-ui.js"></script>
	<script type='text/javascript' src='js/jquery.steps.js'></script>

	<!-- Bootstrap -->
	<script type='text/javascript' src='bootstrap/js/bootstrap.min.js'></script>


	<link rel='stylesheet' href='<%=request.getContextPath()%>/naviox/js/themes/default/style.min.css' />
	<script type='text/javascript' src='<%=request.getContextPath()%>/naviox/js/typewatch.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/naviox/js/naviox.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/naviox/js/jstree.min.js'></script>

	<script type="text/javascript">
		//Wizard Steps

		$("#medcep-wizard")
				.steps(
						{
							headerTag : "h2",
							bodyTag : "fieldset",
							transitionEffect : "slideLeft",
							autoFocus : true,
							labels : {
								cancel : "Cancelar",
								current : "Passo atual:",
								pagination : "Pagina��o",
								finish : "Concluir",
								next : "Pr�ximo",
								previous : "Anterior",
								loading : "Carregando ..."
							},
							onStepChanging : function(event, currentIndex,
									newIndex) {
								if (newIndex === 1) {
									e = document
											.getElementById('medcep-wizard');
									scope = angular.element(e).scope();
									scope
											.post_projeto(function resultado(
													conexao) {
												if (!conexao) {
													alert("Erro ao estabelecer a conex�o! Verifique se os dados de login est�o corretos e tente novamente.")
												}
											});

									return true;
								} else if (newIndex === 2) {
									if (scope.projeto_selected === undefined) {
										return false;
									} else {
										return true;
									}
								} else {
									return true;
								}

							},
							onFinished : function(event, currentIndex) {
								e = document.getElementById('medcep-wizard');
								scope = angular.element(e).scope();
								
								if (scope.projeto_selected === undefined)
									alert("Selecione um projeto antes de concluir!");
								else if (scope.periodicidade_selected === undefined)
									alert("Selecione uma periodicidade de coleta antes de concluir!");
								else{
									scope.post_plano();
									alert("Wizard concluido com Sucesso!");
								}
							}
						});

		$(function() {
			naviox.init();

			$('#menu_tree').jstree({
				"core" : {
					"themes" : {
						"dots" : false
					}
				},
				"plugins" : [ "state", "types" ]
			});

			var partesUrl = window.location.href.split("/");
			var moduloAtual = partesUrl[partesUrl.length - 1];

			if (moduloAtual == 'painel.jsp')
				$('#menu_tree').jstree('select_node', 'PainelControle');
			else if (moduloAtual == 'wizard.jsp')
				$('#menu_tree')
						.jstree('select_node', 'PlanoDeMedicaoIntegrado');
			else
				$('#menu_tree').jstree('select_node', '#' + moduloAtual);

			$('#menu_tree').on("changed.jstree", function(e, data) {
				var href = data.node.a_attr.href;
				window.location = href;
			});
		});
	</script>
</body>
</html>
