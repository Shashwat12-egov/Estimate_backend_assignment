package digit.repository.rowmapper;

import digit.web.models.*;
import digit.web.models.Address;
import org.apache.kafka.common.protocol.types.Field;
import org.egov.common.contract.request.User;
import org.egov.common.contract.user.enums.AddressType;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class EstimateRowMapper implements ResultSetExtractor<List<Estimate>> {
    @Override
    public List<Estimate> extractData (ResultSet rs) throws SQLException {
        Map<String, Estimate> estimateMap = new LinkedHashMap<>();

        while (rs.next()) {
            String estimateId = rs.getString("main_estimate_id");

            Estimate estimate = estimateMap.computeIfAbsent(estimateId, id -> {
                try {
                    AuditDetails auditDetails=AuditDetails.builder()
                            .createdBy(rs.getString("createdby"))
                            .createdTime(rs.getLong("createdtime"))
                            .lastModifiedBy(rs.getString("lastmodifiedby"))
                            .lastModifiedTime(rs.getLong("lastmodifiedtime"))
                            .build();

                    Address address = Address.builder().id(rs.getString("address_id"))
                            .estimateId(rs.getString("main_estimate_id"))
                            .tenantId(rs.getString("tenant_id"))
                            .doorNo(rs.getString("door_no"))
                            .latitude(rs.getDouble("latitude"))
                            .longitude(rs.getDouble("longitude"))
                            .addressNumber(rs.getString("address_number"))
                            .addressLine1(rs.getString("address_line1"))
                            .addressLine2(rs.getString("address_line2"))
                            .landmark(rs.getString("landmark"))
                            .city(rs.getString("city"))
                            .pincode(rs.getString("pincode"))
                            .detail(rs.getString("detail"))
                            .buildingName(rs.getString("building_name"))
                            .street(rs.getString("street"))
                            .boundaryType(rs.getString("boundary_type"))
                            .build();

                    return Estimate.builder().id(rs.getString("main_estimate_id"))
                            .tenantId(rs.getString("tenant_id"))
                            .estimateNumber(rs.getString("estimate_number"))
                            .revisionNumber(rs.getString("revision_number"))
                            .businessService(rs.getString("business_service"))
                            .proposalDate(rs.getBigDecimal("proposal_date"))
                            .projectId(rs.getString("project_id"))
                            .status(Estimate.StatusEnum.valueOf(rs.getString("status")))
                            .wfStatus(rs.getString("wf_status"))
                            .name(rs.getString("name"))
                            .referenceNumber(rs.getString("reference_number"))
                            .description(rs.getString("description"))
                            .executingDepartment(rs.getString("executing_department"))
                            .totalEstimateAmount(rs.getBigDecimal("total_estimate_amount"))
                            .additionalDetails(rs.getObject("additional_details"))
                            .auditDetails(auditDetails)
                            .address(address)
                            .estimateDetails(new ArrayList<>())
                            .build();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            String estimate_detailsId = rs.getString("estimate_details_id");
            if (estimate_detailsId != null) {
                    EstimateDetail estimateDetail1=estimate.getEstimateDetails().stream()
                            .filter(f -> f.getId().equals(estimate_detailsId))
                            .findFirst().orElseGet(()-> {
                                try {
                                    EstimateDetail estimateDetail = EstimateDetail.builder().id(rs.getString("estimate_details_id"))
                                            .estimateId(rs.getString("main_estimate_id"))
                                            .sorId(rs.getString("sor_id"))
                                            .name(rs.getString("name"))
                                            .category(rs.getString("category"))
                                            .description(rs.getString("description"))
                                            .unitRate(rs.getBigDecimal("unit_rate"))
                                            .noOfunit(rs.getBigDecimal("no_of_unit"))
                                            .uom(rs.getString("uom"))
                                            .uomValue(rs.getBigDecimal("uom_value"))
                                            .length(rs.getBigDecimal("length"))
                                            .width(rs.getBigDecimal("width"))
                                            .height(rs.getBigDecimal("height"))
                                            .quantity(rs.getBigDecimal("quantity"))
                                            .isDeduction(rs.getBoolean("is_deduction"))
                                            .status(EstimateDetail.StatusEnum.valueOf(rs.getString("status")))
                                            .prevLineitemId(rs.getString("prev_lineitem_id"))
                                            .additionalDetails(rs.getObject("additional_details"))
                                            .amountDetail(new ArrayList<>())
                                            .build();
                                    estimate.getEstimateDetails().add(estimateDetail);
                                    return estimateDetail;
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            });

                String amountDetailId = rs.getString("estimate_detail_id");
                String amountId=rs.getString("amount_id");
                if (estimate_detailsId.equals(amountDetailId)) {
                    AmountDetail amountDetail = AmountDetail.builder().id(rs.getString("amount_id"))
                            .estimateId(rs.getString("main_estimate_id"))
                            .estimateDetailId(rs.getString("estimate_detail_id"))
                            .type(rs.getString("type"))
                            .amount(rs.getBigDecimal("amount"))
                            .status(AmountDetail.StatusEnum.valueOf(rs.getString("status")))
                            .additionalDetails(rs.getObject("additional_details"))
                            .build();

                    if (estimateDetail1.getAmountDetail().stream().noneMatch(d -> d.getId().equals(amountId))) {
                        estimateDetail1.getAmountDetail().add(amountDetail);
                    }
                }
            }
        }
        return new ArrayList<>(estimateMap.values());
    }
}