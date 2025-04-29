1-Why is caching important in web applications?
What are the tradeoffs?

-Caching improves performance by serving data quickly from memory
instead of querying the database every time.
It reduces database load, speeds up user experience, and saves resources.
-Tradeoffs:
Data consistency issues: Cache might have outdated data if the database
changes but cache doesn't update immediately.
Memory usage: Redis stores data in RAM, which is more expensive and limited
compared to disk storage.
Complexity: Managing cache invalidation and updates adds extra complexity to your application

2-What problems might arise if we don’t properly manage our cache (e.g., when data is updated)?

Stale data: Users might see outdated information if the cache is not updated or
cleared after a database update.
Memory bloat: If old or unnecessary data is never cleared, Redis can get full
and performance might degrade.
Inconsistency: The cache and database might show different values,
leading to confusing bugs for users.

3-How do the different cache annotations work?

@Cacheable--->	If the data exists in cache, return it. If not, run the method, cache the result, and return it.
@CachePut---->  Always run the method and update the cache with the new result.
@CacheEvict-->  Remove a specific entry (or all entries) from the cache.

4-What would happen if we didn’t use a Serializable class with Redis?

Redis needs to serialize (convert into a byte stream) objects before storing them.
If the object is not serializable, you will get serialization errors, and the cache won't work correctly.
Also, the object cannot be stored or retrieved properly.

5-How might we implement more complex caching strategies (different TTLs for different types of data, etc.)?
Create multiple cache configurations with different expiration (TTL) settings.
Use custom cache names for different data types (e.g., short-lived-cache, long-lived-cache).
Conditional caching: Only cache objects that meet certain criteria.
Eviction policies: Set policies like LRU (Least Recently Used) to remove old cache entries automatically.