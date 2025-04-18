class Fancy {
  // To undo a * val + b and get the original value, we append (val - b) / a.
  // By Fermat's little theorem:
  //   a^(p - 1) ≡ 1 (mod p)
  //   a^(p - 2) ≡ a^(-1) (mod p)
  // So, (val - b) / a ≡ (val - b) * a^(p - 2) (mod p)
  public void append(int val) {
    final long x = (val - b + MOD) % MOD;
    vals.add(x * modPow(a, MOD - 2) % MOD);
  }

  // If the value is a * val + b, then the value after adding by `inc` will be
  // a * val + b + inc. So, we adjust b to b + inc.
  public void addAll(int inc) {
    b = (b + inc) % MOD;
  }

  // If the value is a * val + b, then the value after multiplying by `m` will
  // be a * m * val + b * m. So, we adjust a to a * m and b to b * m.
  public void multAll(int m) {
    a = (a * m) % MOD;
    b = (b * m) % MOD;
  }

  public int getIndex(int idx) {
    return idx >= vals.size() ? -1 : (int) ((a * vals.get(idx) + b) % MOD);
  }

  private static final int MOD = 1_000_000_007;
  // For each `val` in `vals`, it actually represents a * val + b.
  private List<Long> vals = new ArrayList<>();
  private long a = 1;
  private long b = 0;

  private int modPow(long x, long n) {
    if (n == 0)
      return 1;
    if (n % 2 == 1)
      return (int) (x * modPow(x % MOD, (n - 1)) % MOD);
    return modPow(x * x % MOD, (n / 2)) % MOD;
  }
}
