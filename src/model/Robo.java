package model;

public class Robo {

	private static final String OBSTACULO = " X ";

	private Tabuleiro tabuleiro;
	private MovimentosTabuleiro movimento;

	private PosicaoXY posicaoXY;
	private int pilhaMovimentos;
	private boolean terminou;

	String distanciaEsquerda;
	String distanciaDireita;
	String distanciaCima;
	String distanciaBaixo;

	public Robo(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		this.tabuleiro.setRobo(this);

		this.posicaoXY = new PosicaoXY();
		this.movimento = MovimentosTabuleiro.CIMA;
	}

	public void movimentar() {
//		PosicaoXY proximoMovimento = retornarMovimento();
//		String valor = this.tabuleiro.retornarValorPosicaoLabirinto(proximoMovimento);
//
//		if ("FIM".equals(valor)) {
//			this.tabuleiro.limpar();
//			this.terminou = true;
//			return;
//		}
//		
//		if (valor.equals("RBO") || valor.equals(OBSTACULO) ) {
//			proximoMovimento();
//			return;
////			aumentarPilha();
////			movimentar();
//		} else {
//			movimento();
//			this.tabuleiro.limpar();
//			this.posicaoXY = proximoMovimento;
//		}

		PosicaoXY posicaoRobo = getPosicao();
		String valorCimaDist1 = null;
		String valorCimaDist2 = null;

		String valorBaixoDist1;
		String valorBaixoDist2;

		String valorDireitaDist1;
		String valorDireitaDist2;
		
		String valorEsquerdaDist1;
		String valorEsquerdaDist2;

//		// Lado esquerdo
//		if (posicaoRobo.getPosicaoY() > 1) {
//			valorEsquerdaDist1 = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX()][posicaoRobo.getPosicaoY() + 1];
//			valorEsquerdaDist2 = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX()][posicaoRobo.getPosicaoY() + 2];
//			distanciaEsquerda = analisaDistanciaObstaculo(valorEsquerdaDist1, valorEsquerdaDist2);
//		} else {
//			distanciaEsquerda = "P";
//		}

		// Lado direito
		if (posicaoRobo.getPosicaoY() < this.tabuleiro.getTamanhotabuleiro() - 2) {
			valorDireitaDist1 = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX()][posicaoRobo.getPosicaoY() + 1];
			valorDireitaDist2 = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX()][posicaoRobo.getPosicaoY() + 2];
			distanciaDireita = analisaDistanciaObstaculo(valorDireitaDist1, valorDireitaDist2);
		} else {
			distanciaDireita = "P";
		}

		// Baixo
		if (posicaoRobo.getPosicaoX() < this.tabuleiro.getTamanhotabuleiro() - 2) {
			valorBaixoDist1 = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX() + 1][posicaoRobo.getPosicaoY()];
			valorBaixoDist2 = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX() + 2][posicaoRobo.getPosicaoY()];
			distanciaBaixo = analisaDistanciaObstaculo(valorBaixoDist1, valorBaixoDist2);
		} else {
			distanciaBaixo = "P";
		}

		// Cima
		if (posicaoRobo.getPosicaoX() > 1) {
			valorCimaDist1 = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX() - 1][posicaoRobo.getPosicaoY()];
			valorCimaDist2 = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX() - 2][posicaoRobo.getPosicaoY()];
			distanciaCima = analisaDistanciaObstaculo(valorCimaDist1, valorCimaDist2);
		} else {
			distanciaCima = "P";
		}

		if (distanciaDireita == "P" && distanciaCima == "P" && distanciaBaixo == "DB") {
			this.movimento = MovimentosTabuleiro.BAIXO;
		} else if (distanciaDireita == "P" && distanciaCima == "P" && distanciaBaixo == "L") {
			this.movimento = MovimentosTabuleiro.BAIXO;
		} else if (distanciaDireita == "P" && distanciaCima == "DB" && distanciaBaixo == "P") {
			this.movimento = MovimentosTabuleiro.CIMA;
		} else if (distanciaDireita == "P" && distanciaCima == "DB" && distanciaBaixo == "DB") {
			this.movimento = MovimentosTabuleiro.BAIXO;
		} else if (distanciaDireita == "P" && distanciaCima == "DB" && distanciaBaixo == "L") {
			this.movimento = MovimentosTabuleiro.BAIXO;
		} else if (distanciaDireita == "P" && distanciaCima == "L" && distanciaBaixo == "P") {
			this.movimento = MovimentosTabuleiro.CIMA;
		} else if (distanciaDireita == "P" && distanciaCima == "L" && distanciaBaixo == "DB") {
			this.movimento = MovimentosTabuleiro.CIMA;
		} else {
			this.movimento = MovimentosTabuleiro.DIREITA;
		}

		PosicaoXY proximoMovimento = retornarMovimento();
		System.out.println(this.movimento);
		System.out.println(distanciaDireita);
		this.tabuleiro.limpar();
		this.posicaoXY = retornarMovimento();

	}

	private String analisaDistanciaObstaculo(String valorDist1, String valorDist2) {

		if (valorDist1 == OBSTACULO) {
			return "P";
		} else if (valorDist2 == OBSTACULO) {
			return "DB";
		} else {
			return "L";
		}
	}

	private void aumentarPilha() {
		this.pilhaMovimentos++;
	}

	private void proximoMovimento() {
		switch (this.movimento) {
		case CIMA:
			this.movimento = MovimentosTabuleiro.DIREITA;
			break;
		case DIREITA:
			this.movimento = MovimentosTabuleiro.BAIXO;
			break;
		case BAIXO:
			this.movimento = MovimentosTabuleiro.ESQUERDA;
			break;
		case ESQUERDA:
			this.movimento = MovimentosTabuleiro.CIMA;
			break;
		}
	}

	public PosicaoXY retornarMovimento() {
		int retornoPosX = this.posicaoXY.getPosicaoX();
		int retornoPosY = this.posicaoXY.getPosicaoY();

		int tamanhoDoPasso = 3;

		switch (movimento) {
		case CIMA:
			if (retornoPosX > 0) {
				retornoPosX -= tamanhoDoPasso;
			}
			break;
		case BAIXO:
			if (retornoPosX < this.tabuleiro.getTamanhotabuleiro() - 1) {
				retornoPosX += tamanhoDoPasso;
			}
			break;
		case ESQUERDA:
			if (retornoPosY > 0) {
				retornoPosY -= tamanhoDoPasso;
			}
			break;
		case DIREITA:
			if (retornoPosY < this.tabuleiro.getTamanhotabuleiro() - 1) {
				retornoPosY += tamanhoDoPasso;
			}
			break;
		}
		return new PosicaoXY(retornoPosX, retornoPosY);
	}

	public boolean isConcluiuPercurso() {
		return !terminou;
	}

	public void zerarPilha() {
		this.pilhaMovimentos = 0;
	}

	public PosicaoXY getPosicao() {
		return this.posicaoXY;
	}
}
