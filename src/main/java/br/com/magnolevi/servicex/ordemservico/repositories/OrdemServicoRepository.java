package br.com.magnolevi.servicex.ordemservico.repositories;


import br.com.magnolevi.servicex.ordemservico.domain.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {

}
