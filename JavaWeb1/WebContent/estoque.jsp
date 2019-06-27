<%@page import="org.senai.ctrlEstoque.modelo.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<table class="table table-striped">
		<thead>
			<tr>
				<th width="10%">Codigo</th>
				<th width="30%">Nome do Item</th>
				<th width="20%">Fornecedor</th>
				<th width="10%">Prazo de validade</th>
				<th width="10%">Quantidade</th>
				<th width="10%">Valor de compra</th>
				<th width="15%">Valor de Venda</th>
			</tr>
		</thead>
		<tbody>
			<script>
				function prepararEditar(cod){
					//alert("editar" +cod);
					window.location.replace('gerenciarItem.jsp?cod='+cod);
				}	
			
			</script>
			
			<% 
			Item item = new Item();
			for (Item p : item.getLista()) {
				out.print("<tr onclick='prepararEditar("+p.getCod()+")'>");
				out.print("<td>" + p.getCod()+"</td>");
				out.print("<td>"+p.getNomeItem()+ "</td>");
				out.print("<td class='"+p.getCorFornecedor()+ "'>"+ p.getFornecedor() + "</td>");
				out.print("<td>"+p.getValidade()+ "</td>");
				out.print("<td>"+p.getQuantidade()+ "</td>");
				out.print("<td>"+p.getCompra()+ "</td>");
				out.print("<td>"+p.getVenda()+ "</td>");
				out.print("</tr>");
			}
			
			%>
		</tbody>
	</table>

</body>
</html>