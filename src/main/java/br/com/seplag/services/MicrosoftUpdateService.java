package br.com.seplag.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.seplag.vo.ValueMicrosoftDetailVO;

public interface MicrosoftUpdateService {

	public Optional<List<ValueMicrosoftDetailVO>> getPatchs();

	public void save(List<ValueMicrosoftDetailVO> lista, String titulo);	

	public Page<ValueMicrosoftDetailVO> filtrar(Pageable page, ValueMicrosoftDetailVO filtro) throws Exception;

}
