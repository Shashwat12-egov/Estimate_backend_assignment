package digit.web.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import digit.web.models.RequestInfo;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestInfoWrapper {

    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;

    @JsonProperty("estimateSearchCriteria")
    private EstimateSearchCriteria estimateSearchCriteria;

}