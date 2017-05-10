package entidade;


public class Livro {

	private Integer codigo;
	private String titulo;
	private Integer ano;
	private String categoria;
	private Double preco;
	
	private Autor autor;
	private Editora editora;
	
	public Livro(Integer codigo, String titulo, Integer ano, String categoria, Double preco) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.ano = ano;
		this.categoria = categoria;
		this.preco = preco;
	}

	public Livro() {
		// TODO Auto-generated constructor stub
	}
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Livro other = (Livro) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Livro: codigo=" + codigo + ", titulo=" + titulo + ", ano=" + ano + ", categoria=" + categoria
				+ ", preco=" + preco + ", autor=" + autor.getNome() + ", editora=" + editora.getNome() + "";
	}
	
	
	
}
