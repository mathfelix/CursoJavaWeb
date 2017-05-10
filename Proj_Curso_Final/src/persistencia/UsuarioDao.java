package persistencia;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entidade.Usuario;

public class UsuarioDao extends Dao {
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public void inserir(Usuario u) throws Exception {
		abrirConexao();
			stmt = conn
					.prepareStatement("INSERT INTO tbusuario VALUES (SEQUSUARIO.NEXTVAL,?,?, TO_DATE(?, 'yyyy-mm-dd'),?,?,?,?)"); 
			stmt.setString(1, u.getNome());
			stmt.setString(2, u.getGenero());
			
			stmt.setString(3, df.format(u.getNascimento()));
			
			stmt.setString(4, u.getEndereco());
			stmt.setString(5, u.getEmail());
			stmt.setString(6, u.getSenha());
			stmt.setString(7, u.getNivel());
			
			stmt.execute();
			stmt.close();
		
		fecharConexao();
	}
	
	public void delete(Integer cod) throws Exception {
		
		abrirConexao();
			stmt = conn.prepareStatement("DELETE FROM tbusuario WHERE codUsuario = ?");
			stmt.setInt(1, cod);
			
			stmt.execute();
			stmt.close();
			
		fecharConexao();
	}
	
	public List<Usuario> pesquisar() throws Exception {
		abrirConexao();
			stmt = conn.prepareStatement("select * from tbusuario");
			rs = stmt.executeQuery();
		
			List<Usuario> lista = new ArrayList<Usuario>();
			Usuario u = null;
			while (rs.next()) {
				u = new Usuario();
				u.setCodigo(null);
				u.setNome(rs.getString("nome"));
				u.setGenero(rs.getString("genero"));
				u.setNascimento(df.parse(rs.getString("nascimento")));
				u.setEndereco(rs.getString("endereco"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				u.setNivel(rs.getString("nivel"));
				
				lista.add(u);
			}
			stmt.close();
			rs.close();
			
		fecharConexao();
		return lista;
	}
	
	public Usuario pesquisarUsuario(String email, String senha) throws Exception{
		abrirConexao();
			stmt = conn.prepareStatement("SELECT CODUSUARIO, NOME, GENERO, NASCIMENTO, ENDERECO, EMAIL, NIVEL FROM TBUSUARIO WHERE EMAIL = ? AND SENHA = ?");
			stmt.setString(1, email);
			stmt.setString(2, senha);
			rs = stmt.executeQuery();
			
			Usuario u = null;
			while (rs.next()) {
				u = new Usuario();
				u.setCodigo(rs.getInt("CODUSUARIO"));
				u.setNome(rs.getString("NOME"));
				u.setGenero(rs.getString("GENERO"));
				u.setNascimento(rs.getDate("NASCIMENTO"));
				u.setEndereco(rs.getString("ENDERECO"));
				u.setEmail(rs.getString("EMAIL"));
				u.setNivel(rs.getString("NIVEL"));
				
			}
			stmt.close();
			rs.close();
			
		fecharConexao();
		return u;
	}
	
	public Usuario pesquisarUsuario(Integer cod) throws Exception{
		abrirConexao();
			stmt = conn.prepareStatement("SELECT codusuario, nome, genero, TO_CHAR(nascimento, 'yyyy-mm-dd'), endereco, email, nivel FROM TBUSUARIO WHERE CODUSUARIO = ?");
			stmt.setInt(1, cod);
			rs = stmt.executeQuery();
			
			Usuario u = null;
			while (rs.next()) {
				u = new Usuario();
				u.setCodigo(rs.getInt("CODUSUARIO"));
				u.setNome(rs.getString("NOME"));
				u.setGenero(rs.getString("GENERO"));
				u.setNascimento(df.parse(rs.getString("NASCIMENTO")));
				u.setEndereco(rs.getString("ENDERECO"));
				u.setEmail(rs.getString("EMAIL"));
				u.setNivel(rs.getString("NIVEL"));
				
			}
			stmt.close();
			rs.close();
			
		fecharConexao();
		return u;
	}
	
	public void mudarSenha(String email, String senha) throws Exception {
		
		abrirConexao();
		
			stmt = conn.prepareStatement("UPDATE tbusuario SET senha = ? WHERE email = ?");
			stmt.setString(1, senha);
			stmt.setString(2, email);
			
			stmt.execute();
			stmt.close();
		
		fecharConexao();
		
	}
	
	public void alterar(Usuario u) throws Exception {
		abrirConexao();
			stmt = conn.prepareStatement("update tbusuario set nome = ?, genero = ?, nascimento = TO_DATE(?, 'yyyy-mm-dd'), endereco = ?, email = ? where codusuario = ?");
			stmt.setString(1, u.getNome());
			stmt.setString(2, u.getGenero());
			stmt.setString(3, df.format(u.getNascimento()));
			stmt.setString(4, u.getEndereco());
			stmt.setString(5, u.getEmail());
			stmt.setInt(6, u.getCodigo());
			
			stmt.execute();
			stmt.close();
	
		fecharConexao();
	}
	
}


