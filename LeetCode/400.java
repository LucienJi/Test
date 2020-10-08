##400. 第N个数字
在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。

注意:
n 是正数且在32位整数范围内 ( n < 231)。

示例 1:

输入:
3

输出:
3
示例 2:

输入:
11

输出:
0

说明:
第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是0，它是10的一部分。
//好奇怪啊，我的int居然撑不到10^9，不是可以到2^31吗，只能强行用long
class Solution {
    public int findNthDigit(long n) {
        int i =1;
        long N =9;
        while(n-N*i>0){
            n-= N*i;
            i++;
            N *= 10;
        }
        
        //i是输出所属数字的位数，indice是输出在该数字中的位数，从第一个i位数：ini 数起第n个字符是输出
        long ini = puissDix(i-1);
        int indice = (int)n%i;
        //输出为某数字末位
        if(indice == 0){
            long nombre = ini + n/i -1;
            return (int)nombre % 10;
        }
      //输出为某数字首位
        else if(indice == 1){
            long nombre = ini + n/i;
            nombre /= puissDix(i-indice);
            return (int)nombre;
        }
      //输出为某数字中间位
        else{
            long nombre = ini + n/i;
            System.out.println(nombre);
            nombre %= puissDix(i-indice+1);
            nombre /= puissDix(i-indice);
            return (int)nombre;
        }

    }
    public long puissDix(int y){
        long res = 1;
        for(int k =0; k<y; k++){
            res *= 10;
        }
        return res;
    }
}
