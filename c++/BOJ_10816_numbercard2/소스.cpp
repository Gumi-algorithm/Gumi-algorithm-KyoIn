#include<iostream>
#include <vector>
#include<algorithm>

using namespace std;

int target[500000 + 10];
int ans[500000 + 10];
//���� ī���� ���� N(1 �� N �� 500,000)
//-10,000,000���� ũ�ų� ����, 10,000,000
int N;

//M(1 �� M �� 500,000)
//-10,000,000���� ũ�ų� ����, 10,000,000���� �۰ų� ����
int M;
vector<int> v;

int lowerBound(int target) {
	int left = 0;
	int right = N - 1;
	while (true) {
		int mid = (left + right) / 2;

		if (left > right) {
			if (v[left] == target) return left;
			else return -1;
		}

		if (v[mid] >= target)
			right = mid - 1;
		else {
			left = mid + 1;
		}

	}
}

int upperBound(int target) {
	int left = 0;
	int right = N - 1;
	while (true) {
		int mid = (left + right) / 2;

		if (left > right) {
			if (v[right] == target) return right;
			else return -1;
		}

		if (v[mid] <= target)
			left = mid + 1;
		else {
			right = mid - 1;
		}

	}
}
int main() {
	cin >> N;
	for (int i = 0;i < N;i++) {
		int temp;
		cin >> temp;
		v.push_back(temp);
	}

	sort(v.begin(), v.end());

	cin >> M;
	for (int i = 0;i < M;i++) {
		cin >> target[i];
	}

	for (int i = 0;i < M;i++) {
		//auto upper = upper_bound(v.begin(), v.end(), target[i]);
		//auto lower = lower_bound(v.begin(), v.end(), target[i]);

		int lower = lowerBound(target[i]);
		if (lower == -1) {
			ans[i] = 0; //����
		}
		else {
			int upper = upperBound(target[i]);
			ans[i] = upper - lower+1; // �ִ� 
		}
	}

	for (int i = 0;i < M;i++) {
		cout << ans[i] << " ";
	}

	return 0;
}