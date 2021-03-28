#include<iostream>
#include<queue>
#include<utility>
#include<tuple>

using namespace std;
string str;
//N, M(2 ≤ N, M ≤ 100)
int N;//세로
int M;//가로

int map[100][100];
bool visited[100][100];

int dx[4] = { 0,0,-1,1 };
int dy[4] = { 1,-1,0,0 };

int main() {
	
	cin >> N >> M;
	for (int i = 0;i < N;i++) {
		cin >> str;
		for (int j = 0;j < M;j++) {
			map[i][j] = str.at(j) - '0';
		}
	}
	/*
	for (int i = 0;i < N;i++) {
		for (int j = 0;j < M;j++) {
			cout << map[i][j] << " ";
		}
		cout << "\n";
	}
	*/
	//최소의 칸 수 bfs
	queue<tuple<int, int, int>> q;
	q.push(make_tuple(0,0,1));
	visited[0][0] = true;

	while (!q.empty()) {
		auto temp = q.front();
		q.pop();
		
		for (int d = 0;d < 4;d++) {
			int newX = get<0>(temp) + dx[d];
			int newY = get<1>(temp) + dy[d];
			int count = get<2>(temp);

			if (newX < 0 || newY < 0 || newX >= M || newY >= N)
				continue;

			if (newX == M - 1 && newY == N - 1) {
				cout << (count + 1) << "\n";
				return 0;
			}

			if (visited[newY][newX] == true)
				continue;

			if (map[newY][newX] == 0)
				continue;

			q.push(make_tuple(newX, newY, count + 1));
			visited[newY][newX] = true;
		}
	}

	return 0;
}