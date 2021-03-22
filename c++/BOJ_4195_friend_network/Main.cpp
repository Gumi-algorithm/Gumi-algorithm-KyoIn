#include <iostream>
#include<map>

using namespace std;

// 첫째 줄에는 친구 관계의 수 F가 주어지며, 이 값은 100,000을 넘지 않는다
const int MAX = 100000 + 1;
map<string, int> m;
int parent[MAX]; //부모 유파 용
int friend_num[MAX]; // 친구수 저장용


int find(int x)
{
	if (x==parent[x]) return x;

	return parent[x] = find(parent[x]);

}
int make_union(int temp1, int temp2)
{
	temp1 = find(temp1);
	temp2 = find(temp2);

	//둘이 붙어있지X
	if (temp1 != temp2) {
		//붙히기
		parent[temp2] = temp1;
		friend_num[temp1] += friend_num[temp2];
		friend_num[temp2]=1;
	}
	return friend_num[temp1];

}



int main() {
	//이거 있어야 빠르다고 하네
	ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int T, cnt, num;
	string str1, str2;

	cin >> T;
	for (int t = 0;t < T;t++) {
		m.clear();
		cnt = 0;

		//make-set
		for (int i = 0;i < MAX;i++) {
			parent[i] = i;
			friend_num[i] = 1;
		}

		cin >> num;
		int temp1, temp2;
		for (int i = 0;i < num;i++) {
			cin >> str1 >> str2;
			if (m.count(str1) == 0) m[str1] = ++cnt;
			if (m.count(str2) == 0) m[str2] = ++cnt;
			

			cout << make_union(m[str1],m[str2] ) << "\n";
		}

	}
	return 0;
}

