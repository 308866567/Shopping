public class Main {

    public static void main(String[] args) {

    }

}


 class LeetCode_162 {
    public int findPeakElement(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return nums[0]>nums[1]?nums[0]:nums[1];
        }
        int ans = nums[1];
        for(int i = 1; i < nums.length; i++){
            if (ans > nums[i-1] && ans > nums[i+1]){
                ans = nums[i];
            }else {
                ans = nums[i+1];
            }
        }
        return ans;
    }
}
