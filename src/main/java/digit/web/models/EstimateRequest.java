package digit.web.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import digit.web.models.Estimate;
import digit.web.models.RequestInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

/**
 * Request for Estimate _create and _update api&#x27;s
 */
@Schema(description = "Request for Estimate _create and _update api's")
@Validated
@jakarta.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2024-07-25T12:23:00.978193237+05:30[Asia/Kolkata]")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstimateRequest   {
        @JsonProperty("RequestInfo")
          @NotNull

          @Valid
                private RequestInfo requestInfo = null;

        @JsonProperty("estimate")
          @NotNull

          @Valid
                private Estimate estimate = null;

        @JsonProperty("workflow")
          @NotNull

                private Object workflow = null;


}
