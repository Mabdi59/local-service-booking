-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER local_booking_owner
WITH PASSWORD 'localbooking';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO local_booking_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO local_booking_owner;

CREATE USER local_booking_appuser
WITH PASSWORD 'localbooking';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO local_booking_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO local_booking_appuser;
