package model;

import utils.Constantes;
import utils.MovimentosTabuleiro;

public class Robo {

	private Tabuleiro tabuleiro;
	private MovimentosTabuleiro movimento;
	private Fuzzyficador fuzzyficador;

	private PosicaoXY posicaoXY;
	private boolean terminou;
	
	int distanciaObstaculoEsquerda;
	int distanciaObstaculoDireita;
	int distanciaObstaculoCima;
	int distanciaObstaculoBaixo;

	public Robo(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		this.tabuleiro.setRobo(this);

		this.posicaoXY = new PosicaoXY();
		fuzzyficador = new Fuzzyficador();
		this.movimento = MovimentosTabuleiro.DIREITA;
	}

	public void movimentar() {
		
		this.movimento = MovimentosTabuleiro.DIREITA;
		
		analisarCaminho();

		float movimentoBaixo = fuzzyficador.decidirMovimento(distanciaObstaculoBaixo);
		float movimentoDireita = fuzzyficador.decidirMovimento(distanciaObstaculoDireita);
		float movimentoEsquerda = fuzzyficador.decidirMovimento(distanciaObstaculoEsquerda);
		float movimentoCima = fuzzyficador.decidirMovimento(distanciaObstaculoCima);

		int tamanhoDoPasso = (int) movimentoDireita;
		
		if ((distanciaObstaculoDireita == 1 || distanciaObstaculoDireita == 0) && distanciaObstaculoBaixo != 0 ) {
			this.movimento = MovimentosTabuleiro.BAIXO;
			tamanhoDoPasso = (int) movimentoBaixo;
		} else if ((distanciaObstaculoBaixo == 1 || distanciaObstaculoBaixo == 0) && distanciaObstaculoDireita != 1) {
			this.movimento = MovimentosTabuleiro.DIREITA;
			tamanhoDoPasso = (int) movimentoDireita;
		} 

		this.tabuleiro.limparCaminhoAnterior();
		this.posicaoXY = retornarMovimento(tamanhoDoPasso);
		
	}
	
	public void analisarCaminho() {
		PosicaoXY posicaoRobo = getPosicao();
		
		distanciaObstaculoEsquerda = 0;
		distanciaObstaculoDireita = 0;
		distanciaObstaculoCima = 0;
		distanciaObstaculoBaixo = 0;

		int distanciaSensor = 5;
		// Direita
		for (int i = 1; i <= distanciaSensor; i++) {
			if (posicaoRobo.getPosicaoY() < this.tabuleiro.getTamanhotabuleiro() - i) {
				String valorDireitaDist = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX()][posicaoRobo.getPosicaoY() + i];
				
				if(valorDireitaDist.equals(Constantes.UNIDADE_TAB)) {
					distanciaObstaculoDireita++;
				} else if(valorDireitaDist.equals(Constantes.OBSTACULO)) {
					break;
				}
				
				if (Constantes.FIM.equals(valorDireitaDist)) 
					this.terminou = true;
				
			}
		}

		// Esquerda
		for (int i = 1; i <= distanciaSensor; i++) {
			if (posicaoRobo.getPosicaoY() > i) {
				String valorEsquerdaDist = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX()][posicaoRobo.getPosicaoY()
						- i];
				if(valorEsquerdaDist.equals(Constantes.UNIDADE_TAB)) {
					distanciaObstaculoEsquerda++;
				} else if(valorEsquerdaDist.equals(Constantes.OBSTACULO)) {
					break;
				}
				
				
				if (Constantes.FIM.equals(valorEsquerdaDist)) 
					this.terminou = true;
			}
		}

		// Baixo
		for (int i = 1; i <= distanciaSensor; i++) {
			if (posicaoRobo.getPosicaoX() < this.tabuleiro.getTamanhotabuleiro() - i) {
				String valorBaixoDist = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX() + i][posicaoRobo
						.getPosicaoY()];
				if(valorBaixoDist.equals(Constantes.UNIDADE_TAB)) {
					distanciaObstaculoBaixo++;
				} else if(valorBaixoDist.equals(Constantes.OBSTACULO)) {
					break;
				}
				
				if (Constantes.FIM.equals(valorBaixoDist)) 
					this.terminou = true;
			}
		}

		// Cima
		for (int i = 1; i <= distanciaSensor; i++) {
			if (posicaoRobo.getPosicaoX() > i) {
				String valorCimaDist = this.tabuleiro.tabuleiro[posicaoRobo.getPosicaoX() - i][posicaoRobo
						.getPosicaoY()];
				if (valorCimaDist.equals(Constantes.UNIDADE_TAB)) {
					distanciaObstaculoCima++;
				} else if(valorCimaDist.equals(Constantes.OBSTACULO)) {
					break;
				}
				
				if (Constantes.FIM.equals(valorCimaDist)) 
					this.terminou = true;
			}
		}
	}

	public PosicaoXY retornarMovimento(int tamanhoDoPasso) {
		int retornoPosX = this.posicaoXY.getPosicaoX();
		int retornoPosY = this.posicaoXY.getPosicaoY();

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

	public PosicaoXY getPosicao() {
		return this.posicaoXY;
	}
	
	public String getValorPosicaoRobo() {
		return this.tabuleiro.tabuleiro[getPosicao().getPosicaoX()][getPosicao().getPosicaoY()];
	}
}
