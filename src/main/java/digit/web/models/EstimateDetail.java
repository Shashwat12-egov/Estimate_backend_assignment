package digit.web.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import digit.web.models.AmountDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

/**
 * EstimateDetail
 */
@Validated
@jakarta.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2024-07-25T12:23:00.978193237+05:30[Asia/Kolkata]")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstimateDetail   {
        @JsonProperty("id")

          @Valid
                private String id = null;

        @JsonProperty("estimateId")

            @Valid
                private String estimateId = null;

        @JsonProperty("sorId")

          @Valid
                private String sorId = null;

        @JsonProperty("name")

        @Size(min=2,max=128)         private String name = null;

        @JsonProperty("category")

                private String category = null;

        @JsonProperty("description")

        @Size(min=2,max=256)         private String description = null;

        @JsonProperty("unitRate")

          @Valid
                private BigDecimal unitRate = null;

        @JsonProperty("noOfunit")

          @Valid
                private BigDecimal noOfunit = null;

        @JsonProperty("uom")

                private String uom = null;

        @JsonProperty("uomValue")

          @Valid
                private BigDecimal uomValue = null;

        @JsonProperty("length")

          @Valid
                private BigDecimal length = null;

        @JsonProperty("width")

          @Valid
                private BigDecimal width = null;

        @JsonProperty("height")

          @Valid
                private BigDecimal height = null;

        @JsonProperty("quantity")

          @Valid
                private BigDecimal quantity = null;

        @JsonProperty("isDeduction")

                private Boolean isDeduction = null;

        @JsonProperty("amountDetail")
          @NotNull
          @Valid
                private List<AmountDetail> amountDetail = new ArrayList<>();

            /**
            * Flag to soft delete
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

                private StatusEnum status = null;

        @JsonProperty("prevLineitemId")

          @Valid
                private String prevLineitemId = null;

        @JsonProperty("additionalDetails")

                private Object additionalDetails = null;


        public EstimateDetail addAmountDetailItem(AmountDetail amountDetailItem) {
        this.amountDetail.add(amountDetailItem);
        return this;
        }

}
