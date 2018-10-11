package com.springbootstarter.repository;

import com.springbootstarter.bean.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository  extends CrudRepository<Topic,String>{

}
