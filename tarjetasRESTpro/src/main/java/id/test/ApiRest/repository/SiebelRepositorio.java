
package id.test.ApiRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.test.ApiRest.model.Siebel;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface SiebelRepositorio extends JpaRepository<Siebel, Long>
{
    
   @Query("SELECT t FROM siebels t WHERE t.tipo_documento=?1 AND t.documento=?2")
   List<Siebel> BuscarListaNegra(String tipodocumento, String documento);  
   
  
}

 