
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class NumeroSorteadoMain {

	static StringBuffer memoria = new StringBuffer();
	public static void main(String[] args) {
		List<jogador> jogadores = new ArrayList<>();
		char opcao=' ';
		try {
			carregarPlacar(jogadores);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Não carregado!");
		}

		do{
			opcao = JOptionPane.showInputDialog("Escolha uma opcao:\n"
					+ "1. Jogar\n"
					+ "2. Pontuacao\n"
					+ "3. Sair do programa").charAt(0);
			switch(opcao) {
			case '1':
				jogar(jogadores);
				break;
			case '2':
				placar(jogadores);
				break;
			case '3': 
				try {
					salvarPlacar(jogadores);
				} catch (IOException e) {

				}			
				JOptionPane.showMessageDialog(null,"Tchau, ate mais!");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opcaoo invalida");
			}

		}while(opcao !='3');

	}

	static int gerarNumero() {
		int aleat;
		Random aleatorio = new Random();
		aleat = aleatorio.nextInt(101);
		return aleat;
	}

	static void jogar(List<jogador> jogadores) {
		jogador jogador = new jogador();
		jogador.setNome(JOptionPane.showInputDialog("Digite o nome do jogador: "));

		int quant=10, num=0, aleat=gerarNumero();
		while(num!=aleat && quant!=0) {
			num = Integer.parseInt(JOptionPane.showInputDialog("Faltam "+ quant +" tentativas!"+"\nNúmero: "));
			if(num<aleat) {
				JOptionPane.showMessageDialog(null, "O  numero secreto e maior que "+ num +"!");
			}else if(num>aleat) {
				JOptionPane.showMessageDialog(null, "O  numero secreto e menor que "+ num +"!");

			}else if(num==aleat) {
				jogador.setPontuacao(quant*10);
				JOptionPane.showMessageDialog(null, "Parabens você acertou o numero e sua pontuação e: "+jogador.getPontuacao());
				jogadores.add(jogador);
			}
			quant--;
		}
		if(num!=aleat && quant==0) {
			JOptionPane.showMessageDialog(null, "O numero secreto era "+aleat+"!");
			jogadores.add(jogador);
		}
	}

	static void placar(List<jogador> jogadores) {
		String jog = "";
		String cabecalho = "Nome        Pontuacao\n"; 
		for(jogador p : jogadores) {
			jog += p.getNome()+"\t         "+p.getPontuacao()+"\n";
		}
		JOptionPane.showMessageDialog(null,cabecalho +  jog);
	}
	static void iniciarArquivo() {
		String linha="";
		try {
			BufferedReader arqEntrada = new BufferedReader (new FileReader("jogadores"));
			memoria.delete(0,memoria.length());
			while((linha=arqEntrada.readLine()) !=null) {
				memoria.append(linha+"\n");
			}
			arqEntrada.close();
		}
		catch(FileNotFoundException erro1) {
			System.out.println("Arquivo não encontrado!");
		}
		catch(Exception erro2) {
			System.out.println("Erro de leitura!");
		}

	}

	static void salvarPlacar(List<jogador> jogadores) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter("jogadores"));
		
		String jog = "";
		for(jogador p : jogadores) {
			jog+=(p.getNome()+" "+p.getPontuacao() + "\n");
		}
		buffWrite.append(jog);
		buffWrite.close();
	}
	static void carregarPlacar(List<jogador> jogadores) throws IOException {
		int inicio=0, ultimo, primeiro, fim;
		
		//carregar o .txt em uma variavel StringBuffer
		iniciarArquivo();
		
		//trabalhar com o conteudo do .txt armazenado na variavel StringBuffer
		if(memoria.length()!=0) {
			while(inicio != memoria.length()) {
				jogador jogador = new jogador();
				ultimo = memoria.indexOf(" ",inicio);
				jogador.setNome(memoria.substring(inicio,ultimo));
				primeiro = ultimo+1;
				fim= memoria.indexOf("\n",primeiro);
				jogador.setPontuacao(Integer.parseInt(memoria.substring(primeiro,fim)));
				inicio=fim+1;
				jogadores.add(jogador);	
				
			}
		}
	}


}
