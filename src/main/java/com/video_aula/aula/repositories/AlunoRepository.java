package com.video_aula.aula.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.video_aula.aula.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
