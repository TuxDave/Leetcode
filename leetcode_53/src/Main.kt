fun main() {
    var nums = intArrayOf(-1)
    println(Solution().maxSubArray(nums))
    return
}

class Solution {
    fun maxSubArray(nums: IntArray): Int {
        var localMax = nums[0]
        var absoluteMax = nums[0]

        for(i in 1 ..< nums.size) {
            localMax = maxOf(nums[i], nums[i] + localMax)
            absoluteMax = maxOf(absoluteMax, localMax)
        }
        return absoluteMax
    }
}