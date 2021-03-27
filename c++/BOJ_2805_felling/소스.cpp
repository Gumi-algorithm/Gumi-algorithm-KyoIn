#include<iostream>

using namespace std;

//1 �� N �� 1,000,000, 1 �� M �� 2,000,000,000
int N; // �����Ǽ� 
int M; // ������ ����
long long tree[1000001];

#define MAX(a, b) a>b? a:b;

bool check(int cut) {
	long long temp = 0;
	for (int i = 0;i < N;i++) {
		long long diff = tree[i] - cut;
		if (diff > 0) {
			temp += diff;
		}

	}
	if (temp >= M) {
		return true;
	}
	return false;
}

int main() {
	cin >> N;
	cin >> M;

	int max = 0;
	for (int i = 0;i < N;i++) {
		cin >> tree[i];
		if (max < tree[i]) {
			max = tree[i];
		}
	}

	long long left = 0; //�ڸ����ִ� �ּ� ����
	long long right = max - 1; //�ڸ����ִ� �ִ� ����, �ּ�1�̻�

	long long result = 0;

	while (left <= right) {
		long mid = (left + right) / 2;
		cout << mid << "\n";
		if (check(mid)) { //������ ������ִ� ���̸� �������ȴ�.
			result = MAX(mid, result);
			left = mid + 1;
			cout << "higer" << result << "\n";
		}
		else { //������ ���������. ���̸� ����ߵȴ�.
			right = mid - 1;
		}
	}
	printf("%lld\n", result);
	return 0;

}