package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.UnionFind;

import java.util.HashSet;
import java.util.Stack;

/**
 * 岛屿的最大面积
 * [DFS] [数组] [并查集]
 *
 * @author ywh
 * @since 2020/11/12/012
 */
public class LeetCode695 {

    /**
     * 四联通问题：已知二维平面上的一个点，求其上下左右四个方向的点。
     * <p>
     * Time: O(R*C), Space(R*C)
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;

        // 以邻接表降维存储图的节点和边的关系。
        HashSet<Integer>[] graph = new HashSet[row * col];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new HashSet<>();
        }

        // 四个方向。
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // 遍历图中所有顶点，把顶点转换为岛屿的二维坐标。
        for (int v = 0; v < graph.length; v++) {
            // 一维映射到二维：i => (i / col, i % col)
            int x = v / col, y = v % col;
            if (grid[x][y] == 0) {
                continue;
            }

            // 处理四个方向。
            for (int d = 0; d < 4; d++) {
                int nextX = x + dirs[d][0], nextY = y + dirs[d][1];
                if (nextX < 0 || nextX >= row || nextY < 0 || nextY >= col || grid[nextX][nextY] == 0) {
                    continue;
                }
                // 二维映射到一维：(x, y) => x * col + y

                // 合法的岛屿，连接。
                int next = nextX * col + nextY;
                graph[v].add(next);
                graph[next].add(v);
            }
        }
        boolean[] visited = new boolean[graph.length];

        int ret = 0;
        // 遍历图
        for (int v = 0; v < graph.length; v++) {
            // 转化为二维坐标，只关注陆地和未访问过的点。
            if (grid[v / col][v % col] == 0 || visited[v]) {
                continue;
            }

            // 对岛屿（一个联通分量）进行深度优先遍历。
            visited[v] = true;
            int area = 1;
            Stack<Integer> stack = new Stack<>();
            stack.push(v);
            while (!stack.empty()) {
                int cur = stack.pop();
                for (int w : graph[cur]) {
                    if (!visited[w]) {
                        area += 1;
                        stack.push(w);
                        visited[w] = true;
                    }
                }
            }
            ret = Math.max(ret, area);
//            private int dfs(int v, boolean[] visited, HashSet<Integer>[] graph) {
//                visited[v] = true;
//                int ret = 1;
//                for (int w : graph[v]) {
//                    if (!visited[w]) {
//                        ret += dfs(w, visited, graph);
//                    }
//                }
//                return ret;
//            }
//             ret = Math.max(ret, dfs(v, visited, graph));
        }
        return ret;
    }


    /**
     * Flood Fill 算法
     * 不转换，直接使用二维坐标。
     * <p>
     * Time: O(R*C), Space(R*C)
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        int ret = 0;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == 0 || visited[r][c]) {
                    continue;
                }
                visited[r][c] = true;
                int tmp = 1;
                Stack<int[]> stack = new Stack<>();
                stack.push(new int[]{r, c});
                while (!stack.empty()) {
                    int[] cur = stack.pop();
                    int x = cur[0], y = cur[1];
                    for (int d = 0; d < 4; d++) {
                        int nextX = x + dirs[d][0], nextY = y + dirs[d][1];
                        if (nextX < 0 || nextX >= row || nextY < 0 || nextY >= col || grid[nextX][nextY] == 0 || visited[nextX][nextY]) {
                            continue;
                        }
                        stack.push(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                        tmp += 1;
                    }
                }
                ret = Math.max(ret, tmp);
            }
        }
        return ret;
    }

    /**
     * @param grid
     * @return
     */
    public int maxAreaOfIsland3(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        int ret = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == 1) {
                    ret = Math.max(ret, dfs(r, c, grid));
                }
            }
        }
        return ret;
    }

    /**
     * @param x
     * @param y
     * @return
     */
    private int dfs(int x, int y, int[][] grid) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {
            return 0;
        }
        grid[x][y] = 0;
        int res = 1;
        res += dfs(x - 1, y, grid);
        res += dfs(x, y + 1, grid);
        res += dfs(x + 1, y, grid);
        res += dfs(x, y - 1, grid);
        return res;
    }

    /**
     * @param x
     * @param y
     * @param grid
     * @return
     */
    private int dfs2(int x, int y, int[][] grid) {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        grid[x][y] = 0;
        int res = 1;
        for (int d = 0; d < 4; d++) {
            int nextX = x + dirs[d][0], nextY = y + dirs[d][1];
            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length || grid[nextX][nextY] == 0) {
                continue;
            }
            res += dfs2(nextX, nextY, grid);
        }
        return res;
    }

    /**
     * 并查集解法
     * TODO 暂时未理解
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland4(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        UnionFind uf = new UnionFind(row * col);
        for (int v = 0; v < row * col; v++) {
            int x = v / col, y = v % col;
            if (grid[x][y] == 1) {
                for (int d = 0; d < 4; d++) {
                    int nextX = x + dirs[d][0], nextY = y + dirs[d][1];
                    if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length || grid[nextX][nextY] == 0) {
                        continue;
                    }
                    int next = nextX * col + nextY;
                    uf.unionElements(v, next);
                }
            }
        }

        int res = 0;
        for (int v = 0; v < row * col; v++) {
            int x = v / col, y = v % col;
            if (grid[x][y] == 1) {
                res = Math.max(res, uf.size(v));
            }
        }
        return res;
    }


    public void main(String[] args) {
    }

}
