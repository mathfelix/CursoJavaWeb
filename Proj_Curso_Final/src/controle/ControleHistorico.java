package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidade.Livro;
import persistencia.HistoricoDao;
import persistencia.LivroDao;


@WebServlet("/historico")
public class ControleHistorico extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LivroDao daoLivro;
	HistoricoDao daoHistorico;
	List<Livro> lista;

    public ControleHistorico() {
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
		case "/historico":
			comprasFeitas(request, response);
			break;

		default:
			break;
		}
		
	}
	
	protected void redirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("restrito/usuario/historico.jsp");
	}
	
	protected void comprasFeitas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			
			daoHistorico = new HistoricoDao();
			
			Integer cod = Integer.parseInt(request.getParameter("codigo"));
			
			request.setAttribute("listHistorico", daoHistorico.pesquisaLivros(cod));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("restrito/usuario/historico.jsp").forward(request, response);
	}

}
