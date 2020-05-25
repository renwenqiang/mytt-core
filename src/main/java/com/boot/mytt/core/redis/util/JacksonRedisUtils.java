package com.boot.mytt.core.redis.util;

import com.boot.mytt.core.exception.SystemException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class JacksonRedisUtils {

    private RedisTemplate<String, Object> redisTemplate;

    public JacksonRedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取前缀相关的keys
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean expire(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        return true;
    }

    /**
     * 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean valueHasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public void valueDelete(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 删除缓存
     *
     * @param keys 集合对象
     */
    public void valueDelete(Collection<String> keys) {
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Object valueGet(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 插入缓存
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean valueSet(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    /**
     * 插入缓存并设置超时时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean valueSet(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            valueSet(key, value);
        }
        return true;
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return 值
     */
    public long valueIncrement(String key, long delta) {
        if (delta < 0) {
            throw new SystemException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return 值
     */
    public long valueDecrement(String key, long delta) {
        if (delta < 0) {
            throw new SystemException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * 获取hash item值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public <HK, HV> HV hashGet(String key, HK item) {
        HashOperations<String, HK, HV> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, item);
    }

    /**
     * 获取对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public <HK, HV> Map<HK, HV> hashEntries(String key) {
        HashOperations<String, HK, HV> operations = redisTemplate.opsForHash();
        return operations.entries(key);
    }

    /**
     * 插入hash值
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public <HK, HV> boolean hashPutAll(String key, Map<HK, HV> map) {
        redisTemplate.opsForHash().putAll(key, map);
        return true;
    }

    /**
     * 插入hash值并设置超时时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public <HK, HV> boolean hashPutAll(String key, Map<HK, HV> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public <HK, HV> boolean hashPut(String key, HK item, HV value) {
        redisTemplate.opsForHash().put(key, item, value);
        return true;
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public <HK, HV> boolean hashPut(String key, HK item, HV value, long time) {
        redisTemplate.opsForHash().put(key, item, value);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public <HK> void hashDelete(String key, HK... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public <HK> boolean hashHasKey(String key, HK item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return 值
     */
    public <HK> double hashIncrement(String key, HK item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return 值
     */
    public <HK> double hashDecrement(String key, HK item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return 值
     */
    public Set<Object> setMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean setIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long setAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long setAdd(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        if (time > 0) {
            expire(key, time);
        }
        return count;
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return 长度
     */
    public long setSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return 值
     */
    public List<Object> listRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 移除list
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return 值
     */
    public void listTrim(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return 长度
     */
    public long listSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return 值
     */
    public Object listIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return true:成功 false:失败
     */
    public boolean listRightPush(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
        return true;
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return true:成功 false:失败
     */
    public boolean listLeftPush(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
        return true;
    }

    /**
     * 将list放入缓存并设置超时时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean listRightPush(String key, Object value, long time) {
        redisTemplate.opsForList().rightPush(key, value);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    }

    /**
     * 将list放入缓存并设置超时时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean listLeftPush(String key, Object value, long time) {
        redisTemplate.opsForList().leftPush(key, value);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return true:成功 false:失败
     */
    public boolean listRightPushAll(String key, List<Object> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
        return true;
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return true:成功 false:失败
     */
    public boolean listLeftPushAll(String key, List<Object> value) {
        redisTemplate.opsForList().leftPushAll(key, value);
        return true;
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return true:成功 false:失败
     */
    public boolean listRightPushAll(String key, List<Object> value, long time) {
        redisTemplate.opsForList().rightPushAll(key, value);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return true:成功 false:失败
     */
    public boolean listLeftPushAll(String key, List<Object> value, long time) {
        redisTemplate.opsForList().leftPushAll(key, value);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    }

    /**
     * 获取list value
     *
     * @param key key
     * @return Object
     */
    public Object listRightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 获取list value
     *
     * @param key key
     * @return Object
     */
    public Object listLeftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 获取list value
     *
     * @param key     key
     * @param timeout 超时等待时间
     * @param unit    超时等待时间单位
     * @return Object
     */
    public Object listRightPop(String key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    /**
     * 获取list value
     *
     * @param key     key
     * @param timeout 超时等待时间
     * @param unit    超时等待时间单位
     * @return Object
     */
    public Object listLeftPop(String key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return true:成功 false:失败
     */
    public boolean listSet(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
        return true;
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */

    public long listRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }
}
