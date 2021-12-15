from opensimplex import OpenSimplex

def linspace(l, u, n):
  passo = 0
  tick = u/n
  arr = []
  for _ in range(n):
    arr.append(passo)
    passo += tick
  return arr

def get_perlin_arr(seed=1, n=45000):
  noise = OpenSimplex(seed=seed)
  x = [i for i in range(n)]

  y = []
  for i in linspace(0,80,n):
    ns = noise.noise2d(i, 0)
    y.append(ns)

  return y