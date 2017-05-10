package persistencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entidade.Autor;

public class AutorDao extends Dao {

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public void inserir(Autor a) throws Exception {
		abrirConexao();
			stmt = conn.prepareStatement("INSERT INTO tbautor VALUES (SEQAUTOR.NEXTVAL, ?, ?, TO_DATE(?, 'yyyy-mm-dd'))");
			stmt.setString(1, a.getNome());
			stmt.setString(2, a.getNacionalidade());
			stmt.setString(3, df.format(a.getNascimento()));
			
			stmt.execute();
			stmt.close();
	
		fecharConexao();
	}
	
	public List<Autor> pesquisarAutores() throws Exception {
		abrirConexao();
		
			stmt = conn.prepareStatement("SELECT CODAUTOR, NOME_AUTOR, ORIGEM_AUTOR, NASCIMENTO FROM tbautor");
			rs = stmt.executeQuery();
			
			List<Autor> lista = new ArrayList<Autor>();
			while (rs.next()) {
				Autor a = new Autor();
				a.setCodigo(rs.getInt("CODAUTOR"));
				a.setNome(rs.getString("NOME_AUTOR"));
				a.setNacionalidade(rs.getString("ORIGEM_AUTOR"));
				a.setNascimento(rs.getDate("NASCIMENTO")); 
						
				lista.add(a);
			}
			
			rs.close();
			stmt.close();
			
		
		fecharConexao();
		return lista;
	}
	
	public Autor pesquisarAutor(Integer cod) throws Exception{
		abrirConexao();
			stmt = conn.prepareStatement("select codautor, nome_autor, origem_autor, nascimento from tbautor where codautor = ?");
			stmt.setInt(1, cod);
			
			rs = stmt.executeQuery();
			
			Autor autor = null;
			if(rs.next()){
				autor = new Autor();
				autor.setCodigo(rs.getInt("CODAUTOR"));
				autor.setNome(rs.getString("NOME_AUTOR"));
				autor.setNacionalidade(rs.getString("ORIGEM_AUTOR"));
				autor.setNascimento(df.parse(rs.getString("NASCIMENTO")));
				
			}
			rs.close();
			stmt.close();
			
		fecharConexao();
		return autor;
	}
	
	public void excluir(Integer cod) throws Exception {
		abrirConexao();
		
			stmt = conn.prepareStatement("DELETE FROM tbautor WHERE codautor = ?");
			stmt.setInt(1, cod);
			
			stmt.execute();
			stmt.close();
		
		fecharConexao();
	}
	
	public void alterar(Autor a) throws Exception{
		abrirConexao();
			stmt = conn.prepareStatement("update tbautor set nome_autor = ?, origem_autor = ?,"+ 
											"nascimento = TO_DATE(?, 'yyyy-mm-dd') where codautor = ?");
			stmt.setString(1, a.getNome());
			stmt.setString(2, a.getNacionalidade());
			stmt.setString(3, df.format(a.getNascimento()));
			stmt.setInt(4, a.getCodigo());
			
			stmt.execute();
			stmt.close();
		
		fecharConexao();
	}
	
	
}
