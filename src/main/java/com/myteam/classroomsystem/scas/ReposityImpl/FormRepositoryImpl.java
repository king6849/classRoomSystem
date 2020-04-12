package com.myteam.classroomsystem.scas.ReposityImpl;

import com.myteam.classroomsystem.scas.Entity.Application_form;
import org.springframework.beans.factory.annotation.Autowired;
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

    //系部老师根据工号，姓名查询是否有待自己审核的申请表
    public List<Application_form> department_teacherFindByTidAndTname(String department_teacher_id, String department_teacher) {
        Criteria criteriaId = Criteria.where("department_teacher_id").is(department_teacher_id);
        Criteria criteriaName = Criteria.where("department_teacher").is(department_teacher);
        Criteria criteriaStatus = Criteria.where("status").is(department_teacherLevel);
        Criteria criteria = new Criteria();
        criteria.andOperator(criteriaId, criteriaName, criteriaStatus);
        Query query = Query.query(criteria);
        return mongoTemplate.find(query, Application_form.class);
    }

    //学生查询待审核的申请表
    public List<Application_form> studentFindFormBySidAndSname(String name, String sid) {
        Criteria criteriaId = Criteria.where("name").is(name);
        Criteria criteriaName = Criteria.where("sid").is(sid);
        Criteria criteriaStatus = Criteria.where("status").lt(finishLevel);
        Criteria criteria = new Criteria();
        criteria.andOperator(criteriaId, criteriaName, criteriaStatus);
        Query query = Query.query(criteria);
        return mongoTemplate.find(query, Application_form.class);
    }


}
