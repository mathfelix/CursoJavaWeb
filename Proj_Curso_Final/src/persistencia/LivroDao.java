package persistencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entidade.Autor;
import entidade.Editora;
import entidade.Livro;

public class LivroDao extends Dao {

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public void inserir(Livro l) throws Exception{
		
		abrirConexao();
		
			stmt = conn.prepareStatement("INSERT INTO tblivro VALUES (seqlivro.nextval, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, l.getTitulo());
			stmt.setInt(2, l.getAno());
			stmt.setString(3, l.getCategoria());
			stmt.setDouble(4, l.getPreco());
			stmt.setInt(5, l.getAutor().getCodigo());
			stmt.setInt(6, l.getEditora().getCodigo());
			
			stmt.execute();
			stmt.close();
		
		fecharConexao();
	}
	
	public List<Livro> pesquisarLivros() throws Exception {
		
		abrirConexao();
			stmt = conn.prepareStatement("select l.*, a.*, e.* from tblivro l inner join tbautor a on l.cod_autor = a.codautor inner join tbeditora e on e.codeditora = l.cod_editora");
			rs = stmt.executeQuery();
			
			List<Livro> lista = new ArrayList<Livro>();
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
				
				aut = new Autor();
				aut.setCodigo(rs.getInt("COD_AUTOR"));
				aut.setNome(rs.getString("NOME_AUTOR"));
				aut.setNacionalidade(rs.getString("ORIGEM_AUTOR"));
				aut.setNascimento(rs.getDate("NASCIMENTO"));
				
				edt = new Editora();
				edt.setCodigo(rs.getInt("COD_EDITORA"));
				edt.setNome(rs.getString("NOME_EDITORA"));
				edt.setOrigem(rs.getString("ORIGEM_EDITORA"));
				
				liv.setAutor(aut);
				liv.setEditora(edt);
				
				lista.add(liv);
				
			}
			rs.close();
			stmt.close();
			
		fecharConexao();
		return lista;
	}
	
	public Livro pesquisarLivro(Integer cod) throws Exception{
		abrirConexao();
			stmt = conn.prepareStatement("select l.*, a.*, e.* from tblivro l inner join tbautor a on l.cod_autor = a.codautor inner join tbeditora e on e.codeditora = l.cod_editora where codlivro = ?");
			stmt.setInt(1, cod);
			
			rs = stmt.executeQuery();
			
			Livro liv = null;
			Autor aut = null;
			Editora edt = null;
			if(rs.next()){
				liv = new Livro();
				liv.setCodigo(rs.getInt("CODLIVRO"));
				liv.setTitulo(rs.getString("TITULO"));
				liv.setAno(rs.getInt("ANO"));
				liv.setCategoria(rs.getString("CATEGORIA"));
				liv.setPreco(rs.getDouble("PRECO"));
				
				aut = new Autor();
				aut.setCodigo(rs.getInt("COD_AUTOR"));
				aut.setNome(rs.getString("NOME_AUTOR"));
				aut.setNacionalidade(rs.getString("ORIGEM_AUTOR"));
				aut.setNascimento(rs.getDate("NASCIMENTO"));
				
				edt = new Editora();
				edt.setCodigo(rs.getInt("COD_EDITORA"));
				edt.setNome(rs.getString("NOME_EDITORA"));
				edt.setOrigem(rs.getString("ORIGEM_EDITORA"));
				
				liv.setAutor(aut);
				liv.setEditora(edt);
				
			}
		
		fecharConexao();
		return liv;
	}
	
	public void excluir(Integer cod) throws Exception {
		abrirConexao();
			stmt = conn.prepareStatement("delete from tblivro where codlivro = ?");
			stmt.setInt(1, cod);
			
			stmt.execute();
			stmt.close();
			
		fecharConexao();
	}
	
	public void alterar(Livro l) throws Exception{
		abrirConexao();
			stmt = conn.prepareStatement("update tblivro set titulo = ?, ano = ?, categoria = ?, preco = ?, cod_editora = ?, cod_autor = ? where codlivro = ?");
			stmt.setString(1, l.getTitulo());
			stmt.setInt(2, l.getAno());
			stmt.setString(3, l.getCategoria());
			stmt.setDouble(4, l.getPreco());
			stmt.setInt(5, l.getEditora().getCodigo());
			stmt.setInt(6, l.getAutor().getCodigo());
			stmt.setInt(7, l.getCodigo());
			
			stmt.execute();
			stmt.close();
			
		fecharConexao();
	}

}
