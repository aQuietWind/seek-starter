local key = KEYS[1]
local version = tonumber(ARGV[1])

-- 判断当前列表长度
local secureKey = redis.call('ZREVRANGE', key,0,0)
if len >= max then
    redis.call('ZREMRANGEBYRANK', key,0,0)
end
-- 追加新token到尾部
redis.call('ZADD', key,score, token)
return true