package digit.web.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Validated
@jakarta.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2024-07-25T12:23:00.978193237+05:30[Asia/Kolkata]")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstimateSearchCriteria {
    @JsonProperty("ids")

    @Size(max=50)         private List<String> ids = null;

    @JsonProperty("tenantId")
    @NotNull

    @Size(min=2,max=64)         private String tenantId = null;

    @JsonProperty("estimateNumber")

    @Size(min=2,max=64)         private String estimateNumber = null;

    @JsonProperty("revisionNumber")

    @Size(min=1,max=64)         private String revisionNumber = null;

    @JsonProperty("businessService")

    @Size(min=2,max=64)         private String businessService = null;

    @JsonProperty("projectId")
    @NotNull

    @Size(min=2,max=64)         private String projectId = null;

    public EstimateSearchCriteria addIdsItem(String idsItem) {
        if (this.ids == null) {
            this.ids = new ArrayList<>();
        }
        this.ids.add(idsItem);
        return this;
    }
}
