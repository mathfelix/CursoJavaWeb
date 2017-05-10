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
import entidade.Editora;
import entidade.Livro;
import persistencia.AutorDao;
import persistencia.EditoraDao;
import persistencia.LivroDao;


@WebServlet({"/livrocad", "/livrocons", "/livrodel", "/livropesq", "/livroalt"})
public class ControleLivro extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	private EditoraDao daoEditora;
	private LivroDao daoLivro;
	
    public ControleLivro() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verificaURL(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verificaURL(request, response);
	}
	
	protected void verificaURL(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getServletPath();
		
		switch (url) {
		
		case "/livrocad":
			cadastrarLivro(request, response);
			break;
		
		case "/livrocons":
			consultarLivros(request, response);
			break;
		
		case "/livrodel":
			deletarLivro(request, response);
			break;
		
		case "/livropesq":
			pesqLivro(request, response);
			break;
		
		case "/livroalt":
			alterarLivro(request, response);
			break;
		
			
		default:
			break;
		}
		
	}
	

	protected void cadastrarLivro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Livro liv = new Livro(null, request.getParameter("titulo"), Integer.parseInt(request.getParameter("ano")),
					request.getParameter("categoria"), Double.parseDouble(request.getParameter("preco")));
			
			
			Autor aut = new Autor();
			aut.setCodigo(Integer.parseInt(request.getParameter("autor")));
			
			
			Editora edt = new Editora();
			edt.setCodigo(Integer.parseInt(request.getParameter("editora")));
			
			liv.setAutor(aut);
			liv.setEditora(edt);
			
			daoLivro = new LivroDao();
			daoLivro.inserir(liv);
			
			request.setAttribute("msg", "Livro cadastrado com sucesso!");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("livrocons").forward(request, response);
	}
	
	protected void consultarLivros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AutorDao daoAutor = new AutorDao();
			request.setAttribute("Autores", daoAutor.pesquisarAutores());
			
			daoEditora = new EditoraDao();
			request.setAttribute("Editoras", daoEditora.pesquisarEditoras());
			
			daoLivro = new LivroDao();
			request.setAttribute("listaLivros", daoLivro.pesquisarLivros());
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());;
		}
		request.getRequestDispatcher("restrito/admin/cadastrolivro.jsp").forward(request, response);
	}
	
	protected void deletarLivro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			daoLivro.excluir(Integer.parseInt(request.getParameter("cod")));
			request.setAttribute("msg", "Livro excluido.");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("livrocons").forward(request, response);
	}
	
	protected void pesqLivro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer cod = Integer.parseInt(request.getParameter("cod"));
			daoLivro = new LivroDao();
			
			AutorDao daoAutor = new AutorDao();
			request.setAttribute("Autores", daoAutor.pesquisarAutores());
			
			daoEditora = new EditoraDao();
			request.setAttribute("Editoras", daoEditora.pesquisarEditoras());
			
			Livro liv = daoLivro.pesquisarLivro(cod);
			request.setAttribute("livro", liv);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("restrito/admin/editarlivro.jsp").forward(request, response);
	}
	
	protected void alterarLivro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Livro liv = new Livro();
			liv.setCodigo(Integer.parseInt(request.getParameter("codigo")));
			liv.setTitulo(request.getParameter("titulo"));
			liv.setAno(Integer.parseInt(request.getParameter("ano")));
			liv.setCategoria(request.getParameter("categoria"));
			liv.setPreco(Double.parseDouble(request.getParameter("preco")));
			
			Autor aut = new Autor();
			aut.setCodigo(Integer.parseInt(request.getParameter("autor")));
			
			Editora edt = new Editora();
			edt.setCodigo(Integer.parseInt(request.getParameter("editora")));
			
			liv.setAutor(aut);
			liv.setEditora(edt);
			
			daoLivro = new LivroDao();
			daoLivro.alterar(liv);
			
			request.setAttribute("msg", "Livro alterado.");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("livrocons").forward(request, response);
	}
	
	

}
