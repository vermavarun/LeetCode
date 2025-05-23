class Solution {
 public:
  vector<string> sortFeatures(vector<string>& features,
                              vector<string>& responses) {
    vector<string> ans;
    vector<pair<int, int>> featCount;  // {i: count[features[i]]}
    unordered_map<string, int> count;

    for (const string& res : responses) {
      istringstream iss(res);
      unordered_set<string> seen;
      for (string token; getline(iss, token, ' ');)
        seen.insert(token);
      for (const string& token : seen)
        ++count[token];
    }

    for (int i = 0; i < features.size(); ++i)
      featCount.emplace_back(i, count[features[i]]);

    ranges::sort(featCount, ranges::less{}, [](const pair<int, int>& a) {
      const auto& [i, count] = a;
      return pair<int, int>{-count, i};
    });

    for (const auto& [i, count] : featCount)
      ans.push_back(features[i]);

    return ans;
  }
};
