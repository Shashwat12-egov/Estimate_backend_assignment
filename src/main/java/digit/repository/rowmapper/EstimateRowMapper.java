package digit.repository.rowmapper;

import digit.web.models.*;
import digit.web.models.Address;
import org.apache.kafka.common.protocol.types.Field;
import org.egov.common.contract.models.AuditDetails;
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
                    Address address = Address.builder().id(rs.getString("address_id"))
                            .estimateId(rs.getString("main_estimate_id")).build();

                    return Estimate.builder().id(rs.getString("main_estimate_id"))
                            .tenantId(rs.getString("tenant_id"))
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