import java.util.*;

class Solution {
    public int countDays(int days, int[][] meetings) {
        if (meetings.length == 0) {
            return days;
        }
        
        // Sort meetings based on the start day
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Merge overlapping meetings
        List<int[]> merged = new ArrayList<>();
        int[] current = meetings[0];
        merged.add(current);
        
        for (int[] meeting : meetings) {
            int currentEnd = current[1];
            int nextStart = meeting[0];
            int nextEnd = meeting[1];
            
            if (nextStart <= currentEnd + 1) { // Overlapping or adjacent
                current[1] = Math.max(currentEnd, nextEnd);
            } else {
                current = meeting;
                merged.add(current);
            }
        }
        
        // Calculate the total days covered by meetings
        int totalMeetingDays = 0;
        for (int[] interval : merged) {
            totalMeetingDays += interval[1] - interval[0] + 1;
        }
        
        // Calculate the available days without meetings
        return days - totalMeetingDays;
    }
}