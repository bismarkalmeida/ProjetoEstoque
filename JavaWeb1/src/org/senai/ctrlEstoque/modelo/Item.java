package org.senai.ctrlEstoque.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Item {
	private int cod;
	private String nomeItem;
	private String fornecedor;
	private String validade;
	private String quantidade;
	private String compra;
	private String venda;

	public Item() {
		setCod(0);
		setNomeItem("");
		setFornecedor("");
		setValidade("");
		setQuantidade("");
		setCompra("");
		setVenda("");
	}

	public int getCod() {
		return cod;
	}

	public String getNomeItem() {
		return nomeItem;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getCompra() {
		return compra;
	}

	public void setCompra(String compra) {
		this.compra = compra;
	}

	public String getVenda() {
		return venda;
	}

	public void setVenda(String venda) {
		this.venda = venda;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public boolean apagar() {

		Connection conexao = new ConectarJDBC().getConectar();

		if (conexao != null) {
			String sql = "delete from item where cod = ?";
			try {
				PreparedStatement prepararSQL = conexao.prepareStatement(sql);
				prepararSQL.setInt(1, cod);
				prepararSQL.execute();
				prepararSQL.close();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	public boolean atualizar() {

		Connection conexao = new ConectarJDBC().getConectar();

		if (conexao != null) {
			String sql = "update into item ( nome_item = ?, fornecedor = ?, validade = ?, quantidade = ?, compra = ?, venda = ? ) where cod = ?";

			try {
				PreparedStatement prepararSQL = conexao.prepareStatement(sql);
				prepararSQL.setString(1, nomeItem);
				prepararSQL.setString(2, fornecedor);
				prepararSQL.setString(3, quantidade);
				prepararSQL.setString(4, validade);
				prepararSQL.setString(5, venda);
				prepararSQL.setString(6, compra);
				prepararSQL.setInt(7, cod);

				prepararSQL.execute();
				prepararSQL.close();

				return true;

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

		}
		return false;
	}

	public boolean inserir() {

		Connection conexao = new ConectarJDBC().getConectar();

		if (conexao != null) {
			String sql = "insert into item ( nome_item , fornecedor, validade, quantidade, compra, venda ) values (?, ?, ?, ?, ?, ?)";

			try {
				PreparedStatement prepararSQL = conexao.prepareStatement(sql);
				prepararSQL.setString(1, nomeItem);
				prepararSQL.setString(2, fornecedor);
				prepararSQL.setString(3, validade);
				prepararSQL.setString(4, quantidade);
				prepararSQL.setString(5, compra);
				prepararSQL.setString(6, venda);

				prepararSQL.execute();
				prepararSQL.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public List<Item> getLista() {
		try {
			Connection conexao = new ConectarJDBC().getConectar();
			PreparedStatement ps = conexao.prepareStatement("select * from item");
			ResultSet rs = ps.executeQuery();

			List<Item> lsItem = new ArrayList<Item>();

			while (rs.next()) {
				Item p = new Item();
				// oq esta entre aspas "nome_da_coluna_no_banco"
				p.setCod(rs.getInt("cod"));
				p.setNomeItem(rs.getString("nome_item"));
				p.setFornecedor(rs.getString("fornecedor"));
				p.setValidade(rs.getString("validade"));
				p.setQuantidade(rs.getString("quantidade"));
				p.setCompra(rs.getString("compra"));
				p.setVenda(rs.getString("venda"));
				lsItem.add(p);

			}

			ps.close();
			conexao.close();
			return lsItem;
		} catch (Exception e) {

		}
		return null;
	}

	public Item getItem(int cod) {
		try {
			Connection conexao = new ConectarJDBC().getConectar();
			PreparedStatement ps = conexao.prepareStatement("select * from item where cod = ? ");
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();

			Item p = new Item();
			while (rs.next()) {
				// oq esta entre aspas "nome_da_coluna_no_banco"
				p.setCod(rs.getInt("cod"));
				p.setNomeItem(rs.getString("nome_item"));
				p.setFornecedor(rs.getString("fornecedor"));
				p.setValidade(rs.getString("validade"));
				p.setQuantidade(rs.getString("quantidade"));
				p.setCompra(rs.getString("compra"));
				p.setVenda(rs.getString("venda"));
			}
			ps.close();
			conexao.close();
			return p;

		} catch (Exception e) {

		}
		return null;
	}

	public String getCorFornecedor() {
		if (fornecedor.equals("Sadia")) {
			return "table-warning";
		}
		if (fornecedor.equals("Perdigão")) {
			return "table-danger";
		}
		if (fornecedor.equals("Copacol")) {
			return "table-primary";
		}
		return "";
	}

}
