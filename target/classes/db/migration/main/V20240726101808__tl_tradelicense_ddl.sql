CREATE TABLE estimates (
    id VARCHAR(255) PRIMARY KEY,
    tenant_id VARCHAR(255),
    revision_number VARCHAR(255),
    business_service VARCHAR(255),
    project_id VARCHAR(255),
    proposal_date BIGINT,
    status VARCHAR(50),
    wf_status VARCHAR(50),
    name VARCHAR(255),
    reference_number VARCHAR(255),
    description TEXT,
    executing_department VARCHAR(255),
    address_id INT
);
