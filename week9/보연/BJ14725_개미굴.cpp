#include <iostream>
#include <vector>
#include <map>

using namespace std;

struct Trie {
	map<string, Trie*> m;

	void insert(vector<string>& str, int idx) {
		if (idx == str.size()) return;
		if (m.find(str[idx]) == m.end()) {
			Trie* trie = new Trie;
			m.insert({ str[idx], trie });
		}
		m[str[idx]]->insert(str, idx + 1);
	}

	void dfs(int curr) {
		for (auto& i : m) {
			for (int j = 0; j < curr; j++) {
				cout << "--";
			}
			cout << i.first << '\n';
			i.second->dfs(curr + 1);
			delete i.second;
		}
	}
};

int N;

int main()
{
	cin >> N;

	Trie* root = new Trie;
	for (int i = 1; i <= N; i++) {
		int num;
		cin >> num;
		vector<string> temp;
		for (int j = 0; j < num; j++) {
			string str;
			cin >> str;
			temp.push_back(str);
		}
		root->insert(temp, 0);
	}

	root->dfs(0);
	delete root;
	return 0;
}
