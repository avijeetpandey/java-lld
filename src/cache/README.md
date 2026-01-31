## LLD of cache implementation

### What is a cache ?
A cache is a something which store some part of your data from the database in memory so that it can be accessed quickly.
In a tradition Cache these three operations are primarily needed
1. Read operation
2. Write operation
3. Evict operation or delte operation

When the items will be deleted from the cached are based on the eviction policies , some of the scenarios of the policies could be 
1. The RAM is full and a new entry is to be pushed , then the least unused value is deleted and then the new value is pushed
2. If a value is been sitting in long time into the cache and is expired as per TTL ( time to live ) , then it should be deleted from the cache


### Write policies in the cache
**Write back policy**
In the write back policy what happens is , if the value in the cache is updating very frequently and quickly the same will not be pushed to DB instantly , it will be hold onto and then will be pushed to DB after some interval maybe in order to avoid hammering the write into the DB , this is an efficient way but is risky as well , in case of data corruption or power failure lets say the only fresh copy which was there in RAM is now gone , so risky in that sense.

**Write through policy**
In a write thorough policy , whenever the value is changed into the cache the synchrounously the DB is also updated , and the fellow caches being notified.


### Requirements for the cache
1. Read your own writes
2. Multiple write policies - write back or write through
3. Expiration time
4. Multiple replacement algorithms - LRU , LFU
5. Async processing
6. Request collapsing
7. Hot reloading
8. Event logging