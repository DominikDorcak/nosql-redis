package sk.upjs.nosql_redis.atomic;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import sk.upjs.nosql_redis.RedisFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelIncrement {

    public static void main(String[] args) {
        JedisConnectionFactory factory = RedisFactory.INSTANCE.jedisConnectionFactory();
        RedisAtomicInteger ai1 = new RedisAtomicInteger("myAI",factory);
        RedisAtomicInteger ai2 = new RedisAtomicInteger("myAI",factory);
        RedisAtomicInteger ai3 = new RedisAtomicInteger("myAI",factory);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new IncrementTask("prvy",ai1));
        service.execute(new IncrementTask("druhy",ai2));
        service.execute(new IncrementTask("treti",ai3));
        service.shutdown();
    }

    private static class IncrementTask implements Runnable {

        private String name;
        private RedisAtomicInteger ai;

        public IncrementTask(String name, RedisAtomicInteger ai) {
            this.name = name;
            this.ai = ai;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(name + " : " + ai.incrementAndGet());
            }
        }
    }
}
