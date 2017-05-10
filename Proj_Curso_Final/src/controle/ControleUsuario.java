package controle;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidade.Usuario;
import persistencia.UsuarioDao;
import sun.misc.BASE64Encoder;

@WebServlet({"/usuariocontrolcad", "/editconta", "/salveditusuario"})
public class ControleUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDao daoUsuario;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
    public ControleUsuario() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verifica(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verifica(request, response);
	}
	
	protected void verifica(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getServletPath();
		
		if(daoUsuario == null){
			daoUsuario = new UsuarioDao();
		}
		
		switch (url) {
		case "/usuariocontrolcad":
			cadastrar(request, response);
			break;
		
		case "/editconta":
			resgataUsuario(request, response);
			break;
			
		case "/salveditusuario":
			alterarUsuario(request, response);
			break;

		default:
			break;
		}
		
		
	}
	
	protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			
			String senha = request.getParameter("senha");
			String confsenha = request.getParameter("confsenha");
			
			if(!senha.equals(confsenha)){
				request.setAttribute("msg", "Erro! Senhas informadas são diferentes.");
				request.getRequestDispatcher("cadastro.jsp").forward(request, response);
			}
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] crip = md.digest(senha.getBytes());
			BASE64Encoder enc = new BASE64Encoder();
			
			
			Usuario u = new Usuario();
			u.setCodigo(null);
			u.setNome(request.getParameter("nome"));
			u.setGenero(request.getParameter("genero"));
			
			u.setNascimento(df.parse(request.getParameter("nascimento")));
			
			u.setEndereco(request.getParameter("endereco"));
			u.setEmail(request.getParameter("email"));
			u.setSenha(enc.encode(crip));
			u.setNivel("USUARIO");
			
			daoUsuario.inserir(u);
			request.setAttribute("msg", "Usuario Cadastrado.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("cadastro.jsp").forward(request, response);
	}
	
	
	protected void resgataUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("restrito/usuario/editarconta.jsp").forward(request, response);
	}
	
	protected void alterarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Usuario u = new Usuario();
			u.setCodigo(Integer.parseInt(request.getParameter("cod")));
			u.setNome(request.getParameter("nome"));
			u.setGenero(request.getParameter("genero"));
			u.setNascimento(df.parse(request.getParameter("nascimento")));
			u.setEndereco(request.getParameter("endereco"));
			u.setEmail(request.getParameter("email"));
			
			daoUsuario = new UsuarioDao();
			daoUsuario.alterar(u);
			
			request.setAttribute("msg", u.getNome()+" seus dados foram alterados com sucesso! Relogue para efetuar as modificações.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("editconta").forward(request, response);
	}
}
