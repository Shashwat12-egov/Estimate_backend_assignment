package digit.repository;

import digit.repository.queryBuilder.EstimateQueryBuilder;
import digit.repository.rowmapper.EstimateRowMapper;
import digit.web.models.Estimate;
import digit.web.models.EstimateSearchCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EstimateRequestRepository {
    @Autowired
    private EstimateQueryBuilder queryBuilder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EstimateRowMapper rowMapper;

    public List<Estimate> getApplications(EstimateSearchCriteria searchCriteria){
        List<Object> preparedStmtList = new ArrayList<>();
        String query = queryBuilder.getEstimateSearchQuery(searchCriteria, preparedStmtList);
        log.info("Final query: " + query);
        return jdbcTemplate.query(query, preparedStmtList.toArray(), rowMapper);
    }
}
