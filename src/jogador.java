
public class jogador {
	private String nome;
	private int pontuacao;
	
	
	public jogador(String nome, int pontuacao) {
		super();
		this.nome = nome;
		this.pontuacao = pontuacao;
	}
	public jogador() {
		super();
		this.nome = "";
		this.pontuacao = 0;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	@Override
	public String toString() {
		return nome +"\t"+ pontuacao;
	}
	
	
	
//	
}
