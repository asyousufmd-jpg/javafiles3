import java.util.*;

public class Solution {
    private static class Group {
        int start;
        int length;

        Group(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }

    // Sparse Table for range maximum queries over pre-calculated merge lengths
    private static class SparseTable {
        private int[][] st;

        public SparseTable(int[] nums) {
            int n = nums.length;
            if (n == 0) return;
            int maxLog = 32 - Integer.numberOfLeadingZeros(n);
            st = new int[maxLog][n];

            for (int j = 0; j < n; j++) {
                st[0][j] = nums[j];
            }

            for (int i = 1; i < maxLog; i++) {
                for (int j = 0; j + (1 << i) <= n; j++) {
                    st[i][j] = Math.max(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
                }
            }
        }

        public int query(int l, int r) {
            if (l > r) return 0;
            int k = 31 - Integer.numberOfLeadingZeros(r - l + 1);
            return Math.max(st[k][l], st[k][r - (1 << k) + 1]);
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int ones = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones++;
            }
        }

        // Identify all 0-groups
        List<Group> zeroGroups = new ArrayList<>();
        int[] zeroGroupIndex = new int[n];
        Arrays.fill(zeroGroupIndex, -1);

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    zeroGroups.get(zeroGroups.size() - 1).length++;
                } else {
                    zeroGroups.add(new Group(i, 1));
                }
            }
            zeroGroupIndex[i] = zeroGroups.isEmpty() ? -1 : zeroGroups.size() - 1;
        }

        List<Integer> ans = new ArrayList<>();

        // If no zeros exist, maximum active sections is always equal to total ones
        if (zeroGroups.isEmpty()) {
            for (int i = 0; i < queries.length; i++) {
                ans.add(ones);
            }
            return ans;
        }

        // Combined lengths of adjacent zero groups
        int numGroups = zeroGroups.size();
        int[] zeroMergeLengths = new int[Math.max(0, numGroups - 1)];
        for (int i = 0; i < numGroups - 1; i++) {
            zeroMergeLengths[i] = zeroGroups.get(i).length + zeroGroups.get(i + 1).length;
        }

        SparseTable st = new SparseTable(zeroMergeLengths);

        for (int q = 0; q < queries.length; q++) {
            int l = queries[q][0];
            int r = queries[q][1];

            int leftVal = -1;
            if (zeroGroupIndex[l] != -1) {
                Group g = zeroGroups.get(zeroGroupIndex[l]);
                leftVal = g.length - (l - g.start);
            }

            int rightVal = -1;
            if (zeroGroupIndex[r] != -1) {
                Group g = zeroGroups.get(zeroGroupIndex[r]);
                rightVal = r - g.start + 1;
            }

            int startAdjIdx = zeroGroupIndex[l] + 1;
            int endAdjIdx = (s.charAt(r) == '1') ? zeroGroupIndex[r] : zeroGroupIndex[r] - 1;

            int activeSections = ones;

            // Case 1: l and r fall in the same or adjacent 0-groups
            if (s.charAt(l) == '0' && s.charAt(r) == '0' && zeroGroupIndex[l] + 1 == zeroGroupIndex[r]) {
                activeSections = Math.max(activeSections, ones + leftVal + rightVal);
            } else if (startAdjIdx <= endAdjIdx - 1) {
                // Case 2: Range query over internal fully contained adjacent zero group pairs
                activeSections = Math.max(activeSections, ones + st.query(startAdjIdx, endAdjIdx - 1));
            }

            // Case 3: Partial left segment combined with next full zero group
            if (s.charAt(l) == '0' && zeroGroupIndex[l] + 1 <= endAdjIdx) {
                activeSections = Math.max(activeSections, ones + leftVal + zeroGroups.get(zeroGroupIndex[l] + 1).length);
            }

            // Case 4: Partial right segment combined with previous full zero group
            if (s.charAt(r) == '0' && zeroGroupIndex[l] < zeroGroupIndex[r] - 1) {
                activeSections = Math.max(activeSections, ones + rightVal + zeroGroups.get(zeroGroupIndex[r] - 1).length);
            }

            ans.add(activeSections);
        }

        return ans;
    }
}