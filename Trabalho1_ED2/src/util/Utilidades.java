package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;

import application.TesteOrdena;

public class Utilidades {

	/**
	 * Generate an array based on a seed
	 */
	public static int[] generateArray(int seed, int length) {
		Random rand = new Random(seed);
		int[] v = new int[length];
		for (int i = 0; i < length; i++)
			v[i] = rand.nextInt();
		return v;
	}

	public static void saveResults(Map<String, Map<Integer, Long>> algorithmsResults) {
		try {
			String path = new File("").getAbsolutePath();
			path = path.substring(0, path.lastIndexOf("\\"));

			File arq = new File(path, "results.csv");

			FileWriter fw = new FileWriter(arq);
			PrintWriter pw = new PrintWriter(fw);

			pw.println("Algoritmo;Tamanho;Nanosegundos");
			for (int t : TesteOrdena.TAMANHOS) {
				for (String a : algorithmsResults.keySet()) {
					pw.printf("%s;%d;%d%n", a, t, algorithmsResults.get(a).get(t));
				}
			}

			pw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			System.err.println("Ouve algum problema ao salvar os arquivos.");
			System.err.println(e.getMessage());
		}
	}

}
