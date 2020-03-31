package sk.upjs.nosql_redis.hash;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import sk.upjs.nosql_data_source.entity.SimpleStudent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleStudentDao {
    private static final String KEY = "SimpleStudentDD";
    private HashOperations<String,Long, SimpleStudent> hashOperations;
    private RedisTemplate<String,SimpleStudent> redisTemplate;

    public SimpleStudentDao(RedisTemplate<String, SimpleStudent> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    public void saveStudent(SimpleStudent student){
        hashOperations.put(KEY, student.getId(),student);
    }

    public SimpleStudent getStudentById(Long id) {
        return hashOperations.get(KEY,id);
    }

    public Map<Long,SimpleStudent> getAllStudentsMap(){
        return hashOperations.entries(KEY);
    }

    public List<SimpleStudent> getAllStudents() {
        return new ArrayList<>(getAllStudentsMap().values());
    }

    public void removeStudent(Long id){
        hashOperations.delete(KEY,id);
    }

    public  void removeAll(){
        redisTemplate.delete(KEY);
    }
}
