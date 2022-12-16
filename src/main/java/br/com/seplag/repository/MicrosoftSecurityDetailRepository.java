package br.com.seplag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.seplag.domain.MicrosoftSecurityDetail;

@Repository
public interface MicrosoftSecurityDetailRepository extends JpaRepository<MicrosoftSecurityDetail, String> {

}
