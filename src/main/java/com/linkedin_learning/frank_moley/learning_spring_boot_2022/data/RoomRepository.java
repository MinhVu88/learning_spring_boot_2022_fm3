package com.linkedin_learning.frank_moley.learning_spring_boot_2022.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {}
