package org.senai.ctrlEstoque.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.senai.ctrlEstoque.modelo.Item;

@WebServlet(name ="servletEstoque" , urlPatterns ="/servletEstoque")
public class ServletEstoque extends HttpServlet {
	
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
		String nomeItem = request.getParameter("nomeItem");
		String fornecedor = request.getParameter("fornecedor");
		String validade = request.getParameter("validade");
		String quatidade = request.getParameter("quatidade");
		String compra = request.getParameter("compra");
		String venda = request.getParameter("venda");
		System.out.println("Nome"+nomeItem +" fornecedor "+ fornecedor + " " + validade +" " + quatidade +" "+compra+" "+venda);
		
		
		// chamer método de inserir 
		// fazer o método na classe Item
		
		
		PrintWriter saida = response.getWriter();
		
		Item obj = new Item();
		
		obj.setNomeItem(nomeItem);
		obj.setFornecedor(fornecedor);
		obj.setValidade(validade);
		obj.setQuantidade(quatidade);
		obj.setCompra(compra);
		obj.setVenda(venda);
		
		int cod = Integer.parseInt(request.getParameter("cod"));
		
		boolean sucesso = false;
		if(cod > 0) {
			obj.setCod(cod);
			if(request.getParameter("apagar")==null) {
				sucesso = obj.atualizar();
			}else {
				sucesso = obj.apagar();
			}
		}else {
			sucesso = obj.inserir();
		}
		
		
		
		
		if(sucesso) {
			saida.print("Gravado com sucesso");
		}else {
			saida.print("Erro ao gravar");
		}
		
		
	}

}
