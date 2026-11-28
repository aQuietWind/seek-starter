local key = KEYS[1]

-- 判断当前列表长度,并且删除
local len = redis.call('zremrangebyrank', key,0,100)
return true