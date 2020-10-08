class Solution {
public:                       #######很简单的一道题  时间复杂度 n^2，空间复杂度就是原来矩阵 n^2
    void rotate(vector<vector<int>>& matrix) {
        int pos1=0,pos2=matrix.size()-1;
        int add,temp;
        while(pos1<pos2){     #####每次对一圈的元素做旋转
            add = 0;
            while(add < pos2-pos1){########每次动一个元素，顺时针，temp储存临时量
                temp = matrix[pos2-add][pos1];
                matrix[pos2-add][pos1] = matrix[pos2][pos2-add];
                matrix[pos2][pos2-add] = matrix[pos1+add][pos2];
                matrix[pos1+add][pos2] = matrix[pos1][pos1+add];
                matrix[pos1][pos1+add] = temp;
                add++;
            }
            pos1++;
            pos2--;
        }

    }
};
