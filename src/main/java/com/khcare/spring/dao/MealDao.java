package com.khcare.spring.dao;

import com.khcare.spring.dto.Meal;
import com.khcare.spring.mapper.MealMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
@Log4j2
public class MealDao {
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public MealDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<Map<String, Object>> mealList(Map<String, Object> pMap){
        List<Map<String,Object>> bList = null;
        bList = sqlSessionTemplate.selectList("mealList", pMap);

        log.info(bList);

        return bList;
    }

    public void mealInsert(List<Meal> meals) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        MealMapper mealMapper = sqlSession.getMapper(MealMapper.class);
        for (Meal meal : meals) {
            mealMapper.mealInsert(meal);
        }
        sqlSession.flushStatements();
        sqlSession.close();
    }

}
