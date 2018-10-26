package main;

import model.Robo;
import model.Tabuleiro;
import utils.Constantes;

public class Principal {

	public static void main(String[] args) throws InterruptedException {

		Tabuleiro tab = new Tabuleiro(15, 29);
		tab.gerarObstaculos(4);
		Thread.sleep(1500);
		tab.exibirTabuleiro();

		Robo robo = new Robo(tab);

		tab.tabuleiro[0][5] = Constantes.OBSTACULO;

		while (robo.isConcluiuPercurso()) {
			robo.movimentar();
			tab.exibirTabuleiro();
			Thread.sleep(1500);
		}

	}

}
