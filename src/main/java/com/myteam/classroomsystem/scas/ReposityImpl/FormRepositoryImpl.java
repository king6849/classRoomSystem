package com.myteam.classroomsystem.scas.ReposityImpl;

import com.myteam.classroomsystem.scas.Entity.Application_form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FormRepositoryImpl {

    private final int counselorLevel = 0;
    private final int department_teacherLevel = 1;
    private final int approverLevel = 2;
    private final int finishLevel = 3;

    @Autowired
    private MongoTemplate mongoTemplate;

    //辅导员，指导老师根据工号，姓名查询是否有待自己审核的申请表
    public List<Application_form> counselorFindByTidAndTname(String counselor_id, String counselor) {
        Criteria criteriaId = Criteria.where("counselor_id").is(counselor_id);
        Criteria criteriaName = Criteria.where("counselor").is(counselor);
        Criteria criteriaStatus = Criteria.where("status").is(counselorLevel);
        Criteria criteria = new Criteria();
        criteria.andOperator(criteriaId, criteriaName, criteriaStatus);
        Query query = Query.query(criteria);
        return mongoTemplate.find(query, Application_form.class);
    }

    //学生查询最近一次申请记录
    public List<Application_form> studentFindRecentFormBySid(String sid) {
        Query query = new Query();
        Criteria criteria = Criteria.where("sid").is(sid);
        query.addCriteria(criteria);
        query.limit(1);
        query.with(Sort.by(Sort.Order.desc("application_time")));
        List<Application_form> list = mongoTemplate.find(query, Application_form.class);
        return list;
    }


}
