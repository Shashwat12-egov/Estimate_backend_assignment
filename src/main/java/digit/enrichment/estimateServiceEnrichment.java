package digit.enrichment;

import digit.web.models.AmountDetail;
import digit.web.models.Estimate;
import digit.web.models.EstimateDetail;
import digit.web.models.EstimateRequest;
import lombok.extern.slf4j.Slf4j;
import digit.web.models.AuditDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class estimateServiceEnrichment {
    public void enrichEstimateService(EstimateRequest estimateRequest) {
        estimateRequest.getEstimate().setId(UUID.randomUUID().toString());

        // Enrich audit details
        AuditDetails auditDetails = AuditDetails.builder().createdBy(estimateRequest.getRequestInfo().getUserInfo().getUuid()).createdTime(System.currentTimeMillis()).lastModifiedBy(estimateRequest.getRequestInfo().getUserInfo().getUuid()).lastModifiedTime(System.currentTimeMillis()).build();
        estimateRequest.getEstimate().setAuditDetails(auditDetails);

        estimateRequest.getEstimate().getAddress().setEstimateId(estimateRequest.getEstimate().getId());

        estimateRequest.getEstimate().getAddress().setId(UUID.randomUUID().toString());

        for (EstimateDetail estimateDetail : estimateRequest.getEstimate().getEstimateDetails()) {
            estimateDetail.setId(UUID.randomUUID().toString());
            estimateDetail.setEstimateId(estimateRequest.getEstimate().getId());

            for (AmountDetail amountDetail : estimateDetail.getAmountDetail()) {
                amountDetail.setId(UUID.randomUUID().toString());
                amountDetail.setEstimateId(estimateRequest.getEstimate().getId());
                amountDetail.setEstimateDetailId(estimateDetail.getId());
            }
        }
    }
}
