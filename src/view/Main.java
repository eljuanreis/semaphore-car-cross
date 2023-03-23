package view;

import java.util.concurrent.Semaphore;

import controller.CarroCruzamentoThread;

public class Main {

	public static void main(String[] args) {
		String[] lados = { "norte", "sul", "leste", "oeste" };

		Semaphore semaforo = new Semaphore(1);

		for (String lado : lados) {
			CarroCruzamentoThread c = new CarroCruzamentoThread(lado, semaforo);

			c.start();
		}
	}
}
