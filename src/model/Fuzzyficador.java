package model;

public class Fuzzyficador {

	float grauPertinenciaVariavelPequena = 0;
	float grauPertinenciaVariavelGrande = 0;
	
	public float decidirMovimento(float distanciaObstaculo) {
		fuzzyficar(distanciaObstaculo);
		
		float resultadoDefuzz = defuzzyficar();
		
		return resultadoDefuzz;
	}
	
	public void fuzzyficar(float distanciaObstaculo) {
		
		grauPertinenciaVariavelPequena(distanciaObstaculo);
		grauPertinenciaVariavelGrande(distanciaObstaculo);
	}
	
	public void grauPertinenciaVariavelPequena(float distanciaObstaculo) {
		
		if(distanciaObstaculo <= 1) {
			grauPertinenciaVariavelPequena = 1;
		} else if(distanciaObstaculo > 1 && distanciaObstaculo < 4) {
			grauPertinenciaVariavelPequena = (4 - distanciaObstaculo) / 3;  //Distancia pequena
		} else if(distanciaObstaculo >= 4) {
			grauPertinenciaVariavelPequena = 0;
		}
		
	}
	
	public void grauPertinenciaVariavelGrande(float distanciaObstaculo) {
		
		if(distanciaObstaculo <= 2) {
			grauPertinenciaVariavelGrande = 0;
		} else if(distanciaObstaculo > 2 && distanciaObstaculo < 5) {
			grauPertinenciaVariavelGrande = (distanciaObstaculo - 2) / 3; //Distancia grande
		} else if(distanciaObstaculo >= 5) {
			grauPertinenciaVariavelGrande = 1;
		}
	}
	
	public float defuzzyficar() {
		return ((1 + 2 + 3 + 4) * grauPertinenciaVariavelPequena + (2 + 3 + 4 + 5) * grauPertinenciaVariavelGrande) / (4 * grauPertinenciaVariavelPequena + 4 * grauPertinenciaVariavelGrande);
		
	}

}
