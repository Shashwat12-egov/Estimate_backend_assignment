package digit.services;

import digit.config.Configuration;
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
    private final Producer producer;

    private final  EstimateRequestRepository estimateRequestRepository;

    private final estimateServiceEnrichment enrichmentUtil;

    private Configuration config;

    EstimateService(Producer producer,EstimateRequestRepository estimateRequestRepository,estimateServiceEnrichment enrichmentUtil,Configuration config) {
        this.producer=producer;
        this.estimateRequestRepository=estimateRequestRepository;
        this.enrichmentUtil=enrichmentUtil;
        this.config=config;
    }

    public Estimate registerBtRequest(EstimateRequest estimateRequest) {
//      Enrich applications creates idgen
        enrichmentUtil.enrichEstimateService(estimateRequest);

        // Push the application to the topic for persister to listen and persist
        producer.push(config.getKafkacreatetopic(),estimateRequest);

        // Return the response back to user
        return estimateRequest.getEstimate();
    }

    public List<Estimate> searchBtApplications(RequestInfoWrapper estimateSearchRequest) {
        return estimateRequestRepository.getApplications(estimateSearchRequest.getEstimateSearchCriteria());
    }
}
