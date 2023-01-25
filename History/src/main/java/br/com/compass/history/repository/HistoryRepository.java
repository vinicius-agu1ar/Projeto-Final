package br.com.compass.history.repository;

import br.com.compass.history.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface HistoryRepository extends MongoRepository <History, String> {

    History findByHistoryDate(LocalDate historyDate);

    Page<History> findByOrderByHistoryDateDesc(Pageable pageable);
}
