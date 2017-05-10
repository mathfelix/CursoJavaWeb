package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import email.EmailConfig;
import entidade.Livro;
import entidade.Pedido;
import persistencia.LivroDao;
import persistencia.PedidoDao;



@WebServlet({"/ofertas", "/guardalivro", "/carrinho", "/logout", "/excluir", "/finalizar"})
public class ControleCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PedidoDao daoPedido;
	private LivroDao daoLivro;
	private List<Livro> listaCarrinho = new ArrayList<Livro>();

	
    public ControleCompra() {
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
		case "/ofertas":
			ofertas(request, response);
			break;

		case "/guardalivro":
			guardaLivro(request, response);
			break;
		
		case "/carrinho":
			carrinho(request, response);
			break;
		
		case "/logout":
			logout(request, response);
			break;
			
		case "/excluir":
			excluirDoCarrinho(request, response);
			break;

		case "/finalizar":
			finalizarPedido(request, response);
			break;
			
		default:
			break;
		}
		
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listaCarrinho.clear();
		HttpSession sessao = request.getSession();
		sessao.invalidate();
		response.sendRedirect("index.jsp");
	}
	
	protected void ofertas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			daoLivro = new LivroDao();
			request.setAttribute("listaLivros", daoLivro.pesquisarLivros());
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());;
		}
		request.getRequestDispatcher("restrito/usuario/ofertas.jsp").forward(request, response);
	}
	
	protected void carrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			request.setAttribute("livrosCarrinho", listaCarrinho);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		
		request.getRequestDispatcher("/restrito/usuario/carrinho.jsp").forward(request, response);
	}
	
	protected void guardaLivro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Integer codigo = Integer.parseInt(request.getParameter("cod"));
			
			daoLivro = new LivroDao();

			listaCarrinho.add(daoLivro.pesquisarLivro(codigo));
			
			request.setAttribute("msg", "Produto adicionado a conta.");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("carrinho").forward(request, response);
	}
	
	protected void excluirDoCarrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Integer cod = Integer.parseInt(request.getParameter("codigo"));

			Livro liv = new Livro();
			liv = daoLivro.pesquisarLivro(cod);
			
			listaCarrinho.remove(liv);
			request.setAttribute("msg", "Produto Removido!");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("carrinho").forward(request, response);
	}
	
	protected void finalizarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String mensagem = null;
			
			
			Integer cod = Integer.parseInt(request.getParameter("codigo"));
			String email = request.getParameter("email");
			
			Pedido p = new Pedido();
			p.setCod_usu(cod);
			
			if(listaCarrinho.size() == 1){
				Livro liv = new Livro();
				liv = listaCarrinho.get(0);
				
				Integer cod2 = liv.getCodigo();
				p.setCod_liv(cod2);
				
				daoPedido = new PedidoDao();
				daoPedido.inserir(p);
				
				String msg_email = listaCarrinho.toString();
				
				EmailConfig.confirmaCompra(email,"Olá! Seu produto foi comprado!\n"+ msg_email);
				
				listaCarrinho.clear();
				mensagem = "Produto comprado! Cheque seu Email.";
				
				
			}else{
				if(listaCarrinho.size() > 1){
					Livro livro = null;
					daoPedido = new PedidoDao();
					
					for (int i = 0; i < listaCarrinho.size(); i++) {
						livro = new Livro();
						livro = listaCarrinho.get(i);
						
						Integer cod2 = livro.getCodigo();
						
						p.setCod_liv(cod2);
						daoPedido.inserir(p);
					}
					
					String msg_email = listaCarrinho.toString();
					
					EmailConfig.confirmaCompra(email,"Olá! Seus produtos foram comprados!\n"+ msg_email);
					
					listaCarrinho.clear();
					mensagem = "Produtos comprados! Cheque seu Email.";
				
				}else{
					listaCarrinho.clear();
					mensagem = "Carrinho vazio!";
				}
				
			}
			
			request.setAttribute("msg", mensagem);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erro: "+e.getMessage());
		}
		request.getRequestDispatcher("carrinho").forward(request, response);
		
	}

	@Override
	public String toString() {
		return "ControleCompra [listaCarrinho=" + listaCarrinho + "]";
	}

	
	
}
