package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidade.Editora;
import persistencia.EditoraDao;


@WebServlet({"/editoracad","/editoracons", "/editoradel", "/editorapesq", "/editoraalt"})
public class ControleEditora extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private EditoraDao daoEditora;

    public ControleEditora() {
        super();
        // TODO Auto-generated constructor stub
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
		case "/editoracad":
			cadastrarEditora(request, response);
			break;
		
		case "/editoracons":
			consultarEditoras(request, response);
			break;
		
		case "/editoradel":
			deletarEditora(request, response);
			break;
		
		case "/editorapesq":
			pesqEditora(request, response);
			break;
		
		case "/editoraalt":
			alterarEditora(request, response);
			break;

		default:
			break;
		}
		
	}
	
	protected void cadastrarEditora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Editora edi = new Editora();
			edi.setCodigo(null);
			edi.setNome(request.getParameter("nome"));
			edi.setOrigem(request.getParameter("origem"));
			
			daoEditora = new EditoraDao();
			daoEditora.inserir(edi);
			
			request.setAttribute("msg", "Editora cadastrada com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("editoracons").forward(request, response);
	}
	
	protected void consultarEditoras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			daoEditora = new EditoraDao();
			request.setAttribute("listaEditoras", daoEditora.pesquisarEditoras());
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro na pesquisa de editoras: "+e.getMessage());
		}
		request.getRequestDispatcher("restrito/admin/cadastroeditora.jsp").forward(request, response);
	}
	
	protected void deletarEditora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Integer cod = Integer.parseInt(request.getParameter("cod"));
			daoEditora = new EditoraDao();
			
			daoEditora.excluir(cod);
			
			request.setAttribute("msg", "Editora excluida.");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("editoracons").forward(request, response);
	}
	
	protected void pesqEditora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer cod = Integer.parseInt(request.getParameter("cod"));
			daoEditora = new EditoraDao();
			
			Editora edt = daoEditora.pesquisarEditora(cod);
			request.setAttribute("editora", edt);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("restrito/admin/editareditora.jsp").forward(request, response);
	}
	
	protected void alterarEditora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Editora edt = new Editora();
			edt.setCodigo(Integer.parseInt(request.getParameter("codigo")));
			edt.setNome(request.getParameter("nome"));
			edt.setOrigem(request.getParameter("origem"));
			
			daoEditora = new EditoraDao();
			daoEditora.alterar(edt);
			
			request.setAttribute("msg", "Editora alterada.");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("editoracons").forward(request, response);
	}
	
}
