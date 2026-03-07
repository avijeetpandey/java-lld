## Flight Aggregation System

The Business Need:
A user searches for a flight from "NYC" to "LHR". You need to fetch prices from United, Delta, and Emirates.
The Technical Hurdles (What the Interviewer is Testing):
Network Latency: United might take 500ms, Delta might take 1.5s, and Emirates might be down and hang for 30 seconds.
Concurrency: If you query them sequentially, the user waits 0.5 + 1.5 + 30 = 32 seconds. You must query them concurrently.
Partial Success (Fault Tolerance): If Emirates is down, you must not fail the entire search. You must return the results from United and Delta and gracefully ignore the failure.
Timeouts: You cannot let a stuck thread block your server. You must enforce a strict SLA (Service Level Agreement)—e.g., "Wait a maximum of 2 seconds. Whatever we have by then, return to the user."