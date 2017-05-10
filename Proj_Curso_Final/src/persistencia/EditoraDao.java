package persistencia;

import java.util.ArrayList;
import java.util.List;

import entidade.Editora;

public class EditoraDao extends Dao {
	
	public void inserir(Editora edt) throws Exception {
		
		abrirConexao();
		
			stmt = conn.prepareStatement("INSERT INTO tbeditora VALUES (seqeditora.nextval, ?, ?)");
			stmt.setString(1, edt.getNome());
			stmt.setString(2, edt.getOrigem());
			
			stmt.execute();
			stmt.close();
		
		fecharConexao();
	}
	
	public List<Editora> pesquisarEditoras() throws Exception {
		abrirConexao();
		
			stmt = conn.prepareStatement("SELECT * FROM tbeditora");
			rs = stmt.executeQuery();
			
			List<Editora> lista = new ArrayList<Editora>();
			while (rs.next()) {
				Editora edt = new Editora();
				edt.setCodigo(rs.getInt("CODEDITORA"));
				edt.setNome(rs.getString("NOME_EDITORA"));
				edt.setOrigem(rs.getString("ORIGEM_EDITORA"));
				
				lista.add(edt);
			}
			
			rs.close();
			stmt.close();
			
		
		fecharConexao();
		return lista;
	}
	
	public Editora pesquisarEditora(Integer cod) throws Exception{
		abrirConexao();
			stmt = conn.prepareStatement("select codeditora, nome_editora, origem_editora from tbeditora where codeditora = ?");
			stmt.setInt(1, cod);
			
			rs = stmt.executeQuery();
			
			Editora Editora = null;
			if(rs.next()){
				Editora = new Editora();
				Editora.setCodigo(rs.getInt("CODEDITORA"));
				Editora.setNome(rs.getString("NOME_EDITORA"));
				Editora.setOrigem(rs.getString("ORIGEM_EDITORA"));
				
			}
			rs.close();
			stmt.close();
			
		fecharConexao();
		return Editora;
	}
	
	public void excluir(Integer cod) throws Exception {
		abrirConexao();
		
			stmt = conn.prepareStatement("DELETE FROM tbeditora WHERE codeditora = ?");
			stmt.setInt(1, cod);
			
			stmt.execute();
			stmt.close();
		
		fecharConexao();
	}
	
	public void alterar(Editora edt) throws Exception{
		abrirConexao();
			stmt = conn.prepareStatement("update tbeditora set nome_editora = ?, origem_editora = ? where codeditora = ?");
			stmt.setString(1, edt.getNome());
			stmt.setString(2, edt.getOrigem());
			stmt.setInt(3, edt.getCodigo());
			
			stmt.execute();
			stmt.close();
		
		fecharConexao();
	}
	
}
