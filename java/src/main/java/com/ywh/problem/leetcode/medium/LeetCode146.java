package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.DoublyListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现 LRU 缓存
 * [设计]
 *
 * @author ywh
 * @since 2019/10/28
 */
public class LeetCode146 {

    public static class LRUCache {

        // 使用双向链表（移动节点 O(1)）和哈希表（get、put 操作 O(1)）存储。
        // 头节点存放下次 put 操作时被淘汰的元素，其 next 表示最近刚被使用的，prev 表示 LRU。

        /**
         *  prev: <-
         *  next: ->
         *
         *         +----------------------------------+
         *         ↓                                  ↓
         *      [node0] <-> [node1] <-> [node2] <-> [node3]
         * （最近刚被使用的）                   （head，存放 LRU）
         */
        private DoublyListNode head;

        private final Map<Integer, DoublyListNode> map;

        /**
         * 辅助方法，把节点移动到头节点，表示最近刚被使用。
         *
         * @param cur
         */
        private void move2Head(DoublyListNode cur) {
            // 当前节点为头节点，头节点移动到其上一个节点。
            if (cur == head) {
                head = head.prev;
                return;
            }
            // detach
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;

            // attach
            cur.next = head.next;
            cur.next.prev = cur;

            head.next = cur;
            cur.prev = head;
        }

        /**
         * 构造方法，初始化链表。
         *
         * @param capacity
         */
        public LRUCache(int capacity) {
            head = new DoublyListNode(-1, -1, null, null);
            map = new HashMap<>();
            DoublyListNode cur = head;
            // 尾插法创建容量 -1 个节点（因为 head 本身也用于存放数据）。
            for (int i = 0; i < capacity - 1; i++) {
                cur.next = new DoublyListNode(-1, -1, null, cur);
                cur = cur.next;
            }
            cur.next = head;
            head.prev = cur;
        }

        /**
         * 获取元素：
         * 从哈希表中取出节点，并把该节点移动到链表头部，返回该节点的值。
         *
         * @param key
         * @return
         */
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            DoublyListNode cur = map.get(key);
            move2Head(cur);
            return cur.val;
        }

        /**
         * 添加元素
         *
         * @param key
         * @param value
         */
        public void put(int key, int value) {
            DoublyListNode cur;
            // 哈希表中已存在该 key，则取出该节点。
            if (map.containsKey(key)) {
                cur = map.get(key);
            }
            // 否则取头节点，并在哈希表中移除 key 对应的旧值。
            else {
                cur = head;
                map.remove(cur.key);
            }
            // 设值，添加到哈希表，并移动到头部。
            cur.key = key;
            cur.val = value;
            map.put(key, cur);
            move2Head(cur);
        }
    }
}
