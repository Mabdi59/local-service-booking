-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'local_service_booking';

DROP local_service_booking;

DROP USER local_booking_owner;
DROP USER local_booking_appuser;
