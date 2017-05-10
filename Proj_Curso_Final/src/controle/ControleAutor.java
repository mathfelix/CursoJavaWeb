package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidade.Autor;
import persistencia.AutorDao;


@WebServlet({"/autorcad", "/autorcons", "/autordel", "/autorpesq", "/autoralt"})
public class ControleAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
    public ControleAutor() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verificaUrl(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verificaUrl(request, response);
	}

	protected void verificaUrl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getServletPath();
		
		switch (url) {
		case "/autorcad":
			cadastrarAutor(request, response);
			break;
		
		case "/autorcons":
			consultarAutores(request, response);
			break;
		
		case "/autordel":
			deletarAutor(request, response);
			break;
		
		case "/autorpesq":
			pesqAutor(request, response);
			break;
	
		case "/autoralt":
			alterarAutor(request, response);
			break;

		default:
			break;
		}
		
		
	}
	
	protected void cadastrarAutor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Autor a = new Autor();
			a.setCodigo(null);
			a.setNacionalidade(request.getParameter("nacionalidade"));
			a.setNome(request.getParameter("nome"));
			a.setNascimento(df.parse(request.getParameter("nascimento")));
			
			AutorDao daoAutor = new AutorDao();
			daoAutor.inserir(a);
			
			request.setAttribute("msg", "Autor cadastrado com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("autorcons").forward(request, response);
	}
	
	protected void consultarAutores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			AutorDao daoAutor = new AutorDao();
			request.setAttribute("listaAutores", daoAutor.pesquisarAutores());
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro na pesquisa de autores: "+e.getMessage());
		}
		request.getRequestDispatcher("restrito/admin/cadastroautor.jsp").forward(request, response);
	}
	
	protected void deletarAutor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			AutorDao daoAutor = new AutorDao();
			
			Integer cod = Integer.parseInt(request.getParameter("cod"));
			daoAutor.excluir(cod);
			request.setAttribute("msg", "Autor excluído.");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("autorcons").forward(request, response);
	}
	
	protected void pesqAutor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer cod = Integer.parseInt(request.getParameter("cod"));
			AutorDao daoAutor = new AutorDao();
			
			Autor aut = new Autor();
			aut = daoAutor.pesquisarAutor(cod);
			
			request.setAttribute("autor", aut);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("restrito/admin/editarautor.jsp").forward(request, response);
	}
	
	protected void alterarAutor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Autor a = new Autor();
			a.setCodigo(Integer.parseInt(request.getParameter("codigo")));
			a.setNome(request.getParameter("nome"));
			a.setNacionalidade(request.getParameter("nacionalidade"));
			a.setNascimento(df.parse(request.getParameter("nascimento")));
			
			AutorDao daoAutor = new AutorDao();
			daoAutor.alterar(a);
			
			request.setAttribute("msg", "Autor alterado.");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("autorcons").forward(request, response);
	}
}
