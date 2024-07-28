package digit.web.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import org.egov.common.contract.response.ResponseInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

import java.util.ArrayList;

/**
 * Response for the Estimate _create, _update and _search api&#x27;s
 */
@Schema(description = "Response for the Estimate _create, _update and _search api's")
@Validated
@jakarta.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2024-07-25T12:23:00.978193237+05:30[Asia/Kolkata]")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstimateResponse   {
        @JsonProperty("ResponseInfo")
          @NotNull

          @Valid
                private ResponseInfo responseInfo = null;

        @JsonProperty("estimates")
          @NotNull
          @Valid
                private List<Estimate> estimates = new ArrayList<>();


        public EstimateResponse addEstimatesItem(Estimate estimatesItem) {
        this.estimates.add(estimatesItem);
        return this;
        }

}
