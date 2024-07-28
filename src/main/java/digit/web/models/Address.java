package digit.web.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import digit.web.models.Boundary;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * Representation of a address. Indiavidual APIs may choose to extend from this using allOf if more details needed to be added in their case. 
 */
@Schema(description = "Representation of a address. Indiavidual APIs may choose to extend from this using allOf if more details needed to be added in their case. ")
@Validated
@jakarta.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2024-07-25T12:23:00.978193237+05:30[Asia/Kolkata]")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address   {
        @JsonProperty("id")

                private String id = null;

        @JsonProperty("tenantId")

                private String tenantId = null;

        @JsonProperty("estimateId")

                private String estimateId = null;


        @JsonProperty("doorNo")

                private String doorNo = null;

        @JsonProperty("latitude")

                private Double latitude = null;

        @JsonProperty("longitude")

                private Double longitude = null;

        @JsonProperty("addressNumber")

                private String addressNumber = null;

                /**
                * Gets or Sets type
                */
                public enum TypeEnum {
                            PERMANENT("PERMANENT"),
                            
                            CORRESPONDENCE("CORRESPONDENCE");
                
                private String value;
                
                TypeEnum(String value) {
                this.value = value;
                }
                
                @Override
                @JsonValue
                public String toString() {
                return String.valueOf(value);
                }
                
                @JsonCreator
                public static TypeEnum fromValue(String text) {
                for (TypeEnum b : TypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                return b;
                }
                }
                return null;
                }
                }        @JsonProperty("type")

                private List<TypeEnum> type = null;

        @JsonProperty("addressLine1")

                private String addressLine1 = null;

        @JsonProperty("addressLine2")

                private String addressLine2 = null;

        @JsonProperty("landmark")

                private String landmark = null;

        @JsonProperty("city")

                private String city = null;

        @JsonProperty("pincode")

                private String pincode = null;

        @JsonProperty("detail")

                private String detail = null;

        @JsonProperty("buildingName")

        @Size(min=2,max=64)         private String buildingName = null;

        @JsonProperty("street")

        @Size(min=2,max=64)         private String street = null;

        @JsonProperty("boundary")

          @Valid
                private Boundary boundary = null;

        @JsonProperty("boundaryType")

                private String boundaryType = null;


        public Address addTypeItem(TypeEnum typeItem) {
            if (this.type == null) {
            this.type = new ArrayList<>();
            }
        this.type.add(typeItem);
        return this;
        }

}
