package util;

public class Ordena {
	@FunctionalInterface
	public static interface SortAlgorithm {

		public void sort(int[] v);

	}
	
	/**
	 * InsertionSort algorithm
	 */
	public static void insertionSort(int[] v) {
		insertionSortBy(v, 1);
	}

	/**
	 * ShellSort algorithm
	 */
	public static void shellSort(int[] v) {
		int h = v.length;
		while (h > 1) {
			h = h / 2;
			insertionSortBy(v, h);
		}
	}

	/**
	 * QuickSort algorithm
	 */
	public static void quickSort(int[] v) {
		quickSort(v, 0, v.length - 1);
	}

	/**
	 * HeapSort algorithm
	 */
	public static void heapSort(int[] v) {
		int n = v.length;

		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(v, n, i);

		for (int i = n - 1; i > 0; i--) {
			int temp = v[0];
			v[0] = v[i];
			v[i] = temp;

			heapify(v, i, 0);
		}
	}

	private static void heapify(int[] arr, int n, int i) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;

		if (l < n && arr[l] > arr[largest])
			largest = l;

		if (r < n && arr[r] > arr[largest])
			largest = r;

		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			heapify(arr, n, largest);
		}
	}

	private static void quickSort(int[] v, int ini, int fim) {
		int p = v[ini];
		int i = ini + 1;
		int j = fim;
		while (i <= j) {
			if (v[i] < p) {
				i++;
			} else if (v[j] > p) {
				j--;
			} else {
				int a = v[i];
				v[i] = v[j];
				v[j] = a;
				i++;
				j--;
			}
		}
		v[ini] = v[j];
		v[j] = p;
		if (ini < j - 1)
			quickSort(v, ini, j - 1);
		if (fim > j + 1)
			quickSort(v, j + 1, fim);
	}

	/**
	 * Aux insertionSort based algorithms
	 */
	private static void insertionSortBy(int[] v, int h) {

        for (int i = h; i < v.length; i++) {
            int valorAtual = v[i];

            int j = i - h;
            while (j >= 0 && valorAtual < v[j]) {
                v[j + h] = v[j];
                j -= h;
            }
            v[j + h] = valorAtual;
        }
    }

}
