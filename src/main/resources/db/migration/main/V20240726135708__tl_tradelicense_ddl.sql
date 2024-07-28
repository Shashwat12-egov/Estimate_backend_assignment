CREATE TABLE estimate_details (
    id VARCHAR(255) PRIMARY KEY,
    estimate_id VARCHAR(255),
    sor_id VARCHAR(36), -- Use VARCHAR(36) for UUID compatibility across databases
    name VARCHAR(255),
    category VARCHAR(255),
    description TEXT,
    unit_rate DECIMAL,
    no_of_unit DECIMAL,
    uom VARCHAR(255),
    uom_value DECIMAL,
    length DECIMAL,
    width DECIMAL,
    height DECIMAL,
    quantity DECIMAL,
    is_deduction BOOLEAN,
    status VARCHAR(50),
    prev_Lineitem_Id VARCHAR(255),
    additional_details JSONB,
    FOREIGN KEY (estimate_id) REFERENCES estimates(id)
);