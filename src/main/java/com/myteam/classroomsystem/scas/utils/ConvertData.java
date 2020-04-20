package com.myteam.classroomsystem.scas.utils;

import com.myteam.classroomsystem.scas.Entity.Application_form;
import com.myteam.classroomsystem.scas.Entity.Application_form2;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;

@Component
@Scope(value = "prototype")
public class ConvertData {

    public Application_form2 conventToApplication_form2(Application_form application_form) {
        if (application_form == null) {
            return null;
        }
        Application_form2 application_form2 = new Application_form2();
        application_form2.setId(application_form.getId());
        application_form2.setSid(application_form.getSid());
        application_form2.setName(application_form.getName());
        application_form2.setPhone(application_form.getPhone());
        application_form2.setOrganization(application_form.getOrganization());
        application_form2.setMembers(application_form.getMembers());
        application_form2.setApplication_time(application_form.getApplicationtime());
        application_form2.setEnd_time(application_form.getEndtime());
        application_form2.setReason(application_form.getReason());
        application_form2.setCounselor_id(application_form.getCounselortid());
        application_form2.setCounselor(application_form.getCounselor());
        application_form2.setCounselor_deal_time(application_form.getCounselordealtime());
        application_form2.setDepartment_teacher_id(application_form.getDepartmentteachertid());
        application_form2.setDepartment_teacher(application_form.getDepartmentteacher());
        application_form2.setDepartment_teacher_deal_time(application_form.getDepartmentteacherdealtime());
        application_form2.setApprover_time(application_form.getApprovertime());
        application_form2.setNote(application_form.getNote());
        application_form2.setMultimedia(application_form.getMultimedia());
        application_form2.setClass_size(application_form.getClasssize());
        application_form2.setStatus(application_form.getStatus());
        return application_form2;
    }

    public Application_form convertToApplication_form(Application_form2 application_form2) {
        Application_form application_form = new Application_form();
        if (application_form == null) {
            return null;
        }
        application_form.setId(application_form2.getId());
        application_form.setSid(application_form2.getSid());
        application_form.setName(application_form2.getName());
        application_form.setPhone(application_form2.getPhone());
        application_form.setOrganization(application_form2.getOrganization());
        application_form.setMembers(application_form2.getMembers());
        application_form.setApplicationtime(application_form2.getApplication_time());
        application_form.setEndtime(application_form2.getEnd_time());
        application_form.setReason(application_form2.getReason());
        application_form.setCounselortid(application_form2.getCounselor_id());
        application_form.setCounselor(application_form2.getCounselor());
        application_form.setCounselordealtime(application_form2.getCounselor_deal_time());
        application_form.setDepartmentteachertid(application_form2.getDepartment_teacher_id());
        application_form.setDepartmentteacher(application_form2.getDepartment_teacher());
        application_form.setDepartmentteacherdealtime(application_form2.getDepartment_teacher_deal_time());
        application_form.setApprovertime(application_form2.getApplication_time());
        application_form.setNote(application_form2.getNote());
        application_form.setMultimedia(application_form2.getMultimedia());
        application_form.setClasssize(application_form2.getClass_size());
        application_form.setStatus(application_form2.getStatus());
        return application_form;
    }

    public static <T> T[] convertArray(Class<T> targetType, Object[] arrayObjects) {
        if (targetType == null) {
            return (T[]) arrayObjects;
        }
        if (arrayObjects == null) {
            return null;
        }
        T[] targetArray = (T[]) Array.newInstance(targetType, arrayObjects.length);
        try {
            System.arraycopy(arrayObjects, 0, targetArray, 0, arrayObjects.length);
        } catch (ArrayStoreException e) {
            e.printStackTrace();
        }
        return targetArray;
    }

}
