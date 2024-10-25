public class Main {
   static class Solution {
        int[] dp=new int[100006];
        int[] dp2=new int[100006];//起点
        public int maxSubarraySumCircular(int[] nums) {
            int n=nums.length;
            int maxx=nums[0];
            //破环成链
            //nums 0,n-1
            //dp 1,n
            int j=1;
            int last=0;
            for(int k=1;k<2*n;k++){
                int tt=last;
                while (dp2[tt]!=0&&k-dp2[tt]+1>=n){
                    tt++;
                }
                int num=nums[j-1];
                dp[j]=Math.max(num,dp[tt]+num);
                dp2[j]=dp[j]==num?k:dp2[tt];
                // j=n+1 dp[n] dp[n+1]
                // 1 dp[n] dp[1]
                System.out.println(dp[j]);
                maxx=Math.max(maxx,dp[j]);
                j++;
                last++;
                if(j>n){
                    j=1;
                }
                if(last>n){
                    last=1;
                }
            }
            return maxx;
        }
    }


    public static void main(String[] args) {
        int[] nums=new int[]{1,2,3};
        Solution s=new Solution();
        int t= s.maxSubarraySumCircular(nums);
        System.out.printf(String.valueOf(t));
    }


}