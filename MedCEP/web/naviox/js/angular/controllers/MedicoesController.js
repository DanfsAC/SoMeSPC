/**
 * Controlador da aba de Medições do Painel de Controle.
 */
app.controller('MedicoesController', function($scope, MedicaoService) {
	
	carregarProjetos();
	inicializar();

	function carregarProjetos() {
		MedicaoService.obterProjetos().then(function(projetos) {
			$scope.projetos = projetos;
		});
	}
	
	function inicializar(){
		$scope.paginaAtual = 1;
		$scope.numPerPage = 5;
		$scope.dados = [[0,0,0,0,0]];
		$scope.labels = ["0","0","0","0","0"];	
		$scope.series = [''];
		$scope.colours = [ {
			 	fillColor : "#d5e3ee",
			 	strokeColor : "#3073AD",
			 	pointColor : "#3073AD",
			 	pointStrokeColor : "#fff",
			 },
			 {
				fillColor : "#9ead8c",
				strokeColor : "#1e2d0c",
				pointColor : "#1e2d0c",
				pointStrokeColor : "#fff",
			 }]
	}
	
	$scope.configurarPaginator = function configurarPaginator(numPerPage)	{
		MedicaoService.obterTotalMedicoes($scope.projetoSelecionado.id, $scope.medidaSelecionada.id).then(function (total) {
			 $scope.totalItems = total;
			 $scope.numPerPage = numPerPage;
		});
	}

	$scope.obterMedidas = function obterMedidas() {
		inicializar();
		
		MedicaoService.obterMedidas($scope.projetoSelecionado.id).then(
				function(medidas) {
					$scope.medidas = medidas;
				});
	}
	
	$scope.obterMedicoes = function (paginaAtual) {				
		MedicaoService.obterMedicoes($scope.projetoSelecionado.id, $scope.medidaSelecionada.id, paginaAtual, $scope.numPerPage).then(function(valores) {			
			var dados = new Array();
			var labels = new Array();
			
			for (var medicao of valores){
				dados.push(medicao.valor_medido);
				labels.push(medicao.data)
			};
				
			$scope.paginaAtual = paginaAtual;
			$scope.dados = [dados];
			$scope.labels = labels;			
			$scope.series = [$scope.projetoSelecionado.nome + ' / ' + $scope.medidaSelecionada.nome];
			
			$scope.configurarPaginator($scope.numPerPage);
		});
	}
	
});