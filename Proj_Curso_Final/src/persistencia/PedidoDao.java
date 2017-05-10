package persistencia;

import entidade.Pedido;


public class PedidoDao extends Dao {

	public void inserir(Pedido p) throws Exception {
		abrirConexao();
			stmt = conn.prepareStatement("INSERT INTO TBPEDIDO VALUES (seqpedido.nextval, ?, ?)");
			stmt.setInt(1, p.getCod_usu());
			stmt.setInt(2, p.getCod_liv());
		
			stmt.execute();
			stmt.close();
			
		fecharConexao();
	}
	
	
}
