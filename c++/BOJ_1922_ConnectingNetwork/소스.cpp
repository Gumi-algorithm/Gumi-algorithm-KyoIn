#include<iostream>
#include<vector>
#include<utility>
#include<algorithm>

#define INF 1001
using namespace std;
//프림써보기
int N; // 컴퓨터의 수 N (1 ≤ N ≤ 1000)
int M; //연결할 수 있는 선의 수 M (1 ≤ M ≤ 100,000)

int map[1001][1001]; 
int d[1001];
bool visited[1001];

int main() {
	//모든 컴퓨터를 연결할 수 없는 경우는 없다.
	cin >> N;
	cin >> M;
	
	for (int i = 1;i <=N;i++) {
		d[i] = INF;
		for (int j = 1;j <=N;j++) {
			map[i][j] = INF;
		}
	}

	for (int i = 0;i < M;i++) {
		int start, end, cost;
		cin >> start; //a에서
		cin >> end; //b로
		cin >> cost; //c만큼

		//방향성이 없는 것 중요!!!
		map[start][end] = cost;
		map[end][start] = cost;
	}

	int minVertex = 1;
	int min = 0;
	d[1] = 0;
	int result = 0;
	for (int v = 1;v <= N;v++) { // 모든 정점수만큼
		min = INF; 
		minVertex=0; 

		for (int i = 1;i <= N;i++) {
			if (!visited[i] && min > d[i]) {
				min = d[i];
				minVertex = i;
			}
		}

		result += min;
		visited[minVertex] = true;

		for (int i = 1;i <=N;i++) {
			if (!visited[i] && map[minVertex][i] != INF && map[minVertex][i] < d[i]) {
				d[i]=map[minVertex][i];
			}
		}

	}

	cout << result << "\n";

	return 0;
}