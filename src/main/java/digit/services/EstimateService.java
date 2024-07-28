package digit.services;

import digit.enrichment.estimateServiceEnrichment;
import digit.kafka.Producer;
import digit.repository.EstimateRequestRepository;
import digit.web.models.Estimate;
import digit.web.models.EstimateRequest;
import digit.web.models.EstimateSearchCriteria;
import digit.web.models.RequestInfoWrapper;
import lombok.extern.slf4j.Slf4j;
import org.egov.common.contract.workflow.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class EstimateService {
    @Autowired
    Producer producer;

    @Autowired
    private EstimateRequestRepository estimateRequestRepository;


    @Autowired
    private estimateServiceEnrichment enrichmentUtil;

    public Estimate registerBtRequest(EstimateRequest estimateRequest) {
//      Enrich applications creates idgen
        enrichmentUtil.enrichEstimateService(estimateRequest);

        // Push the application to the topic for persister to listen and persist
        producer.push("save-est-application",estimateRequest);

        // Return the response back to user
        return estimateRequest.getEstimate();
    }

    public List<Estimate> searchBtApplications(RequestInfoWrapper estimateSearchRequest) {
        return estimateRequestRepository.getApplications(estimateSearchRequest.getEstimateSearchCriteria());
    }
}
