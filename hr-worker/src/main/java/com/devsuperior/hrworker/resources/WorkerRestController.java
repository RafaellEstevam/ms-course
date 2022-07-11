package com.devsuperior.hrworker.resources;

import com.devsuperior.hrworker.entities.Worker;
import com.devsuperior.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/workers")
public class WorkerRestController {

   private static Logger logger = LoggerFactory.getLogger(WorkerRestController.class);

   @Autowired
   private Environment env;
    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping
    public ResponseEntity<List<Worker>> retrieveAll() {
        List<Worker> workers = workerRepository.findAll();
        return ResponseEntity.ok(workers);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Worker> retrieveById(@PathVariable Long id) {

        System.out.println("Chegou!");

/*        try{
            Thread.sleep(3000L);
        }catch (Exception e){

        }*/

        logger.info("Port"+env.getProperty("local.server.port"));

        Optional<Worker> workerOptional = workerRepository.findById(id);

        if (workerOptional.isPresent()) {
            return ResponseEntity.ok(workerOptional.get());
        }

        return ResponseEntity.notFound().build();
    }
}
