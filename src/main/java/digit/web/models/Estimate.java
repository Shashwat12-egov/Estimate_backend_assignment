package digit.web.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

/**
 * Estimate
 */
@Validated
@jakarta.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2024-07-25T12:23:00.978193237+05:30[Asia/Kolkata]")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Estimate   {
        @JsonProperty("id")

          @Valid
                private String id = null;

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

        @JsonProperty("proposalDate")

          @Valid
                private BigDecimal proposalDate = null;

            /**
            * It stores the status of the estimate. 
            */
            public enum StatusEnum {
                        ACTIVE("ACTIVE"),
                        
                        INACTIVE("INACTIVE");
            
            private String value;
            
            StatusEnum(String value) {
            this.value = value;
            }
            
            @Override
            @JsonValue
            public String toString() {
            return String.valueOf(value);
            }
            
            @JsonCreator
            public static StatusEnum fromValue(String text) {
            for (StatusEnum b : StatusEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
            return b;
            }
            }
            return null;
            }
            }        @JsonProperty("status")

        @Size(min=2,max=64)         private StatusEnum status = null;

        @JsonProperty("wfStatus")

        @Size(min=2,max=64)         private String wfStatus = null;

        @JsonProperty("name")
          @NotNull

        @Pattern(regexp="^[A-Za-z0-9/ . _$@#]*$") @Size(min=2,max=64)         private String name = null;

        @JsonProperty("referenceNumber")

        @Pattern(regexp="^[A-Za-z0-9/ . _$@#]*$") @Size(min=2,max=64)         private String referenceNumber = null;

        @JsonProperty("description")

        @Pattern(regexp="^[A-Za-z0-9/ . _$@#]*$") @Size(min=2,max=240)         private String description = null;

        @JsonProperty("executingDepartment")

        @Size(min=2,max=64)         private String executingDepartment = null;

        @JsonProperty("address")
          @NotNull

          @Valid
                private Address address = null;

        @JsonProperty("totalEstimateAmount")

          @Valid
                private BigDecimal totalEstimateAmount = null;

        @JsonProperty("estimateDetails")
          @NotNull
          @Valid
                private List<EstimateDetail> estimateDetails = new ArrayList<>();

        @JsonProperty("auditDetails")

          @Valid
                private AuditDetails auditDetails = null;

        @JsonProperty("additionalDetails")

                private Object additionalDetails = null;


        public Estimate addEstimateDetailsItem(EstimateDetail estimateDetailsItem) {
        this.estimateDetails.add(estimateDetailsItem);
        return this;
        }

}
