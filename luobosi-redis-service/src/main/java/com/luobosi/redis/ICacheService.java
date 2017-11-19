package com.luobosi.redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ICacheService
 *
 * @author luobosi@2dfire.com
 * @since 2017-11-17
 */
public interface ICacheService {
    /**
     * 回收redis连接到连接池
     *
     * @param redis
     */
    void returnResource(Jedis redis);

    /**
     * 取得redis的连接,使用完成之后 ,需要把连接返回连接池
     *
     * @return 返回 redis 连接
     */
    @Deprecated
    Jedis getResource();

    /**
     * 保存一个 Object 对象
     *
     * @param key           键
     * @param value         值
     * @param expireSecond 过期秒数
     */
    void setObject(String key, Object value, int expireSecond);

    /**
     * 通过键值获取一个对象
     *
     * @param key           键
     * @return              键对应的对象
     */
    Object getObject(String key);


    /**
     * 返回多个值
     *
     * @param keys         键值列表
     * @return             键对应的值
     */
    List<String> mget(String... keys);


    /**
     * 保存一个 string
     *
     * @param key           键
     * @param value         值
     * @param expireSecond 过期秒数
     */
    void set(String key, String value, int expireSecond);

    /**
     * 根据对应的键获取值
     *
     * @param key           键
     * @return              值
     */
    String get(String key);

    /**
     * 获取某个键的过期时间
     *
     * @param key 键值
     * @return  返回过期时间
     */
    long getTTL(String key);

    /**
     * 删除key
     *
     * @param key           键
     * @return              影响的纪录数
     */
    long del(String... key);

    /**
     * 获取存储在哈希表中指定字段的值。
     * 命令：HGET key field
     *
     * @param key           hash 表的键
     * @param field         hash 表里面的字段值
     * @return              string
     */
    String hget(String key, String field);


    /**
     * 将哈希表 key 中的字段 field 的值设为 value 。
     * 命令：HSET key field value
     * TODO：过期时间待定
     * @param key            hash 表 key
     * @param field          字段名
     * @param value          字段值
     * @param expireSecond   过期的值
     * @return               修改的纪录数
     */
    Long hset(String key, String field, String value, int expireSecond);

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。set hash map
     * 命令：HMSET key field1 value1 [field2 value2 ]
     *
     * @param key           hash表
     * @param map           要存储的键值对的值
     * @param expireSecond 过期秒数
     */
    void hmset(String key, Map<String, String> map, int expireSecond);


    /**
     * 删除一个或多个哈希表字段
     * 命令：HDEL key field1 [field2]
     *
     * @param key       hash 表的值
     * @param fields    要删除的字段名（可以有多个）
     * @return          返回删除影响行数
     */
    int hdel(String key, String... fields);

    /**
     * 获取在哈希表中指定 key 的所有字段和值。
     * 命令：HGETALL key
     *
     * @param key       hash 表值
     * @return          返回 hash map
     */
    Map<String, String> hgetAll(String key);

    /**
     * 获取所有给定字段的值 返回多个hash value
     * 命令：HMGET key field1 [field2]
     *
     * @param key       hash 表值
     * @param fields    给定的字段值
     * @return          结果 list
     */
    List<String> hmget(String key, String... fields);

    /**
     * 在list尾部插入数据
     *
     * @param key
     * @param length list的长度，如果超过，就会除去最早进入的元素
     * @param values
     */
    void rpushLength(String key, long length, String... values);

    /**
     * 在列表中添加一个或多个值（尾部）
     *
     * @param key
     * @param values
     * @param expireSecond 整个list过期
     */
    void rpush(String key, int expireSecond, String... values);

    /**
     * 在list尾部插入数据,没有有效期
     *
     * @param key
     * @param values
     */
    void rpush(String key, String... values);

    /**
     * 返回list里的所有数据
     *
     * @param key           list
     * @param start
     * @param end
     */
    List<String> lrange(String key, long start, long end);

    /**
     * 返回list里的长度
     *
     * @param key
     */
    long llen(String key);

    /**
     * 从存于 key 的列表里移除前 count 次出现的值为 value 的元素。
     * 这个 count 参数通过下面几种方式影响这个操作： count > 0: 从头往尾移除值为 value 的元素。 count < 0:
     * 从尾往头移除值为 value 的元素。 count = 0: 移除所有值为 value 的元素。
     *
     * @param key
     * @param count
     * @param value
     */
    long lrem(String key, int count, String value);

    /**
     * 将 key 中储存的数字值增一。(该操作是原子的)
     * 命令：INCR key
     *
     * @param key        键值
     * @return          返回加1后的值
     */
    long incr(String key);

    /**
     * 将 key 中储存的数字值减一。(该操作是原子的)
     *
     * @param key       键值
     * @return          返回减1后的值
     */
    long decr(String key);

    /**
     * 将 key 所储存的值加上给定的增量值（increment） 。
     * 命令：INCRBY key increment
     *
     * @param key       键值
     * @return          加上增量 increment 之后的值
     */
    long incrBy(String key, long increment);

