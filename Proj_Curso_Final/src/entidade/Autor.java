package entidade;

import java.util.Date;

public class Autor {
	
	private Integer codigo;
	private String nome;
	private String nacionalidade;
	private Date nascimento;

	public Autor() {
		// TODO Auto-generated constructor stub
	}
	
	public Autor(Integer codigo, String nome, String nacionalidade, Date nascimento) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.nascimento = nascimento;
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	
	
	
}
