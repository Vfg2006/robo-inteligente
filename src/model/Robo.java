package model;

public class Robo {

	private Tabuleiro tabuleiro;
	private MovimentosTabuleiro movimento;

	private PosicaoXY posicaoXY;
	private int pilhaMovimentos;
	private boolean terminou;

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
//		if (valor.equals("RBO") || valor.equals(" X ") ) {
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
		
		// Lado direito
		String valorDireitaDist1 = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX()][posicaoRobo.getPosicaoY() + 1];
		String valorDireitaDist2 = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX()][posicaoRobo.getPosicaoY() + 2];

		// Baixo
		String valorBaixoDist1 = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX() + 1][posicaoRobo.getPosicaoY()];
		String valorBaixoDist2 = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX() + 2][posicaoRobo.getPosicaoY()];

		// Cima
		if(posicaoRobo.getPosicaoX() > 1) {
			valorCimaDist1 = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX() - 1][posicaoRobo.getPosicaoY()];
			valorCimaDist2 = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX() - 2][posicaoRobo.getPosicaoY()];
			distanciaCima = analisaDistanciaObstaculo(valorCimaDist1, valorCimaDist2);
		} else {
			distanciaCima = "P";
		}

		distanciaDireita = analisaDistanciaObstaculo(valorDireitaDist1, valorDireitaDist2);
		distanciaBaixo = analisaDistanciaObstaculo(valorBaixoDist1, valorBaixoDist2);
		

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
		System.err.println(this.movimento);
		System.out.println(distanciaDireita);
		this.tabuleiro.limpar();
		this.posicaoXY = retornarMovimento();

	}

	private String analisaDistanciaObstaculo(String valorDist1, String valorDist2) {

		if (valorDist1 == " X ") {
			return "P";
		} else if (valorDist2 == " X ") {
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

		switch (movimento) {
		case CIMA:
			if (retornoPosX > 0) {
				retornoPosX -= 1;
			}
			break;
		case BAIXO:
			if (retornoPosX < this.tabuleiro.getTamanhotabuleiro() - 1) {
				retornoPosX += 1;
			}
			break;
		case ESQUERDA:
			if (retornoPosY > 0) {
				retornoPosY -= 1;
			}
			break;
		case DIREITA:
			if (retornoPosY < this.tabuleiro.getTamanhotabuleiro() - 1) {
				retornoPosY += 1;
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
