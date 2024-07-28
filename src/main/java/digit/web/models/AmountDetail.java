package digit.web.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

/**
 * AmountDetail
 */
@Validated
@jakarta.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2024-07-25T12:23:00.978193237+05:30[Asia/Kolkata]")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AmountDetail   {
        @JsonProperty("id")

          @Valid
                private String id = null;

        @JsonProperty("estimateId")

            @Valid
                private String estimateId = null;

        @JsonProperty("estimateDetailId")

            @Valid
                private String estimateDetailId = null;

        @JsonProperty("type")

                private String type = null;

        @JsonProperty("amount")
          @NotNull

          @Valid
                private BigDecimal amount = null;

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

        @JsonProperty("additionalDetails")

                private Object additionalDetails = null;


}
