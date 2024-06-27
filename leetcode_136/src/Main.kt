fun singleNumber(nums: IntArray): Int {
    var n = nums.first()
    tailrec fun helper(nums: List<Int>) {
        if(nums.isEmpty()) {
            return
        }
        n = n xor nums.first()
        helper(nums.drop(1))
    }
    helper(nums.drop(1))
    return n
}