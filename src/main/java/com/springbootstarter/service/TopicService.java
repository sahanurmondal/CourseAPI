package com.springbootstarter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.springbootstarter.bean.Topic;
import com.springbootstarter.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepository;
	
	public List<Topic> getAllTopics(){
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll()
		.forEach(topics::add);
		return topics;
	}
	
	public Topic getTopic(String id) {
		return topicRepository.findById(id).orElse(null);
	}
	
	public void addTopic(Topic topic) {
		topicRepository.save(topic);
	}

	public String updateTopic(Topic topic, String id) {
		String res="";
		if(!(topicRepository.findById(id).orElse(null)==null)) {
			topic.setId(id);
			topicRepository.save(topic);
			res="Topic "+ id+" has been updated successfully";
		}else
			res="Topic "+id +" Not Found";

		return res;
	}
	
	public void deleteTopic(String id) {
		topicRepository.deleteById(id);
	}
}
