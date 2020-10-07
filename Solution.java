
class Solution {
	int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        int h = max(heightMap,m,n);
        int higherBaba[][] = new int[m][n];
        int c = 0;
        for(int k = h; k>=1; k-- ) {
        	int[][] floor = new int[m][n];
        	for(int i = 0; i<m; i++ ) {
        		for(int j = 0; j<n; j++ ) {
        			if(higherBaba[i][j]==1 || heightMap[i][j] >= k)
        				floor[i][j] = 1;
        		}
        	}
        	int s = sum(floor,m,n);
        	/*
        	System.out.println("***");
        	show(floor,m,n);
        	System.out.println(s);
        	*/
        	int[][] floor1 = floorTrap(floor, m, n);
        	int s1 = sum(floor1,m,n);
        	/*
        	show(L,m,n);
        	System.out.println(sum(L,m,n));
        	*/
        	c += s1-s;
        	for(int i = 0; i<m; i++ ) {
        		for(int j = 0; j<n; j++ ) {
        			if(floor1[i][j] != floor[i][j])
        				higherBaba[i][j] = 1;
        		}
        	}
        }
        return c;
    }
	
    int[][] floorTrap(int[][] L,int m, int n) {
    	
    	for(int i = 1; i<m-1; i++ ) {
    		for(int j = 1; j<n-1; j++ ) {
    			if(L[i-1][j] ==1 && L[i][j-1] ==1 && L[i][j] != 1) {
    				L[i][j]=1;
    				
    				int[][] upgrade = flow(L, i, j);
    				
    				if(upgrade == null) 
    					L[i][j] = 0;
    				else 
    					L = upgrade;
    			}
    		}	 			
    	}
    	return L;
    }
    int max(int[][] L, int m, int n) {
    	
    	int v = L[0][0];
    	for(int i = 0; i<m; i++ ) {
    		for(int j = 0; j<n; j++ ) {
    			
    			if(L[i][j] > v)
    				v = L[i][j];
    		}
    	}
    	return v;
    }
    int sum(int[][] L, int m, int n) {
    	
    	int v = 0;
    	for(int i = 0; i<m; i++ ) {
    		for(int j = 0; j<n; j++ ) {
    				v += L[i][j];
    		}
    	}
    	return v;
    }
    int[][] flow(int[][] L1,int i, int j) {
    	
    	if(i ==1 && L1[i-1][j] == 0)
    		return null;
    	if(j ==1 && L1[i][j-1] == 0)
    		return null;
    	if(i ==L1.length - 2 && L1[i+1][j] == 0)
    		return null;
    	if(j ==L1[0].length - 2 && L1[i][j+1] == 0)	
    		return null;
    	if(L1[i-1][j] == 1 && L1[i+1][j] == 1 && L1[i][j-1] == 1 && L1[i][j+1] == 1) {
    		return L1;
    	}
    	else {
    		int m = L1.length;
            int n = L1[0].length;
    		int[][] L = new int[m][n];
			for(int a = 0; a<m; a++ ) {
	    		for(int b = 0; b<n; b++ ) {
	    			L[a][b]=L1[a][b];
	    		}
	    	}
    		if(L[i-1][j] == 0 ) {
    			L[i-1][j] = 1;
    			L = flow(L, i-1,j);
    			if(L == null)
    				return null;
    		}
    		if(L[i+1][j] == 0 ) {
    			L[i+1][j] = 1;
    			L = flow(L, i+1,j);
    			if(L == null)
    				return null;
    		}
    		if(L[i][j-1] == 0 ) {
    			L[i][j-1] = 1;
    			L = flow(L, i,j-1);
    			if(L == null)
    				return null;
    		}
    		if(L[i][j+1] == 0 ) {
    			L[i][j+1] = 1;
    			L = flow(L, i,j+1);
    			if(L == null)
    				return null;
    		}
    	}
    	return L1;
    }
    /*
    void show(int[][] L,int m, int n) {
		for(int i = 0; i<m; i++ ) {
    		for(int j = 0; j<n; j++ ) 
    			System.out.print(L[i][j]);
    		System.out.println("");
    		}
	}
	*/
	
}
