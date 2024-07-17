package com.mangoket.camunda.storage;

import com.mangoket.camunda.model.SourceService;
import com.mangoket.camunda.storage.entity.Process;

public interface ProcessStorageService {
    void createProcess(Long processId, String name, SourceService sourceService, String requester);

    Process getProcess();

}
