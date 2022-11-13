package com.cdemo.learnspringboot.config.redis;

/**
 * @description:
 * @author: star
 * @time: 2021/3/7 14:28
 */
//@Configuration
//@ImportResource(locations = {"classpath:spring-redis.xml"})
public class RedisSentinelConfig {
/*
    *//**
     * redis的连接池配置
     *//*
//    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    *//**
     * redis密码
     *//*
    @Value("${spring.redis.password}")
    private String password;
    *//**
     * 主节点名字
     *//*
    @Value("${spring.redis.sentinel.master}")
    private String master = "mymaster";
    *//**
     * 哨兵节点配置，数据格式 127.0.0.1:26379,127.0.0.1:26380
     *//*
    @Value("${spring.redis.sentinel.nodes}")
    private String sentinelNodes;

    *//**
     * redis哨兵连接池工厂
     * 创建条件：
     * 1、系统中没有任何RedisConnectionFactory对象
     * 2、需要存在JedisPoolConfig连接池配置时，才会创建此对象
     *
     * @return 连接池工厂
     *//*
    @Bean
    @ConditionalOnMissingBean(RedisConnectionFactory.class)
    @ConditionalOnClass(JedisPoolConfig.class)
    @Order(-1)
    public RedisConnectionFactory redisConnectionFactory() {
        // 装配哨兵节点配置文件
        if (!StringUtils.hasLength(sentinelNodes)) {
            throw new RuntimeException("哨兵服务ip&port:不能为空");
        }
        Set<String> sentinels = Sets.newHashSet(StringUtils.split(sentinelNodes, ","));
        // 哨兵专用配置类
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration(master, sentinels);
        // 设置redis密码
        sentinelConfig.setPassword(password);
        // 默认数据库为0
        sentinelConfig.setDatabase(0);
        return new JedisConnectionFactory(sentinelConfig, jedisPoolConfig);

    }

    *//**
     * 自定义的RedisTemplate,如果系统中没有RedisTemplate，则默认实现一个
     *
     * @param jedisConnectionFactory 缓存连接工厂类
     * @return
     *//*
    @Bean
    @ConditionalOnMissingBean(RedisTemplate.class)
    @Order(-1)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(jedisConnectionFactory);

        //使用FastJsonRedisSerializer 来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        // 值采用json序列化
        template.setValueSerializer(fastJsonRedisSerializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(fastJsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    *//**
     * 自定义Redis的连接池配置文件
     *
     * @param maxTotal                      jedis最大分配对象
     * @param maxIdle                       最大保存idel状态对象数
     * @param maxWaitMillis                 jedis池没有对象返回时，最大等待时间
     * @param testOnBorrow
     * @param testOnReturn
     * @param blockWhenExhausted            连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
     * @param testWhileIdle                 Idle时进行连接扫描
     * @param timeBetweenEvictionRunsMillis 表示idle object evitor两次扫描之间要sleep的毫秒数
     * @param numTestsPerEvictionRun        表示idle object evitor每次扫描的最多的对象数
     * @param minEvictableIdleTimeMillis    表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
     * @return redis连接池配置类
     *//*
    @Bean
    @ConditionalOnMissingBean(JedisPoolConfig.class)
    @Order(-1)
    public JedisPoolConfig jedisPoolConfig(@Value("${spring.redis.jedis.pool.maxTotal}") int maxTotal,
                                           @Value("${spring.redis.jedis.pool.maxIdle}") int maxIdle,
                                           @Value("${spring.redis.jedis.pool.maxWaitMillis}") int maxWaitMillis,
                                           @Value("${spring.redis.jedis.pool.testOnBorrow}") boolean testOnBorrow,
                                           @Value("${spring.redis.jedis.pool.testOnReturn}") boolean testOnReturn,
                                           @Value("${spring.redis.jedis.pool.blockWhenExhausted}") boolean blockWhenExhausted,
                                           @Value("${spring.redis.jedis.pool.testWhileIdle}") boolean testWhileIdle,
                                           @Value("${spring.redis.jedis.pool.timeBetweenEvictionRunsMillis}") long timeBetweenEvictionRunsMillis,
                                           @Value("${spring.redis.jedis.pool.numTestsPerEvictionRun}") int numTestsPerEvictionRun,
                                           @Value("${spring.redis.jedis.pool.minEvictableIdleTimeMillis}") long minEvictableIdleTimeMillis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setBlockWhenExhausted(blockWhenExhausted);
        config.setTestWhileIdle(testWhileIdle);
        config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        config.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);

        return config;
    }

    class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

        private Class<T> clazz;

        public FastJsonRedisSerializer(Class<T> clazz) {
            super();
            this.clazz = clazz;
        }

        @Override
        public byte[] serialize(T t) throws SerializationException {
            if (t == null) {
                return new byte[0];
            }
            return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
        }

        @Override
        public T deserialize(byte[] bytes) throws SerializationException {
            if (bytes == null || bytes.length <= 0) {
                return null;
            }
            String str = new String(bytes, DEFAULT_CHARSET);
            return JSON.parseObject(str, clazz);
        }
    }*/

}
