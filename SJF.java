package SJF;

import java.util.ArrayList;
import java.util.Scanner;

// Shortest Job First: non preemptive version of SRTF
public class SJF {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		System.out.println("Enter number of processes: ");
		int numOfProcesses = s.nextInt();

		int[] arrivalTime = new int[numOfProcesses];
		int[] burstTime = new int[numOfProcesses];
		int[] completionTime = new int[numOfProcesses];
		int[] waitingTime = new int[numOfProcesses];
		int[] turnaroundTime = new int[numOfProcesses];
		int[] remainingTime = new int[numOfProcesses];
		ArrayList<Integer> processNumberTimeline = new ArrayList<>(); 


		int completedProcesses = 0;
		int totalWaitingTime = 0;
		int totalTurnaroundTime = 0;
		int currentTime = 0;

		System.out.println("Enter arrival time and burst time");
		for (int i = 0; i < numOfProcesses; i++) {
			System.out.println("Process " + (i + 1) + " : ");
			arrivalTime[i] = s.nextInt();
			burstTime[i] = s.nextInt();
			remainingTime[i] = burstTime[i];
		}

		while (completedProcesses != numOfProcesses) {
			int shortestTime = Integer.MAX_VALUE;
			int shortestProcess = -1;

			for (int i = 0; i < numOfProcesses; i++) {
				if (arrivalTime[i] <= currentTime && remainingTime[i] < shortestTime && remainingTime[i] > 0) {
					shortestTime = remainingTime[i];
					shortestProcess = i;

				}
			}

			if (shortestProcess == -1) {
				currentTime++;
			} else {
				while (remainingTime[shortestProcess] > 0) {
					remainingTime[shortestProcess]--;
					currentTime++;
					processNumberTimeline.add(shortestProcess + 1);


					if (remainingTime[shortestProcess] == 0) {
						completedProcesses++;
						completionTime[shortestProcess] = currentTime;
						waitingTime[shortestProcess] = completionTime[shortestProcess] - arrivalTime[shortestProcess]
								- burstTime[shortestProcess];
						turnaroundTime[shortestProcess] = completionTime[shortestProcess]
								- arrivalTime[shortestProcess];
						totalWaitingTime += waitingTime[shortestProcess];
						totalTurnaroundTime += turnaroundTime[shortestProcess];

					}
				}
			}

		}

		System.out.println("Number of completed processes: " + completedProcesses);
		for (int i = 0; i < numOfProcesses; i++) {
			System.out.println("Completion time of process " + (i + 1) + " : " + completionTime[i]);
			System.out.println("Remaining time of process " + (i + 1) + " : " + remainingTime[i]);

		}
		System.out.println("Process Number Timeline: " + processNumberTimeline.toString());

	}

}