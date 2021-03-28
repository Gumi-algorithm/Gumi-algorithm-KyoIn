#include<iostream>

using namespace std;

#define MAX(a,b) a<b? b:a

long long lan[10000 + 1];

int K; // 이미 가지고 있는 랜선의 개수, K는 1이상 10,000이하의 정수
int N; // 필요한 랜선의 개수 , 1이상 1,000,000이하의 정수
//K ≦ N
//랜선의 길이 2^31-1 => int

bool check(long long check) {
	long long temp = 0;
	for (int i = 0;i < K;i++) {
		temp += (lan[i] / check);
	}
	//cout << "몇개 만들어지나요 " << temp << "\n";
	if (temp >= N) {
		return true;
	}
	else {
		//cout << "못만들어여\n";
		return false;
	}
}
int main() {

	cin >> K;
	cin >> N;

	long long max = 0;
	for (int i = 0;i < K;i++) {
		cin >> lan[i];
		if (max < lan[i]) {
			max = lan[i];
		}
	}

	//2^31-1 두개 더하면 최대값넘어갈수있다는거...

	long long left = 1; // 여기서 틀림
	//0로나누면 DivisionByZero 될수있다는거...
	long long right = max;//최대

	long long result = 0;

	while (left <= right) {
		long long mid = (left + right) / 2;

		if (check(mid)) {
			result = MAX(result, mid);
			left = mid + 1;
		}
		else {
			right = mid - 1;
		}
	}
	cout << result;
	return 0;
}