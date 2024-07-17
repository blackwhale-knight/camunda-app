package com.mangoket.camunda.storage;

import com.mangoket.camunda.model.SourceService;
import com.mangoket.camunda.storage.entity.Process;
import org.springframework.stereotype.Service;

@Service
public class ProcessStorageServiceImpl implements ProcessStorageService {
    @Override
    public void createProcess(Long processId, String name, SourceService sourceService, String requester) {

    }

    @Override
    public Process getProcess() {
        return null;
    }
}
