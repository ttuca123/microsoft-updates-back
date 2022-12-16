package br.com.seplag.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.seplag.services.MicrosoftUpdateService;
import br.com.seplag.vo.ValueMicrosoftDetailVO;

/**
 * 
 * @author Artur
 * @since 16/12/2022
 * Classe responsável pelos endpoints de controle de Microsoft  Patchs
 *
 */
@Controller
@RequestMapping(path = "/v1/patchs")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MicrosoftUpdateController {

	@Autowired
	public MicrosoftUpdateService service;

	private static final Logger log = LoggerFactory.getLogger(MicrosoftUpdateController.class);

	private ObjectMapper mapper;

	/**
	 * @author Artur Cavalcante
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @param jsonData
	 * @return Lista filtrada
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/")
	public ResponseEntity<Page<ValueMicrosoftDetailVO>> filtrar(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "ID") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "filter") String jsonData) {

		mapper = new ObjectMapper();
		ValueMicrosoftDetailVO microsoftUpdateVO;
		Page<ValueMicrosoftDetailVO> paginaVO = null;

		try {

			microsoftUpdateVO = mapper.readValue(jsonData, ValueMicrosoftDetailVO.class);
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			paginaVO = service.filtrar(pageRequest, microsoftUpdateVO);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}

		return ResponseEntity.status(200).body(paginaVO);
	}

}
