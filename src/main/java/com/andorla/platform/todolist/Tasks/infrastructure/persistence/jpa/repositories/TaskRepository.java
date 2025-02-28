package com.andorla.platform.todolist.Tasks.infrastructure.persistence.jpa.repositories;

import com.andorla.platform.todolist.Tasks.domain.model.aggregates.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsById(Long id);
    Optional<Task> findById(Long id);
}
