package controle;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import email.EmailConfig;
import entidade.Usuario;
import persistencia.UsuarioDao;
import sun.misc.BASE64Encoder;


@WebServlet({"/acessologin", "/alterasenha", "/logout_adm", "/mudarsenha"})
public class ControleAcesso extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UsuarioDao daoUsuario;
	String senha_veri = null;

    public ControleAcesso() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verificaURL(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verificaURL(request, response);

	}
	
	protected void verificaURL(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String URL = request.getServletPath();
		
		if(daoUsuario == null){
			daoUsuario = new UsuarioDao();
		}
		
		switch (URL) {
		case "/acessologin":
			login(request, response);
			break;
		
		case "/mudarsenha":
			novaSenha(request, response);
			break;
		
		case "/alterasenha":
			alteraSenha(request, response);
			break;
		
		case "/logout_adm":
			logout_ADM(request, response);
			break;
			
		default:
			break;
		}
	
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino = null;
		
		try {
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			if(senha.equals(senha_veri)){
				destino = "restrito/usuario/mudarsenha.jsp";
				
			}else{
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] crip = md.digest(senha.getBytes());
				BASE64Encoder enc = new BASE64Encoder();
				 
				
				Usuario u = daoUsuario.pesquisarUsuario(email, enc.encode(crip));

				
				if(u != null && u.getNivel().equals("USUARIO")){
					HttpSession sessao = request.getSession();
					sessao.setAttribute("usu", u);
					destino = "restrito/usuario/home.jsp";
					
				}else if(u != null && u.getNivel().equals("ADMIN")){
					
						HttpSession sessao = request.getSession();
						sessao.setAttribute("usu", u);
						destino = "restrito/admin/home.jsp";
					}else{
					
						request.setAttribute("msg", "Email e/ou senha não encontrado - Acesso Negado!");
						destino = "index.jsp";
					}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
			
		}
		request.getRequestDispatcher(destino).forward(request, response);
	}
	
	protected void novaSenha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String email = request.getParameter("email");
			
			System.out.println(email);
			
			Random rd = new Random();
			String senha_nova = email.substring(0, 3) + rd.nextInt(99999);
			
			senha_veri = senha_nova;
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] crip = md.digest(senha_nova.getBytes());
			BASE64Encoder enc = new BASE64Encoder();
			
			
			daoUsuario = new UsuarioDao();
			daoUsuario.mudarSenha(email, enc.encode(crip));
			
			
			EmailConfig.recuperaSenha(email, "Nova senha solicitada: " + senha_nova);
	
			request.setAttribute("msg", "Senha alterada. Verifique email.");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
	
	protected void alteraSenha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino = null;
		
		try {
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String confsenha = request.getParameter("confsenha");
			
			
			if(senha.equals(confsenha)){
				
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] crip = md.digest(senha.getBytes());
				BASE64Encoder enc = new BASE64Encoder();
				
				daoUsuario.mudarSenha(email, enc.encode(crip));
				
				
				Usuario u = daoUsuario.pesquisarUsuario(email, enc.encode(crip));

				
				if(u != null && u.getNivel().equals("USUARIO")){
					HttpSession sessao = request.getSession();
					sessao.setAttribute("usu", u);
					destino = "restrito/usuario/home.jsp";
					
				}else if(u != null && u.getNivel().equals("ADMIN")){
					
						HttpSession sessao = request.getSession();
						sessao.setAttribute("usu", u);
						destino = "restrito/admin/home.jsp";
					}else{
					
						request.setAttribute("msg", "Email e/ou senha não encontrado - Acesso Negado!");
						destino = "index.jsp";
					}
				
			}else{
				request.setAttribute("msg", "Senhas não são iguais!");
				destino = "restrito/usuario/mudarsenha.jsp";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher(destino).forward(request, response);
		
	}
	
	protected void logout_ADM(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession();
		sessao.invalidate();
		response.sendRedirect("index.jsp");
	}
	
	protected void resgataUserHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("restrito/usuario/home.jsp").forward(request, response);
	}
	
	protected void resgataAdmHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("restrito/admin/home.jsp").forward(request, response);
	}
	
	protected void resgataMudarSenha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("restrito/usuario/mudarsenha.jsp").forward(request, response);
	}
	
}
