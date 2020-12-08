
Eff(int m, int n):
1. transfer functoin:  Eff(m,n) = max(Eff(m-1,n-h)+pm(h)) for all 0<= h <= n

Initialization:
ans[m+1][n+1] = -1
for(int i = 0 ;i<=n;i++){
    ans[0][i] = 0;
    ans[1][i] = h1(i)
}

2. Optimal Solution(recursion)
int Eff(int m,int n,vector<int>& ans){
    if(ans[m][n]!=-1){
        return ans[m][n];
    }else{
        int res = 0;
        for(int i =0;i<=n;i++){
            res = max(res,Eff(m-1,n-i,ans)+pm(i));
    }
        ans[m][n] = res;
        return res;
    }

}

Optimal Solution(iterative)
for(int i = 1; i <= m ;i++){
    for(int h = 1;h<= n;h++){

    }
}
3. Optimal Choice

int Eff(int m,int n,vector<int>& ans, vector<int>& c){
    if(ans[m][n]!=-1){
        return ans[m][n];
    }else{
        c[m] = 0;
        int res = Eff(m-1,n,ans,c);
        for(int i =1;i<=n;i++){
            if(res < Eff(m-1,n-i,ans,c)+pm(i)){
                res = Eff(m-1,n-i,ans,c)+pm(i);
                c[m] = i;
            }
    }
        return res;
    }

}

*******
*******
MaxSegSum(a)

F[0] = a[0];
S = F[0];

for (int i = 1;i< n;i++){
    F[i] = max(a[i],F[i-1]+a[i]);
    S = max(S,F[i]);
}

return S;

*******

Longest common string

algo(n,m):
    M[n,m]
    if(n[0] == n[0]){
        M[0,0] = 1;
    }else{
        M[0,0] = 0;
    }

    for(int i = 1;i<n;i++){
        for(int j = 1 ;j<m;j++){
            if(m[i]==n[j]){
                M[i,j] = 1 + M[i-1,j-1];
            }else{
                M[i.j] = max(M[i-1,j],M[i,j-1],M[i-1,j-1])
            }
        }
    }

    return M[n-1,m-1];