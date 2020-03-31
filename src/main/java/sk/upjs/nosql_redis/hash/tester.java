package sk.upjs.nosql_redis.hash;

import sk.upjs.nosql_data_source.entity.SimpleStudent;
import sk.upjs.nosql_data_source.persist.DaoFactory;
import sk.upjs.nosql_data_source.persist.StudentDao;
import sk.upjs.nosql_redis.RedisFactory;

import java.util.List;

public class tester {
    public static void main(String[] args) {
        SimpleStudentDao redisDao = RedisFactory.INSTANCE.getSimpleStudentDao();
        StudentDao studentDao = DaoFactory.INSTANCE.getStudentDao();
        List<SimpleStudent> simpleStudents = studentDao.getSimpleStudents();
        redisDao.saveStudent(simpleStudents.get(0));
//        for (SimpleStudent s: simpleStudents
//             ) {
//            redisDao.saveStudent(s);
//        }

        System.out.println(redisDao.getAllStudents());
    }
}
