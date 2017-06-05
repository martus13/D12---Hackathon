start transaction;

use `Acme-Flights`;

revoke all privileges on `Acme-Flights`.* from 'acme-user'@'%';
revoke all privileges on `Acme-Flights`.* from 'acme-manager'@'%';

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

drop database if exists `Acme-Flights`;

commit;