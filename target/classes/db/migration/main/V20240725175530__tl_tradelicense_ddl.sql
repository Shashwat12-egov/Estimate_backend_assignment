CREATE TABLE estimates (
    id INT PRIMARY KEY,
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
    address_id INT,
    additional_details JSON
);

CREATE TABLE addresses (
    id INT PRIMARY KEY,
    estimate_id INT,
    tenant_id VARCHAR(255),
    door_no VARCHAR(255),
    latitude INT,
    longitude INT,
    address_number VARCHAR(255),
    type VARCHAR(255),
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    landmark VARCHAR(255),
    city VARCHAR(255),
    pincode VARCHAR(255),
    detail VARCHAR(255),
    building_name VARCHAR(255),
    street VARCHAR(255),
    boundary_code VARCHAR(255),
    boundary_name VARCHAR(255),
    boundary_label VARCHAR(255),
    boundary_latitude VARCHAR(255),
    boundary_longitude VARCHAR(255),
    boundary_children JSON,
    boundary_type VARCHAR(255),
    FOREIGN KEY (estimate_id) REFERENCES estimates(id)
);


CREATE TABLE estimate_details (
    id INT PRIMARY KEY,
    estimate_id INT,
    sor_id VARCHAR(36), -- Use VARCHAR(36) for UUID compatibility across databases
    name VARCHAR(255),
    category VARCHAR(255),
    description TEXT,
    unit_rate INT,
    no_of_unit INT,
    uom VARCHAR(255),
    uom_value INT,
    length INT,
    width INT,
    height INT,
    quantity INT,
    is_deduction BOOLEAN,
    status VARCHAR(50),
    additional_details JSON,
    FOREIGN KEY (estimate_id) REFERENCES estimates(id)
);

CREATE TABLE amount_details (
    id INT PRIMARY KEY,
    estimate_detail_id INT,
    type VARCHAR(255),
    amount INT,
    status VARCHAR(50),
    additional_details JSON,
    FOREIGN KEY (estimate_detail_id) REFERENCES estimate_details(id)
);

