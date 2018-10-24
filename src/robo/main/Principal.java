package robo.main;

import model.Robo;
import model.Tabuleiro;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		
		Tabuleiro tab = new Tabuleiro(4, 8);
		tab.gerarObstaculos(4);
		Thread.sleep(1500);
		tab.exibirTabuleiro();
		
		Robo robo = new Robo(tab);

		while (robo.isConcluiuPercurso()) {
			robo.zerarPilha();
			robo.movimentar();
			tab.exibirTabuleiro();
			Thread.sleep(1500);
		}

	}

}
