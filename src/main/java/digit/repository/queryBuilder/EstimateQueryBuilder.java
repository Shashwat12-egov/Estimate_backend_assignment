package digit.repository.queryBuilder;

import digit.web.models.EstimateSearchCriteria;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class EstimateQueryBuilder {
    private static String INITIAL_QUERY= " SELECT amdt.id as amount_id,* from (SELECT *,etd.id as estimate_details_id,etd.estimate_id as estimate_details_estimate_id FROM ( ";

    private static final String BASE_BTR_AND_ADDRESS_QUERY = " SELECT *,ad.id as address_id,est.id as main_estimate_id" ;

    private static final String FROM_TABLES = " FROM estimates est JOIN addresses ad ON est.id = ad.estimate_id ";

    private static String LAST_QUERY= " ) estad JOIN estimate_details etd ON estad.estimate_id=etd.estimate_id) final_table JOIN amount_details amdt ON final_table.estimate_details_id=amdt.estimate_detail_id ";

    private final String ORDERBY_CREATEDTIME = " ORDER BY final_table.createdtime DESC ";

    public String getEstimateSearchQuery(EstimateSearchCriteria criteria, List<Object> preparedStmtList){
        StringBuilder query = new StringBuilder(INITIAL_QUERY);
        query.append(BASE_BTR_AND_ADDRESS_QUERY);
        query.append(FROM_TABLES);

        if(!ObjectUtils.isEmpty(criteria.getTenantId())){
            System.out.println("queryBuilder");
            addClauseIfRequired(query, preparedStmtList);
            query.append(" est.tenant_id = ? ");
            preparedStmtList.add(criteria.getTenantId());
        }

        if(!CollectionUtils.isEmpty(criteria.getIds())){
            addClauseIfRequired(query, preparedStmtList);
            query.append(" est.id IN ( ").append(createQuery(criteria.getIds())).append(" ) ");
            addToPreparedStatement(preparedStmtList, criteria.getIds());
        }

        query.append(LAST_QUERY);

        // order birth registration applications based on their createdtime in latest first manner
        query.append(ORDERBY_CREATEDTIME);

        return query.toString();
    }

    private void addClauseIfRequired(StringBuilder query, List<Object> preparedStmtList){
        if(preparedStmtList.isEmpty()){
            query.append(" WHERE ");
        }else{
            query.append(" AND ");
        }
    }

    private String createQuery(List<String> ids) {
        StringBuilder builder = new StringBuilder();
        int length = ids.size();
        for (int i = 0; i < length; i++) {
            builder.append(" ?");
            if (i != length - 1)
                builder.append(",");
        }
        return builder.toString();
    }

    private void addToPreparedStatement(List<Object> preparedStmtList, List<String> ids) {
        ids.forEach(id -> {
            preparedStmtList.add(id);
        });
    }
}
