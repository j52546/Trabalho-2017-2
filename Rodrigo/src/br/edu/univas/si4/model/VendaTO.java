package br.edu.univas.si4.model;

import java.util.Date;

public class VendaTO {
	
	private String nomeCliente;
	private String nomeProduto;
	private float valorPedido;
	private Date dataVenda;
	private int quantidade;
	private boolean isAprovado;
	private int id;

	
	public VendaTO(String nome, String produto, float valor, int qtd, boolean res) {
			this.setNomeCliente(nome);
			this.setNomeProduto(produto);
			this.setValorPedido(valor);
			this.setQuantidade(qtd);
			this.setAprovado(res);
	}


	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	public String getNomeProduto() {
		return nomeProduto;
	}


	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}


	public float getValorPedido() {
		return valorPedido;
	}


	public void setValorPedido(float valorPedido) {
		this.valorPedido = valorPedido;
	}


	public Date getDataVenda() {
		return dataVenda;
	}


	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public boolean isAprovado() {
		return isAprovado;
	}


	public void setAprovado(boolean isAprovado) {
		this.isAprovado = isAprovado;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
}
