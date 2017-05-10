package persistencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entidade.Autor;
import entidade.Editora;
import entidade.Livro;

public class HistoricoDao extends Dao {
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public List<Livro> pesquisaLivros(Integer cod) throws Exception {
		abrirConexao();
		
			stmt = conn.prepareStatement("select l.*, a.*, e.*, p.* from tblivro l inner join tbautor a on l.cod_autor = a.codautor inner join tbeditora e on e.codeditora = l.cod_editora inner join tbpedido p on l.codlivro = p.cod_livro where cod_usuario = ?");
			stmt.setInt(1, cod);
			rs = stmt.executeQuery();
			
			List<Livro> listaComprados = new ArrayList<Livro>();
			Livro liv = null;
			Autor aut = null;
			Editora edt = null;
			
			while (rs.next()) {
				liv = new Livro();
				liv.setCodigo(rs.getInt("CODLIVRO"));
				liv.setTitulo(rs.getString("TITULO"));
				liv.setAno(rs.getInt("ANO"));
				liv.setCategoria(rs.getString("CATEGORIA"));
				liv.setPreco(rs.getDouble("PRECO"));
	
				edt = new Editora();
				edt.setCodigo(rs.getInt("COD_EDITORA"));
				edt.setNome(rs.getString("NOME_EDITORA"));
				edt.setOrigem(rs.getString("ORIGEM_EDITORA"));
				
				aut = new Autor();
				aut.setCodigo(rs.getInt("COD_AUTOR"));
				aut.setNome(rs.getString("NOME_AUTOR"));
				aut.setNacionalidade(rs.getString("ORIGEM_AUTOR"));
				aut.setNascimento(rs.getDate("NASCIMENTO"));

				liv.setAutor(aut);
				liv.setEditora(edt);
				
				listaComprados.add(liv);
			}
			rs.close();
			stmt.close();
		
		fecharConexao();
		return listaComprados;
	}
	
	
}
