import matplotlib.pyplot as plt
import numpy as np


def unique(arr):
    return np.unique(np.array(arr))


algs, tamanhos, ns = np.loadtxt('results.csv',
                                delimiter=';',
                                unpack=True,
                                dtype='str')

algoritmos = unique(algs[1:])
tamanhos = unique(tamanhos[1:].astype(int))

print(tamanhos)

r = dict()
for a in algoritmos:
    r[a] = ns[algs == a].astype(int)

print(r)

x = np.arange(len(tamanhos))
width = 0.2

fig, ax = plt.subplots()

for i in range(0, len(algoritmos)):
    ax.plot(x, r[algoritmos[i]], '.-', label=algoritmos[i])
    # ax.bar(x + width * i, r[algoritmos[i]], width, label=algoritmos[i])

ax.set_ylabel('Tempo (nanosegundos)')
ax.set_xlabel('Quantidade de Elementos')
ax.set_title('Tempo de Execução')
ax.set_xticks(x, tamanhos)
ax.legend(title='Algoritmo')

fig.tight_layout()
plt.show()
