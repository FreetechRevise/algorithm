package com.ywh.problem.leetcode.medium;

import com.ywh.ds.graph.UGN;
import com.ywh.util.AssertUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 测试图的深拷贝
 * {@link LeetCode133}
 *
 * @author ywh
 * @since 27/11/2019
 */
@DisplayName("测试图的深拷贝")
public class LeetCode133Test {

    private static LeetCode133 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode133();
    }

    @Test
    @DisplayName("测试图的深拷贝")
    void testCloneGraph() {
        UGN n1 = new UGN(1), n2 = new UGN(2), n4 = new UGN(4), n8 = new UGN(8);
        n1.neighbors = Arrays.asList(n2, n4, n8);
        n2.neighbors = Arrays.asList(n1, n8);
        n4.neighbors = Arrays.asList(n1, n8);
        n8.neighbors = Arrays.asList(n1, n2, n4);
        UGN clone = solution.cloneGraph(n1);

        assertNotNull(clone);
        assertEquals(1, clone.val);
        List<Integer> cloneNeighbours = new ArrayList<>();
        for (UGN neighbor : clone.neighbors) {
            cloneNeighbours.add(neighbor.val);
        }
        AssertUtil.assertIntListEquals(Arrays.asList(2, 4, 8), cloneNeighbours);
    }
}
