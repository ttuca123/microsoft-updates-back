package br.com.seplag.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.seplag.domain.MicrosoftSecurityDetail;
import br.com.seplag.repository.MicrosoftSecurityDetailRepository;
import br.com.seplag.services.MicrosoftUpdateService;
import br.com.seplag.services.rest.impl.MicrosoftUpdateServiceRestImpl;
import br.com.seplag.vo.ValueMicrosoftDetailVO;

/**
 * Classe responsável por gerenciar os dados de Microsoft Update
 * 
 * @author Artur
 * @since 14/12/2022 22:14
 *
 */
@Service
public class MicrosoftUpdateServiceImpl implements MicrosoftUpdateService {

	private static final Logger log = LoggerFactory.getLogger(MicrosoftUpdateServiceRestImpl.class);

	@Autowired
	public MicrosoftSecurityDetailRepository repo;

	@Autowired
	MicrosoftSecurityDetailRepository microsoftRepo;

	@PersistenceContext()
	EntityManager emService;

	private CriteriaBuilder criteriaBuilder;

	private Root<MicrosoftSecurityDetail> root;

	public Optional<List<ValueMicrosoftDetailVO>> getPatchs() {

		return Optional.of(repo.findAll().stream()
				.map(p -> new ValueMicrosoftDetailVO(p.getId(), p.getAlias(), p.getDocumentTitle(), p.getSeverity(),
						p.getInitialReleaseDate(), p.getCurrentReleaseDate(), p.getCvrfUrl(), p.getContexto()))
				.collect(Collectors.toList()));
	}

	@Override
	public Page<ValueMicrosoftDetailVO> filtrar(Pageable page, ValueMicrosoftDetailVO microsoftUpdateVO)
			throws Exception {

		criteriaBuilder = emService.getCriteriaBuilder();
		CriteriaQuery<MicrosoftSecurityDetail> query = criteriaBuilder.createQuery(MicrosoftSecurityDetail.class);
		root = query.from(MicrosoftSecurityDetail.class);
		Predicate predicados = adicionarFiltros(microsoftUpdateVO);
		query.select(root).where(predicados);
		long total = emService.createQuery(query).getResultStream().count();
		TypedQuery<MicrosoftSecurityDetail> resultado = emService.createQuery(query)
				.setFirstResult(page.getPageNumber() * page.getPageSize()).setMaxResults(page.getPageSize());

		return paginar(resultado.getResultList(), page, total);

	}

	public Page<ValueMicrosoftDetailVO> paginar(List<MicrosoftSecurityDetail> lista, Pageable page, Long total)
			throws Exception {

		try {
			Page<MicrosoftSecurityDetail> list = new PageImpl<MicrosoftSecurityDetail>(lista, page, total);

			List listaVO = list.getContent().stream().map(obj -> new ValueMicrosoftDetailVO(obj.getId(),
					obj.getAlias(), obj.getDocumentTitle(), obj.getSeverity(), obj.getInitialReleaseDate(),
					obj.getCurrentReleaseDate(), obj.getCvrfUrl(), obj.getContexto())).collect(Collectors.toList());
			
			Page<ValueMicrosoftDetailVO> pageVO = new PageImpl<ValueMicrosoftDetailVO>(listaVO, page, total);

			return pageVO;
		} catch (Exception e) {

			// TODO Auto-generated catch block			
			log.error(e.getMessage());
			throw new Exception(e.getMessage());
		}

	}

	private Predicate adicionarFiltros(ValueMicrosoftDetailVO dto) {

		Predicate predicados = criteriaBuilder.and();

		if (dto.getId() != null && !dto.getId().isEmpty()) {
			Path<String> id = root.get("id");
			Predicate predicado = criteriaBuilder.like(id, dto.getId());
			predicados = criteriaBuilder.and(predicados, predicado);
		}

		if (dto.getAlias() != null && !dto.getAlias().isEmpty()) {
			Path<String> alias = root.get("alias");
			Predicate predicado = criteriaBuilder.like(alias, "%" + dto.getAlias() + "%");
			predicados = criteriaBuilder.and(predicados, predicado);
		}

		if (dto.getContexto() != null && !dto.getContexto().isEmpty()) {
			Path<String> contexto = root.get("contexto");
			Predicate predicado = criteriaBuilder.like(contexto, dto.getContexto());
			predicados = criteriaBuilder.and(predicados, predicado);
		}

		if (dto.getCvrfUrl() != null && !dto.getCvrfUrl().isEmpty()) {
			Path<String> cvrfUrl = root.get("cvrfUrl");
			Predicate predicado = criteriaBuilder.like(cvrfUrl, "%" +  dto.getCvrfUrl() + "%" );
			predicados = criteriaBuilder.and(predicados, predicado);
		}

		if (dto.getSeverity() != null && !dto.getSeverity().isEmpty()) {
			Path<String> severity = root.get("severity");
			Predicate predicado = criteriaBuilder.like(severity, dto.getSeverity());
			predicados = criteriaBuilder.and(predicados, predicado);
		}

		if (dto.getDocumentTitle() != null && !dto.getDocumentTitle().isEmpty()) {
			Path<String> documentTitle = root.get("documentTitle");
			Predicate predicado = criteriaBuilder.like(documentTitle, "%" + dto.getDocumentTitle() + "%");
			predicados = criteriaBuilder.and(predicados, predicado);
		}

		return predicados;
	}

	/**
	 * Método responsável por salvar a lista de atualizações de segurança Microsoft
	 * 
	 * @param lista
	 */
	@Transactional
	public void save(List<ValueMicrosoftDetailVO> lista, String titulo) {

		try {

			List<MicrosoftSecurityDetail> lista1 = lista.stream()
					.map(p -> new MicrosoftSecurityDetail(p.getId(), p.getAlias(), p.getDocumentTitle(),
							p.getSeverity(), p.getInitialReleaseDate(), p.getCurrentReleaseDate(), p.getCvrfUrl(),
							titulo))
					.collect(Collectors.toList());

			microsoftRepo.saveAll(lista1);

		} catch (Exception e) {

			log.error(e.getMessage());
		}
	}

	@Override
	public void getUpdates() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getUpdates(String key) {
		// TODO Auto-generated method stub

	}

}
