package model;

public class PosicaoXY {

	private int posicaoX;
	private int posicaoY;

	public PosicaoXY() {
		this.posicaoX = 0;
		this.posicaoY = 0;
	}

	public PosicaoXY(int posX, int posY) {
		this.posicaoX = posX;
		this.posicaoY = posY;
	}

	public int getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}

	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}

	@Override
	public String toString() {
		return "Posição X : " + this.posicaoX + ", Posicao Y: " + this.posicaoY ;
	}
}
