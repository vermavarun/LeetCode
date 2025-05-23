class Solution {
  public int maxFrequencyScore(int[] nums, int k) {
    Map<Integer, Integer> count = new HashMap<>();

    for (int i = 0; i < k; ++i)
      count.merge(nums[i], 1, Integer::sum);

    int sum = getInitialSum(count);
    int ans = sum;

    for (int i = k; i < nums.length; ++i) {
      // Remove the leftmost number that's out-of-window.
      final int leftNum = nums[i - k];
      sum = (sum - modPow(leftNum, count.get(leftNum)) + MOD) % MOD;
      // After decreasing its frequency, if it's still > 0, then add it back.
      if (count.merge(leftNum, -1, Integer::sum) > 0)
        sum = (sum + modPow(leftNum, count.get(leftNum))) % MOD;
      // Otherwise, remove it from the count map.
      else
        count.remove(leftNum);
      // Add the current number. Similarly, remove the current score like above.
      final int rightNum = nums[i];
      if (count.getOrDefault(rightNum, 0) > 0)
        sum = (sum - modPow(rightNum, count.get(rightNum)) + MOD) % MOD;
      sum = (sum + modPow(rightNum, count.merge(rightNum, 1, Integer::sum))) % MOD;
      ans = Math.max(ans, sum);
    }

    return ans;
  }

  private static final int MOD = 1_000_000_007;

  private int getInitialSum(Map<Integer, Integer> count) {
    int sum = 0;
    for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
      final int num = entry.getKey();
      final int freq = entry.getValue();
      sum = (sum + modPow(num, freq)) % MOD;
    }
    return sum;
  }

  private int modPow(long x, long n) {
    if (n == 0)
      return 1;
    if (n % 2 == 1)
      return (int) (x * modPow(x % MOD, (n - 1)) % MOD);
    return modPow(x * x % MOD, (n / 2)) % MOD;
  }
}
