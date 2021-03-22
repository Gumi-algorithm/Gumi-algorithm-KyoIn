#include <iostream>
#include<map>

using namespace std;

// ù° �ٿ��� ģ�� ������ �� F�� �־�����, �� ���� 100,000�� ���� �ʴ´�
const int MAX = 100000 + 1;
map<string, int> m;
int parent[MAX]; //�θ� ���� ��
int friend_num[MAX]; // ģ���� �����


int find(int x)
{
	if (x==parent[x]) return x;

	return parent[x] = find(parent[x]);

}
int make_union(int temp1, int temp2)
{
	temp1 = find(temp1);
	temp2 = find(temp2);

	//���� �پ�����X
	if (temp1 != temp2) {
		//������
		parent[temp2] = temp1;
		friend_num[temp1] += friend_num[temp2];
		friend_num[temp2]=1;
	}
	return friend_num[temp1];

}



int main() {
	//�̰� �־�� �����ٰ� �ϳ�
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

