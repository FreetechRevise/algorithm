package com.ywh.problem.leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 青蛙过河
 * [动态规划]
 * 
 * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。
 * 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
 * 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
 * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
 * 示例 1：
 *      输入：stones = [0,1,3,5,6,8,12,17]
 *      输出：true
 *      解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子,
 *      接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子,
 *      最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
 * 示例 2：
 *      输入：stones = [0,1,2,3,4,8,9,11]
 *      输出：false
 *      解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
 * 提示：
 *      2 <= stones.length <= 2000
 *      0 <= stones[i] <= 2^31 - 1
 *      stones[0] == 0
 * 
 * @author ywh
 * @since 4/27/2021
 */
public class LeetCode403 {

    /**
     * Time: O(n^2), Space: O(n^2)
     *
     * @param stones
     * @return
     */
    public boolean canCross(int[] stones) {

        // key 为当前石头的位置， value 是包含 jumpSize 的集合，每个 jumpSize 表示可以跳 jumpSize 的距离到达当前位置。
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int stone : stones) {
            map.put(stone, new HashSet<>());
        }
        // 位置 0 对应的 value 初始化为 {0}
        map.get(0).add(0);

        // 遍历每个位置的石头。
        for (int curPos : stones) {
            // 对于每个位置。遍历 value 中的每个 jumpSize。
            // 表示之前是走过 k 步到达 curPos 的，因此接下来只能走 k-1、k、k+1 步，看看能不能到达下一个位置。
            for (int k : map.get(curPos)) {

                // 判断跳 step 的距离（[k-1, k+1]）存在于 map 中，如果存在，则可以添加到 curPos+step 的位置集合。
                // 即可以从 curPos 的位置跳 step，到达 curPos+ step。
                for (int step = k - 1; step <= k + 1; step++) {
                    if (step > 0 && map.containsKey(curPos + step)) {
                        map.get(curPos + step).add(step);
                    }
                }
            }
        }
        // 在结束时，如果最后一个位置的集合非空，表示可以通过前面的操作到达当前位置，否则不能。
        return map.get(stones[stones.length - 1]).size() > 0;
    }
}
