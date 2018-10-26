package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.Constantes;

public class Tabuleiro {

	public String tabuleiro[][];
	public int coordenadaFim[] = new int[2];

	private Robo robo;

	public Tabuleiro(int linha, int coluna) {
		this.coordenadaFim[0] = linha;
		this.coordenadaFim[1] = coluna;

		this.construirTabuleiro();
	}

	public void setRobo(Robo robo) {
		this.robo = robo;
	}

	private void construirTabuleiro() {

		tabuleiro = new String[Constantes.TAMANHO][Constantes.TAMANHO];

		for (int i = 0; i < Constantes.TAMANHO; i++) {
			for (int j = 0; j < Constantes.TAMANHO; j++) {

				if (i == 0 && j == 0) {
					tabuleiro[i][j] = Constantes.ROBO;
					continue;
				}

				if (i == coordenadaFim[0] && j == coordenadaFim[1]) {
					tabuleiro[i][j] = Constantes.FIM;
					continue;
				}

				tabuleiro[i][j] = Constantes.UNIDADE_TAB;

			}
		}

	}

	public void exibirTabuleiro() {
		atualizarPosicaoAgente();

		for (int i = 0; i < Constantes.TAMANHO; i++) {
			for (int j = 0; j < Constantes.TAMANHO; j++) {
					System.out.print("|" + tabuleiro[i][j]);
			}
			System.out.print("|");
			System.out.println("");

		}
		System.out.println("");
	}

	private void atualizarPosicaoAgente() {
		if (this.robo != null) {
			PosicaoXY posAgente = this.robo.getPosicao();
			tabuleiro[posAgente.getPosicaoX()][posAgente.getPosicaoY()] = Constantes.ROBO;
		}
	}

	public void limparCaminhoAnterior() {
		PosicaoXY posicao = this.robo.getPosicao();
		tabuleiro[posicao.getPosicaoX()][posicao.getPosicaoY()] = Constantes.UNIDADE_TAB;
	}

	public String retornarValorPosicaoLabirinto(PosicaoXY posicao) {
		return this.tabuleiro[posicao.getPosicaoX()][posicao.getPosicaoY()];
	}

	public int getTamanhotabuleiro() {
		return Constantes.TAMANHO;
	}

	public void gerarObstaculos(int numObstaculos) {

		List<Integer> anterioresDeX = new ArrayList<>();
		List<Integer> anterioresDeY = new ArrayList<>();

		anterioresDeX.add(0);
		anterioresDeY.add(0);

		for (int i = 0; i < numObstaculos; i++) {

			Random gerador = new Random();

			int tipoObstaculo = gerador.nextInt(8);
			int x = 0;
			int y = 0;

			while ((x == 0 || x == Constantes.TAMANHO - 1) || (y == 0 || y == Constantes.TAMANHO - 1)) {
				x = gerador.nextInt(29);
				y = gerador.nextInt(29);

				for (int j = 0; j < anterioresDeX.size(); j++) {
					if ((anterioresDeX.get(j) == x && anterioresDeY.get(j) == y)
							|| (anterioresDeX.get(j) - 2 == x && anterioresDeY.get(j) - 2 == y)
							|| (anterioresDeX.get(j) - 2 == x && anterioresDeY.get(j) + 2 == y)
							|| (anterioresDeX.get(j) + 2 == x && anterioresDeY.get(j) - 2 == y)
							|| (anterioresDeX.get(j) + 2 == x && anterioresDeY.get(j) + 2 == y)
							|| (anterioresDeX.get(j) - 2 == x && anterioresDeY.get(j) == y)
							|| (anterioresDeX.get(j) == x && anterioresDeY.get(j) - 2 == y)
							|| (anterioresDeX.get(j) + 2 == x && anterioresDeY.get(j) == y)
							|| (anterioresDeX.get(j) == x && anterioresDeY.get(j) + 2 == y) || anterioresDeX.get(j) == x
							|| anterioresDeY.get(j) == y) {

						x = 0;
						y = 0;
					}
				}
			}

			anterioresDeX.add(x);
			anterioresDeY.add(y);

			switch (tipoObstaculo) {
			case 0:
				this.tabuleiro[x][y] = Constantes.OBSTACULO;
				this.tabuleiro[x - 1][y] = Constantes.OBSTACULO;
				this.tabuleiro[x - 1][y + 1] = Constantes.OBSTACULO;
				break;
			case 1:
				this.tabuleiro[x][y] = Constantes.OBSTACULO;
				this.tabuleiro[x - 1][y] = Constantes.OBSTACULO;
				this.tabuleiro[x - 1][y - 1] = Constantes.OBSTACULO;
				break;
			case 2:
				this.tabuleiro[x][y] = Constantes.OBSTACULO;
				this.tabuleiro[x][y - 1] = Constantes.OBSTACULO;
				this.tabuleiro[x - 1][y - 1] = Constantes.OBSTACULO;
				break;
			case 3:
				this.tabuleiro[x][y] = Constantes.OBSTACULO;
				this.tabuleiro[x][y - 1] = Constantes.OBSTACULO;
				this.tabuleiro[x + 1][y - 1] = Constantes.OBSTACULO;
				break;
			case 4:
				this.tabuleiro[x][y] = Constantes.OBSTACULO;
				this.tabuleiro[x][y + 1] = Constantes.OBSTACULO;
				this.tabuleiro[x - 1][y + 1] = Constantes.OBSTACULO;
				break;
			case 5:
				this.tabuleiro[x][y] = Constantes.OBSTACULO;
				this.tabuleiro[x][y + 1] = Constantes.OBSTACULO;
				this.tabuleiro[x + 1][y + 1] = Constantes.OBSTACULO;

				break;
			case 6:
				this.tabuleiro[x][y] = Constantes.OBSTACULO;
				this.tabuleiro[x + 1][y] = Constantes.OBSTACULO;
				this.tabuleiro[x + 1][y - 1] = Constantes.OBSTACULO;
				break;
			case 7:
				this.tabuleiro[x][y] = Constantes.OBSTACULO;
				this.tabuleiro[x + 1][y] = Constantes.OBSTACULO;
				this.tabuleiro[x + 1][y + 1] = Constantes.OBSTACULO;
				break;
			}
		}
	}
}
