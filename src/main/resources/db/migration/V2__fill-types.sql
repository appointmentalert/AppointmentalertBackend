INSERT INTO alert_type_entity (id, name)
VALUES ('7cde964a-6fe8-4622-9858-290fcbb840ca', 'Leipzig residence permit pickup')
ON DUPLICATE KEY UPDATE name = 'Leipzig residence permit pickup';

INSERT INTO alert_type_entity (id, name)
VALUES ('0dab0e08-d43b-440d-834b-cd186f2c3f07', 'Leipzig drivers licence pickup')
ON DUPLICATE KEY UPDATE name = 'Leipzig drivers licence pickup';
