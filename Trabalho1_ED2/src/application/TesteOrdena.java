package application;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import util.Ordena;
import util.Utilidades;

public class TesteOrdena {

	public static final int REPETICOES = 50;
	public static final int TAMANHOS[] = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};

	public static void main(String[] args) {
		// Locale.setDefault(Locale.US);
		long start, end;
		int[] arr;

		// Map contendo as referências para cada algoritmo de ordenação:
		Map<String, Ordena.SortAlgorithm> sortAlgorithms = new LinkedHashMap<>();
		sortAlgorithms.put("InsertionSort", Ordena::insertionSort);
		sortAlgorithms.put("ShellSort", Ordena::shellSort);
		sortAlgorithms.put("QuickSort", Ordena::quickSort);
		sortAlgorithms.put("HeapSort", Ordena::heapSort);

		// Map de resultados:
		Map<String, Map<Integer, Long>> algorithmsResuts = new LinkedHashMap<>(sortAlgorithms.size());

		// Lista contendo os dez vetores para serem copiados:
		List<int[]> arrays = new ArrayList<>(TAMANHOS.length);
		Random rand = new Random(12345);
		for (int T : TAMANHOS) {
			arrays.add(Utilidades.generateArray(rand.nextInt(), T));
		}

		// Calculando resultados:
		for (String algorithm : sortAlgorithms.keySet()) {
			algorithmsResuts.put(algorithm, new LinkedHashMap<>());

			// Calcula o resultado de cada vetor:
			for (int i = 0; i < TAMANHOS.length; i++) {
				int t = TAMANHOS[i], sumTime = 0;

				// Roda o algoritmo de ordenação 50 vezes no vetor:
				for (int k = 0; k < REPETICOES; k++) {
					arr = new int[t];
					System.arraycopy(arrays.get(i), 0, arr, 0, t);
					start = System.nanoTime();
					sortAlgorithms.get(algorithm).sort(arr);
					end = System.nanoTime();
					sumTime += end - start;
				}

				// Salva o tempo médio em nanosegundos:
				algorithmsResuts.get(algorithm).put(t, (long) sumTime / REPETICOES);
			}
		}

		// Printa os resultados na tela:
		sortAlgorithms.forEach((k, v) -> {
			System.out.printf("%nUtilizando %s%n", k);
			algorithmsResuts.get(k).forEach((k2, v2) -> System.out.printf("   %5d elementos - %9d nanosegundos%n", k2,
					algorithmsResuts.get(k).get(k2)));
		});

//		sortAlgorithms.forEach((k, v) -> {
//			System.out.printf("%nUtilizando %s%n", k);
//			System.out.print(Arrays.toString(algorithmsResuts.get(k).values().toArray()));
//		});

		// Salva os resultados em disco:
		Utilidades.saveResults(algorithmsResuts);
	}

}
