/*
919 · Meeting Rooms II
Algorithms
Medium

Description
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

(0,8),(8,10) is not conflict at 8

Example
Example1

Input: intervals = [(0,30),(5,10),(15,20)]
Output: 2
Explanation:
We need two meeting rooms
room1: (0,30)
room2: (5,10),(15,20)
Example2

Input: intervals = [(2,7)]
Output: 1
Explanation: 
Only need one meeting room
*/


// Time Complexity:  O(nlogn)
// Space Complexity: O(n)

class Interval {
    int start, end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(
            canAttendMeetings(Arrays.asList(
                new Interval(0, 30), 
                new Interval(5, 10), 
                new Interval(15, 20), 
                new Interval(20, 35),
                new Interval(31, 34)
            ))
        );
    }
    public static int canAttendMeetings(List<Interval> intervals) {
        Collections.sort(intervals, (a, b)->a.start-b.start);
        Queue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(intervals.get(0).end);
        for(int i=1; i<intervals.size(); i++) {
            int nearEnd = minHeap.poll();
            if(intervals.get(i).start < nearEnd) {
                minHeap.add(intervals.get(i).end);
            }
            minHeap.add(Math.max(nearEnd, intervals.get(i).end));
        }
        return minHeap.size();
    }
}
