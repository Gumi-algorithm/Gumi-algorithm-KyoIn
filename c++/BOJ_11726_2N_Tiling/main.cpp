#include <iostream>

using namespace std;

int main()
{
	int arr[1001];

	arr[0] = 0;
	arr[1] = 1;
	arr[2] = 2;

	int N = 0;
	cin >> N;

	if (N == 2) {
		cout << arr[2];
		return 0;
	}
	//그냥 피보나치였군..
	//모듈러 연산 공식 보면 각가 (x+y)%z=(x%z+y%z)%z 같음!!!
	for (int i = 3;i <= N;i++) {
		arr[i] = (arr[i - 1] + arr[i - 2]) %10007;
	}
	//overflow 발생가능
	//cout << arr[N]%10007;
	cout << arr[N] % 10007;

}

