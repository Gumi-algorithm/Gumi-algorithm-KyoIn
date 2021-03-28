#include<iostream>
#include<vector>
#include<utility>
#include<algorithm>

using namespace std;

int V; //  정점의 개수 V(1 ≤ V ≤ 10,000) 
int E; // 간선의 개수 E(1 ≤ E ≤ 100,000)
//크루스칼

vector<pair<pair<int, int>, int>> vec;

bool compare(const pair<pair<int, int>, int>& a, const pair<pair<int, int>, int>& b) {
	return a.second < b.second;
}

//union find사용하기
//1부터라는 사실 ㅠㅠ
int arr[10001];

int find(int a) {
	if (arr[a] == a) return a;
	return arr[a] = find(arr[a]);
}

bool union_edge(int to, int from) {
	to = find(to);
	from = find(from);

	if (to == from)
		return false;

	arr[from] = to;
	return true;
}

int main() {

	cin >> V;
	cin >> E;

	for (int i = 0;i < E;i++) {
		int temp1, temp2, temp3;
		cin >> temp1;//A에서
		cin >> temp2;//B로
		cin >> temp3;//간선C
		vec.push_back(make_pair(make_pair(temp1, temp2), temp3));
	}

	sort(vec.begin(), vec.end(), compare);

	//for (int i = 0;i < E;i++) {
	//	pair<pair<int, int>, int> temp = vec[i];
	//	cout << vec[i].first.first<<" " <<vec[i].first.second << " "<< vec[i].second << "\n";
	//}

	//makeset
	for (int i = 1;i < V+1;i++) {
		arr[i] = i;
	}
	//간선 작은거 부터 넣기
	long long result = 0;
	int count = 0;
	for(int i=0;i<E;i++){
		pair<pair<int, int>, int> temp = vec[i];

		if (union_edge(temp.first.first, temp.first.second)) {
			count++;
			result += temp.second;
		}
		
	}
	cout << result;
	return 0;
}