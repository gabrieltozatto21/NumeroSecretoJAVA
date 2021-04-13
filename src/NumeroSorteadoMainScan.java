
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NumeroSorteadoMainScan {

	static StringBuffer memoria = new StringBuffer();
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		List<jogador> jogadores = new ArrayList<>();
		char opcao=' ';
		try {
			carregarPlacar(jogadores);
		} catch (IOException e1) {
			System.out.println("Não carregado!");
		}

		do{
			System.out.println("Escolha uma opcao:\n"
					+ "1. Jogar\n"
					+ "2. Pontuacao\n"
					+ "3. Sair do programa");
			System.out.print("Digite uma opcao: ");
			opcao = in.next().charAt(0);
			System.out.println();
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
				System.out.println("Tchau, ate mais!");
				break;
			default:
				System.out.println("Opcaoo invalida");
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
		System.out.print("Digite o nome do jogador: ");
		in.nextLine();
		jogador.setNome(in.nextLine());

		int quant=10, num=0, aleat=gerarNumero();
		while(num!=aleat && quant!=0) {
			System.out.println("Faltam "+ quant +" tentativas!");
			System.out.println("Número: ");
			num = in.nextInt();
			if(num<aleat) {
				System.out.println("O  numero secreto e maior que "+ num +"!");
			}else if(num>aleat) {
				System.out.println("O  numero secreto e menor que "+ num +"!");

			}else if(num==aleat) {
				jogador.setPontuacao(quant*10);
				System.out.println("Parabens você acertou o numero e sua pontuação e: "+jogador.getPontuacao());
				jogadores.add(jogador);
			}
			quant--;
		}
		if(num!=aleat && quant==0) {
			System.out.println("O numero secreto era "+aleat+"!");
			jogadores.add(jogador);
		}
	}

	static void placar(List<jogador> jogadores) {
		String jog = "";
		String cabecalho = "Nome\t\tPontuacao\n"; 
		for(jogador p : jogadores) {
			jog += p.getNome()+"\t\t   "+p.getPontuacao()+"\n\n";
		}
		System.out.println(cabecalho +  jog);
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
