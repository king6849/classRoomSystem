package com.myteam.classroomsystem.scas.Repository;

import com.myteam.classroomsystem.scas.Entity.Application_form;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormRepository extends MongoRepository<Application_form, String> {

    @Override
    public <S extends Application_form> S save(S form);

    @Override
    public Optional<Application_form> findById(String form_id);

    @Override
    public void deleteById(String form_id);

}
