/*
920 · Meeting Rooms
Algorithms
Easy

Description
Given an array of meeting time intervals consisting of start and end times [(s1,e1),(s2,e2),...] (si < ei), determine if a person could attend all meetings.

0≤intervals.length≤10^4
intervals[i].length==2
0≤starti<endi<=10^6
0<=starti<endi<=10^6
[(0,8), (8,10)] is not conflict at 8

Example
Example1

Input: intervals = [(0,30),(5,10),(15,20)]
Output: false
Explanation: 
(0,30), (5,10) and (0,30),(15,20) will conflict
Example2

Input: intervals = [(5,8),(9,15)]
Output: true
Explanation: 
Two times will not conflict 
Tags
Company
Facebook
Uber
*/


// Time Complexity:  O(n)
// Space Complexity: O(1)

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
                new Interval(1, 5), 
                new Interval(6, 10), 
                new Interval(8, 15), 
                new Interval(20, 25)
            ))
        );
    }
    public static boolean canAttendMeetings(List<Interval> intervals) {
        Collections.sort(intervals, (a, b)->a.start-b.start);
        for(int i=1; i<intervals.size(); i++) {
        if(intervals.get(i).start < intervals.get(i-1).end)
            return false;
        }
        return true;
    }
}
