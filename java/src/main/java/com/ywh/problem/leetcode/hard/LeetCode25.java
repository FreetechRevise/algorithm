package com.ywh.problem.leetcode.hard;

import com.ywh.model.ListNode;

/**
 * K 个一组翻转链表
 * [链表]
 *
 * @author ywh
 * @since 2020/9/19 18:22
 */
public class LeetCode25 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        // dummy 表示 head 前的辅助节点，prev 表示待反转子链表的前驱节点， left、right 表示待反转子链表的左右边界（闭区间）。
        ListNode dummy = new ListNode(0, head), prev = dummy, left = head, right;

        // k = 3
        // dummy -> [1] -> [2] -> [3] -> [4] -> [5] -> [6] -> [7] -> null
        while (left != null) {

            // 检查从 tail 开始的剩余部分长度是否大于等于 k。
            //          |<-     k     ->|
            // dummy -> [1] -> [2] -> [3] -> ...
            //          right   =>    right
            right = prev;
            for (int i = 0; i < k; i++) {
                right = right.next;
                // 剩余部分不足 k，返回。
                if (right == null) {
                    return dummy.next;
                }
            }

            // 保存待反转子链表的后继节点。
            //          |<-     k     ->|
            // dummy -> [1] -> [2] -> [3] -> [4] -> [5]
            // prev    left          right   next
            ListNode next = right.next;

            // 反转长度为 k 的子链表。
            //          |<-     k     ->|
            // dummy -> [1] <- [2] <- [3] -> [4] -> [5]
            // prev    right          left   next
            ListNode[] group = reverseGroup(left, right);
            left = group[0];
            right = group[1];

            // 把反转后的子链表重新接回原链表。
            //                prev
            //                dummy ---+
            //                         ↓
            //          [1] <- [2] <- [3]    [4] -> [5]
            //          tail          left   next
            prev.next = left;

            //           +--------------------+
            //           |    dummy ---+      |
            //           |             ↓      ↓
            //          [1] <- [2] <- [3]    [4] -> [5]
            //         right          cur    next
            right.next = next;

            // 移动 prev 和 head。
            //           +--------------------+
            //           |    dummy ---+      |
            //           |             ↓      ↓
            //          [1] <- [2] <- [3]    [4] -> [5]
            //          prev          cur    left
            prev = right;
            left = next;
        }

        return dummy.next;
    }

    /**
     * 反转 head 与 tail 之间的链表，返回以 tail 为头、head 为尾的链表数组（元组）。
     *
     * @param left
     * @param right
     * @return
     */
    public ListNode[] reverseGroup(ListNode left, ListNode right) {
        //     [ ] -> [1] -> [2] -> [3] -> [ ]
        //            cur                  prev
        ListNode cur = left, prev = right.next, next;
        while (prev != right) {

            //     [ ] -> [1] -> [2] -> [3] -> [ ]
            //            cur    next          prev

            //             +--------------------+
            //             |                    ↓
            //     [ ] -> [1]    [2] -> [3] -> [ ]
            //            cur    next          prev

            //             +--------------------+
            //             |                    ↓
            //     [ ] -> [1]    [2] -> [3] -> [ ]
            //            prev

            //             +--------------------+
            //             |                    ↓
            //     [ ] -> [1] <- [2]    [3] -> [ ]
            //            prev   cur    next   prev

            //             +--------------------+
            //             |                    ↓
            //     [ ] -> [1] <- [2]    [3] -> [ ]
            //                   prev   cur   next

            //             +--------------------+
            //             |                    ↓
            //     [ ] -> [1] <- [2] <- [3]    [ ]
            //                   prev   cur   next

            //             +--------------------+
            //             |                    ↓
            //     [ ] -> [1] <- [2] <- [3]    [ ]
            //                          prev   cur    (break)
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return new ListNode[]{right, left};
    }

}