    /**
     * key 所储存的值减去给定的减量值（decrement） 。
     * 命令：DECRBY key decrement
     *
     * @param key       键值
     * @return          减去减量 decrement 之后的值
     */
    long decrBy(String key, long decrement);

    /**
     * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。
     * 如果 key 不存在则相当于 set key value
     * 命令：APPEND key value
     *
     * @param key       键
     * @param value     要追加的值
     * @return          追加之后的值
     */
    String append(String key, String value);

    /**
     * 只有在 key 不存在时设置 key 的值
     * 命令：SETNX key value
     *
     * @param key           键
     * @param value         值
     * @param expireSecond  过期时间
     */
    Long setnx(String key, String value, int expireSecond);

    /******************************** Redis 有序集合(sorted set) **********************************/
    /***********    Redis 有序集合和集合一样也是string类型元素的集合,且不允许重复的成员。**********/
    /***********    不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为  **********/
    /***********    集合中的成员进行从小到大的排序。有序集合的成员是唯一的,但分数(score) **********/
    /***********    却可以重复。集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。*****/
    /***********    集合中最大的成员数为 232 - 1 (4294967295, 每个集合可存储40多亿个成员)。********/
    /**********************************************************************************************/

    /**
     * 向有序集合添加一个或多个成员，或者更新已存在成员的分数
     * 命令：ZADD key score1 member1 [score2 member2]
     *
     * @param key               键
     * @param score             关联的分数
     * @param value             值
     * @param expireSecond      过期时间
     * @return                  1-成功 0-不成功
     */
    int zadd(String key, double score, String value, int expireSecond);

    /**
     * 返回有序集中，成员的分数值
     * 命令：ZSCORE key member
     *
     * @param key           键
     * @param value         值
     * @return              score值
     */
    double zscore(String key, String value);

    /**
     * 移除有序集合中的一个或多个成员
     * 命令：ZREM key member [member ...]
     *
     * @param key           键
     * @param value         值
     * @return              1-成功 0-不成功
     */
    int zrem(String key, String value);

    /**
     * 返回有序集key中，指定区间内的成员
     *
     * @param key
     * @param start
     * @param end
     * @param isAsc 1:按score值递增(从小到大),2:按score值递增(从大到小)
     * @return List<String>
     */
    List<String> zrange(String key, long start, long end, int isAsc);

    /**
     * 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序
     * 命令：ZREVRANK key member
     *
     * @param key           键
     * @param member        值
     * @return              键值指定的排名
     */
    long zrank(String key, String member);

    /**
     * 获取有序集合的成员数
     *
     * @param key           集合
     * @return              集合中成员的个数
     */
    long zcard(String key);

    /**
     * 移除有序集合中给定的字典区间的所有成员
     *
     * @param key           键
     * @param start         起始值
     * @param stop          结束值
     * @return              删除的个数
     */
    long zremrangebyrank(String key, long start, long stop);

    /**
     * 获取哈希表中所有值
     * 命令：HVALS key
     *
     * @param key           hash 表
     * @return              所有的值
     */
    List<String> hvals(String key);

    /**
     * 移除并获取列表最后一个元素，并返回该值
     * 命令：	RPOP key
     *
     * @param key       键
     * @return          值
     */
    String rpop(String key);

    /**
     * 将一个或多个值插入到列表头部
     *
     * @param key           键
     * @param expireSecond  过期时间
     * @param values        值
     */
    void lpush(String key, int expireSecond, String... values);

    /**
     * 将一个或多个值插入到列表头部，没有有效期
     * 命令：LPUSH key value1 [value2]
     *
     * @param key           键
     * @param values        多个值
     */
    void lpush(String key, String... values);

    /**
     * 移出并获取列表的第一个元素，返回value
     *
     * @param key       键
     * @return          值
     */
    String lpop(String key);

    /***********************************  集合操作   **************************************/

    /**
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
     * 当 key 不是集合类型时，返回一个错误。
     * 命令：SADD key member1 [member2]
     *
     * @param key       集合名
     * @param values    值
     * @return          影响的行数
     */
    long sadd(String key, String... values);

    /**
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
     * 当 key 不是集合类型时，返回一个错误。
     *
     * @param key           集合名
     * @param expireSecond  过期时间
     * @param values        值
     * @return              影响的行数
     */
    long sadd(String key, int expireSecond, String... values);

    /**
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
     * 当 key 不是集合类型，返回一个错误。
     * 命令：SREM key member1 [member2]
     *
     * @param key       集合名
     * @param values    值
     * @return          影响的行数
     */
    long srem(String key, String... values);

    /**
     * 返回集合 key 中的所有成员。 不存在的 key 被视为空集合。
     *
     * @param key       集合名
     * @return          返回一个 set 包含集合里面所有的值
     */
    Set<String> smembers(String key);

    /**
     * 检查给定 key 是否存在。
     * 命令：EXISTS key
     *
     * @param key   指定的key
     * @return      是否存在
     */
    boolean exists(String key);

    /**
     * 为给定 key 设置过期时间。
     * 命令：EXPIRE key seconds
     *
     * @param key       键值
     * @param seconds   过期值
     * @return          影响的纪录数
     */
    int expire(String key, int seconds);
}