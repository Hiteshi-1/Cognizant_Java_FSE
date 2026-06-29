package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.repository.SkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for Skill operations.
 * Day 2 HO-6 – ManyToMany relationship between Employee and Skill
 */
@Service
public class SkillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillService.class);

    @Autowired
    private SkillRepository skillRepository;

    @Transactional
    public Skill get(int id) {
        LOGGER.info("Start get: id={}", id);
        return skillRepository.findById(id).get();
    }

    @Transactional
    public void save(Skill skill) {
        LOGGER.info("Start save");
        skillRepository.save(skill);
        LOGGER.info("End save");
    }
}
