CREATE TABLE amount_details (
    id VARCHAR(255) PRIMARY KEY,
    estimate_id VARCHAR(255),
    estimate_detail_id VARCHAR(255),
    type VARCHAR(255),
    amount DECIMAL,
    status VARCHAR(50),
    additional_details JSONB,
    FOREIGN KEY (estimate_detail_id) REFERENCES estimate_details(id),
    FOREIGN KEY (estimate_id) REFERENCES estimates(id)
);
