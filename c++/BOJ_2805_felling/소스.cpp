#include<iostream>

using namespace std;

//1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000
int N; // 나무의수 
int M; // 나무의 길이
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

	long long left = 0; //자를수있는 최소 높이
	long long right = max - 1; //자를수있는 최대 높이, 최소1이상

	long long result = 0;

	while (left <= right) {
		long mid = (left + right) / 2;
		cout << mid << "\n";
		if (check(mid)) { //나무를 들고갈수있다 높이를 높여도된다.
			result = MAX(mid, result);
			left = mid + 1;
			cout << "higer" << result << "\n";
		}
		else { //나무를 들고갈수없다. 높이를 낮춰야된다.
			right = mid - 1;
		}
	}
	printf("%lld\n", result);
	return 0;

}