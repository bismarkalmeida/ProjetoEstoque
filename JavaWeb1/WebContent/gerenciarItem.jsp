<!DOCTYPE html>
<%@page import="org.senai.ctrlEstoque.modelo.Item"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<head>
<meta charset="UTF-8">
<title>Gerenciar Painel</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />

</head>
<body>

	<script type="text/javascript">
		function dadosForm() {
			var dados = "";
			dados += "cod="+document.getElementById("cod").value;
			dados += "&nomeItem=" + document.getElementById("nomeItem").value;
			dados += "&fornecedor="+ document.getElementById("fornecedor").value;
			dados += "&validade=" + document.getElementById("validade").value;
			dados += "&quatidade=" + document.getElementById("quatidade").value;
			dados += "&compra=" + document.getElementById("compra").value;
			dados += "&venda=" + document.getElementById("venda").value;

			return dados;
		}
		
		function apagar(){
			if(confirm ("Realmente deseja apagar esse registro?")){
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function(){
					if (this.readyState == 4 && this.status ==200){
						var msg = xhttp.responseText;
						
						if(msg =="Gravado com sucesso"){
							document.getElementById("msg").className = "alert alert-info";
							document.getElementById("msg").innerHTML = "Informação Apagada";
							document.getElementById("formulario").reset();
						} else {
							document.getElementById("msg").className = "alert alert-danger";
							document.getElementById("msg").innerHTML = "Erro ao apagar";
						}
						
					}
				
				};
				xhttp.open("GET", "servletEstoque?"+dadosForm()+"&apagar",true);
				xhttp.send();
			}
		}
		
		
		function novo(){
			window.location.replace('gerenciarItem.jsp');
			
		}

		function gravar() {

			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {

					// pega um retorno lá do servlet 
					var msg = xhttp.responseText;

					document.getElementById("msg").innerHTML = msg;
					if (msg == "Gravado com sucesso") {
						document.getElementById("msg").className = "alert alert-info";
					} else {
						document.getElementById("msg").className = "alert alert-danger";
					}

				}
			};
			alert(dadosForm());
			xhttp.open("GET", "servletEstoque?" + dadosForm(), true);
			xhttp.send();

		}
	</script>
	<%
		// meu java rolar
		Item item = new Item();
		
		if(request.getParameter("cod") != null){
			int cod = Integer.parseInt(request.getParameter("cod"));
			item = item.getItem(cod);
		}
	%>

	<div class="container">
		<h2>Itens no estoque</h2>
		<form action="/action_page.php">
			<div id="msg"></div>
			<div class="form-row">
				<div class="form-group col-md-3">
				<input type="hidden" id="cod" value="<% out.print(item.getCod()); %>" >
					<label for="nome">Nome do Item:</label> <input type="text"
					value="<% out.print(item.getNomeItem()); %>"
						class="form-control" id="nomeItem" name="nome">
				</div>

				<div class="form-group col-md-3">
					<label for="status">Fornecedor:</label> <select
						class="form-control" id="fornecedor">
						<option>Sadia</option>
						<option>Perdigão</option>
						<option>Copacol</option>
						
					</select>
					<script>
						document.getElementById("fornecedor").value = 
							"<% out.print(item.getFornecedor()); %>";
					</script>
				</div>

				<div class="form-group col-md-3">
					<label for="local">Prazo de validade:</label> <input type="text"
						class="form-control" id="validade" value="<% out.print(item.getValidade()); %>">
				</div>
				<div class="form-group col-md-3">
					<label for="local">Quantidade:</label> <input type="text"
						class="form-control" id="quatidade" value="<% out.print(item.getQuantidade()); %>">
				</div>
				<div class="form-group col-md-3">
					<label for="local">Valor de compra:</label> <input type="text"
						class="form-control" id="compra" value="<% out.print(item.getCompra()); %>">
				</div>
				<div class="form-group col-md-3">
					<label for="local">Valor de venda:</label> <input type="text"
						class="form-control" id="venda" value="<% out.print(item.getVenda()); %>">
				</div>
			</div>
			<button type="button" class="btn btn-secondary" onclick="novo()">Novo</button>
			<button type="button" class="btn btn-primary" onclick="gravar()">Gravar</button>
			<button type="button" class="btn btn-danger" onclick="apagar()">Apagar</button>
		</form>
	</div>

</body>
</html>
