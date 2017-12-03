package br.edu.univas.si4.model;



	import java.util.Date;

	public class VendaTO {
		
		private String nomeCliente;
		private String nomeProduto;
		private float valorPedido;
		private Date dataVenda;
		private int quantidade;

		
		public VendaTO(String nome, String produto, float valor, int qtd) {
				this.setNomeCliente(nome);
				this.setNomeProduto(produto);
				this.setValorPedido(valor);
				this.setQuantidade(qtd);
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
		
		
	
}
