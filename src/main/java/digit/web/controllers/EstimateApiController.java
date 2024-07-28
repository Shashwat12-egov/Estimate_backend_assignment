package digit.web.controllers;


import java.math.BigDecimal;

import digit.kafka.Producer;
import digit.services.EstimateService;
import digit.util.ResponseInfoFactory;
import digit.web.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.egov.common.contract.response.ResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.util.*;

    import jakarta.validation.constraints.*;
    import jakarta.validation.Valid;
    import jakarta.servlet.http.HttpServletRequest;
        import java.util.Optional;
@jakarta.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2024-07-25T12:23:00.978193237+05:30[Asia/Kolkata]")
@Controller
    @RequestMapping("")
    public class EstimateApiController{

        private final ObjectMapper objectMapper;

        private final HttpServletRequest request;

        private final EstimateService estimateService;

        @Autowired
        Producer producer;

        @Autowired
        private ResponseInfoFactory responseInfoFactory;

        @Autowired
        public EstimateApiController(ObjectMapper objectMapper, HttpServletRequest request, EstimateService estimateService) {
            this.objectMapper = objectMapper;
            this.request = request;
            this.estimateService = estimateService;
        }

                @RequestMapping(value="/estimate/v1/_create", method = RequestMethod.POST)
                public ResponseEntity<EstimateResponse> estimateV1CreatePost(@Parameter(in = ParameterIn.DEFAULT, description = "Request object to create estimate in the system", required=true, schema=@Schema()) @RequestBody EstimateRequest estimateRequest) {
                    Estimate applications = estimateService.registerBtRequest(estimateRequest);
                    List<Estimate> res=new ArrayList<>();
                    res.add(applications);

                    ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(estimateRequest.getRequestInfo(), true);
                    EstimateResponse response = EstimateResponse.builder().estimates(res).responseInfo(responseInfo).build();
                    return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
                }

                @RequestMapping(value="/estimate/v1/_search", method = RequestMethod.POST)
                public ResponseEntity<EstimateResponse> estimateV1SearchPost(@Parameter(in = ParameterIn.DEFAULT, description = "Request object to search estimate in the system", required = true, schema=@Schema())@Valid @RequestBody RequestInfoWrapper estimateSearchRequest) {
                    System.out.println("output"+ estimateSearchRequest.getEstimateSearchCriteria());

                    List<Estimate> applications = estimateService.searchBtApplications(estimateSearchRequest);
                    System.out.println(applications);
                    ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(estimateSearchRequest.getRequestInfo(), true);
                    EstimateResponse response = EstimateResponse.builder().estimates(applications).responseInfo(responseInfo).build();
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }

                @RequestMapping(value="/estimate/v1/_update", method = RequestMethod.POST)
                public ResponseEntity<EstimateResponse> estimateV1UpdatePost(@Parameter(in = ParameterIn.DEFAULT, description = "Request object to update estimate in the system", required=true, schema=@Schema()) @Valid @RequestBody EstimateRequest body) {
                        String accept = request.getHeader("Accept");
                            if (accept != null && accept.contains("application/json")) {
                            try {
                            return new ResponseEntity<EstimateResponse>(objectMapper.readValue("{  \"ResponseInfo\" : {    \"userInfo\" : {      \"mobileNumber\" : \"mobileNumber\",      \"roles\" : [ {        \"tenantId\" : \"tenantId\",        \"name\" : \"name\",        \"description\" : \"description\",        \"id\" : \"id\"      }, {        \"tenantId\" : \"tenantId\",        \"name\" : \"name\",        \"description\" : \"description\",        \"id\" : \"id\"      } ],      \"tenantId\" : \"tenantId\",      \"emailId\" : \"emailId\",      \"id\" : 6,      \"userName\" : \"userName\",      \"uuid\" : \"uuid\"    },    \"ver\" : \"ver\",    \"requesterId\" : \"requesterId\",    \"authToken\" : \"authToken\",    \"action\" : \"action\",    \"msgId\" : \"msgId\",    \"correlationId\" : \"correlationId\",    \"apiId\" : \"apiId\",    \"did\" : \"did\",    \"key\" : \"key\",    \"ts\" : 0  },  \"estimates\" : [ {    \"proposalDate\" : 1658222690000,    \"businessService\" : \"ESTIMATE, REVISION-ESTIMATE\",    \"executingDepartment\" : \"executingDepartment\",    \"address\" : {      \"boundary\" : {        \"code\" : \"code\",        \"materializedPath\" : \"materializedPath\",        \"children\" : [ null, null ],        \"latitude\" : \"latitude\",        \"name\" : \"name\",        \"label\" : \"label\",        \"longitude\" : \"longitude\"      },      \"pincode\" : \"pincode\",      \"city\" : \"city\",      \"latitude\" : 1.4658129805029452,      \"boundaryType\" : \"boundaryType\",      \"type\" : [ \"PERMANENT\", \"PERMANENT\" ],      \"addressId\" : \"addressId\",      \"buildingName\" : \"buildingName\",      \"street\" : \"street\",      \"tenantId\" : \"tenantId\",      \"addressNumber\" : \"addressNumber\",      \"addressLine1\" : \"addressLine1\",      \"addressLine2\" : \"addressLine2\",      \"doorNo\" : \"doorNo\",      \"detail\" : \"detail\",      \"landmark\" : \"landmark\",      \"longitude\" : 5.962133916683182    },    \"revisionNumber\" : \"EST/2022-23/010-001\",    \"estimateNumber\" : \"EST/2022-23/010\",    \"totalEstimateAmount\" : 5.637376656633329,    \"description\" : \"Construct new schools\",    \"additionalDetails\" : { },    \"referenceNumber\" : \"File-18430283\",    \"estimateDetails\" : [ {      \"quantity\" : 4.145608029883936,      \"prevLineitemId\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",      \"noOfunit\" : 7.061401241503109,      \"length\" : 9.301444243932576,      \"description\" : \"description\",      \"unitRate\" : 2.3021358869347655,      \"additionalDetails\" : { },      \"uom\" : \"Kilogram, meter, number\",      \"isDeduction\" : true,      \"name\" : \"name\",      \"width\" : 3.616076749251911,      \"amountDetail\" : [ {        \"amount\" : 7.386281948385884,        \"id\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",        \"type\" : \"Gst, cess, charge \",        \"additionalDetails\" : { },        \"status\" : \"ACTIVE\"      }, {        \"amount\" : 7.386281948385884,        \"id\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",        \"type\" : \"Gst, cess, charge \",        \"additionalDetails\" : { },        \"status\" : \"ACTIVE\"      } ],      \"sorId\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",      \"uomValue\" : 5,      \"id\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",      \"category\" : \"Overhead, SOR, non-SOR\",      \"height\" : 2.027123023002322,      \"status\" : \"ACTIVE\"    }, {      \"quantity\" : 4.145608029883936,      \"prevLineitemId\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",      \"noOfunit\" : 7.061401241503109,      \"length\" : 9.301444243932576,      \"description\" : \"description\",      \"unitRate\" : 2.3021358869347655,      \"additionalDetails\" : { },      \"uom\" : \"Kilogram, meter, number\",      \"isDeduction\" : true,      \"name\" : \"name\",      \"width\" : 3.616076749251911,      \"amountDetail\" : [ {        \"amount\" : 7.386281948385884,        \"id\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",        \"type\" : \"Gst, cess, charge \",        \"additionalDetails\" : { },        \"status\" : \"ACTIVE\"      }, {        \"amount\" : 7.386281948385884,        \"id\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",        \"type\" : \"Gst, cess, charge \",        \"additionalDetails\" : { },        \"status\" : \"ACTIVE\"      } ],      \"sorId\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",      \"uomValue\" : 5,      \"id\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",      \"category\" : \"Overhead, SOR, non-SOR\",      \"height\" : 2.027123023002322,      \"status\" : \"ACTIVE\"    } ],    \"auditDetails\" : {      \"lastModifiedTime\" : 1,      \"createdBy\" : \"createdBy\",      \"lastModifiedBy\" : \"lastModifiedBy\",      \"createdTime\" : 1    },    \"tenantId\" : \"pb.jalandhar,dwss\",    \"name\" : \"Construct new schools\",    \"id\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",    \"wfStatus\" : \"CREATED\",    \"projectId\" : \"projectId\",    \"status\" : \"ACTIVE\"  }, {    \"proposalDate\" : 1658222690000,    \"businessService\" : \"ESTIMATE, REVISION-ESTIMATE\",    \"executingDepartment\" : \"executingDepartment\",    \"address\" : {      \"boundary\" : {        \"code\" : \"code\",        \"materializedPath\" : \"materializedPath\",        \"children\" : [ null, null ],        \"latitude\" : \"latitude\",        \"name\" : \"name\",        \"label\" : \"label\",        \"longitude\" : \"longitude\"      },      \"pincode\" : \"pincode\",      \"city\" : \"city\",      \"latitude\" : 1.4658129805029452,      \"boundaryType\" : \"boundaryType\",      \"type\" : [ \"PERMANENT\", \"PERMANENT\" ],      \"addressId\" : \"addressId\",      \"buildingName\" : \"buildingName\",      \"street\" : \"street\",      \"tenantId\" : \"tenantId\",      \"addressNumber\" : \"addressNumber\",      \"addressLine1\" : \"addressLine1\",      \"addressLine2\" : \"addressLine2\",      \"doorNo\" : \"doorNo\",      \"detail\" : \"detail\",      \"landmark\" : \"landmark\",      \"longitude\" : 5.962133916683182    },    \"revisionNumber\" : \"EST/2022-23/010-001\",    \"estimateNumber\" : \"EST/2022-23/010\",    \"totalEstimateAmount\" : 5.637376656633329,    \"description\" : \"Construct new schools\",    \"additionalDetails\" : { },    \"referenceNumber\" : \"File-18430283\",    \"estimateDetails\" : [ {      \"quantity\" : 4.145608029883936,      \"prevLineitemId\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",      \"noOfunit\" : 7.061401241503109,      \"length\" : 9.301444243932576,      \"description\" : \"description\",      \"unitRate\" : 2.3021358869347655,      \"additionalDetails\" : { },      \"uom\" : \"Kilogram, meter, number\",      \"isDeduction\" : true,      \"name\" : \"name\",      \"width\" : 3.616076749251911,      \"amountDetail\" : [ {        \"amount\" : 7.386281948385884,        \"id\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",        \"type\" : \"Gst, cess, charge \",        \"additionalDetails\" : { },        \"status\" : \"ACTIVE\"      }, {        \"amount\" : 7.386281948385884,        \"id\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",        \"type\" : \"Gst, cess, charge \",        \"additionalDetails\" : { },        \"status\" : \"ACTIVE\"      } ],      \"sorId\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",      \"uomValue\" : 5,      \"id\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",      \"category\" : \"Overhead, SOR, non-SOR\",      \"height\" : 2.027123023002322,      \"status\" : \"ACTIVE\"    }, {      \"quantity\" : 4.145608029883936,      \"prevLineitemId\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",      \"noOfunit\" : 7.061401241503109,      \"length\" : 9.301444243932576,      \"description\" : \"description\",      \"unitRate\" : 2.3021358869347655,      \"additionalDetails\" : { },      \"uom\" : \"Kilogram, meter, number\",      \"isDeduction\" : true,      \"name\" : \"name\",      \"width\" : 3.616076749251911,      \"amountDetail\" : [ {        \"amount\" : 7.386281948385884,        \"id\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",        \"type\" : \"Gst, cess, charge \",        \"additionalDetails\" : { },        \"status\" : \"ACTIVE\"      }, {        \"amount\" : 7.386281948385884,        \"id\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",        \"type\" : \"Gst, cess, charge \",        \"additionalDetails\" : { },        \"status\" : \"ACTIVE\"      } ],      \"sorId\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",      \"uomValue\" : 5,      \"id\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",      \"category\" : \"Overhead, SOR, non-SOR\",      \"height\" : 2.027123023002322,      \"status\" : \"ACTIVE\"    } ],    \"auditDetails\" : {      \"lastModifiedTime\" : 1,      \"createdBy\" : \"createdBy\",      \"lastModifiedBy\" : \"lastModifiedBy\",      \"createdTime\" : 1    },    \"tenantId\" : \"pb.jalandhar,dwss\",    \"name\" : \"Construct new schools\",    \"id\" : \"251c51eb-e970-4e01-a99a-70136c47a934\",    \"wfStatus\" : \"CREATED\",    \"projectId\" : \"projectId\",    \"status\" : \"ACTIVE\"  } ]}", EstimateResponse.class), HttpStatus.NOT_IMPLEMENTED);
                            } catch (IOException e) {
                            return new ResponseEntity<EstimateResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
                            }
                            }

                        return new ResponseEntity<EstimateResponse>(HttpStatus.NOT_IMPLEMENTED);
                }

        }
