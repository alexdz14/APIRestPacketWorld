package modelo.mybatis;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
    
    private static final String RESOURCE = "modelo/mybatis/mybatis-config.xml";
    private static final String ENVIROMENT = "desarrollo";
    private static SqlSessionFactory sessionFactory;
    
    public static SqlSessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try {
                Reader reader = Resources.getResourceAsReader(RESOURCE);
                sessionFactory = new SqlSessionFactoryBuilder().build(reader, ENVIROMENT);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return sessionFactory;
    }
}