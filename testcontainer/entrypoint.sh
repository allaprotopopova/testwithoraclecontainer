#!/bin/bash
set -e


chown -R oracle:dba /u01/app/oracle
rm -f /u01/app/oracle/product
ln -s /u01/app/oracle-product /u01/app/oracle/product

#Run Oracle root scripts
/u01/app/oraInventory/orainstRoot.sh > /dev/null 2>&1
echo | /u01/app/oracle/product/12.2.0/EE/root.sh > /dev/null 2>&1 || true

		#Check for mounted database files
		if [ "$(ls -A /u01/app/oracle/oradata 2>/dev/null)" ]; then
			echo "found files in /u01/app/oracle/oradata Using them instead of initial database"
			echo "EE:$ORACLE_HOME:N" >> /etc/oratab
			chown oracle:dba /etc/oratab
			chown 664 /etc/oratab
			rm -rf /u01/app/oracle-product/12.2.0/EE/dbs
			ln -s /u01/app/oracle/dbs /u01/app/oracle-product/12.2.0/EE/dbs
			#Startup Database
			su oracle -c "/u01/app/oracle/product/12.2.0/EE/bin/tnslsnr &"
			su oracle -c 'echo startup\; | $ORACLE_HOME/bin/sqlplus -S / as sysdba'
		else
			echo "Database not initialized. Initializing database."

			if [ -z "$CHARACTER_SET" ]; then
				export CHARACTER_SET="AL32UTF8"
			fi

			#printf "Setting up:\nprocesses=$processes\nsessions=$sessions\ntransactions=$transactions\n"

			mv /u01/app/oracle-product/12.2.0/EE/dbs /u01/app/oracle/dbs
			ln -s /u01/app/oracle/dbs /u01/app/oracle-product/12.2.0/EE/dbs

			echo "Starting tnslsnr"
			su oracle -c "/u01/app/oracle/product/12.2.0/EE/bin/tnslsnr &"
			#create DB for SID: EE
			if [ "${MANUAL_DBCA}" == 'true' ]; then
				echo "Open in Browser http://localhost:6800/vnc_auto.html with password ${VNC_PASSWORD} for future configuration"
				su oracle -c "$ORACLE_HOME/bin/dbca"
			else
				su oracle -c "$ORACLE_HOME/bin/dbca -silent -createDatabase -templateName General_Purpose.dbc -gdbname EE.oracle.docker -sid EE -responseFile NO_VALUE -characterSet $CHARACTER_SET -totalMemory $DBCA_TOTAL_MEMORY -emConfiguration LOCAL -pdbAdminPassword oracle -sysPassword oracle -systemPassword oracle"
			fi
			
      #INMEMORY
      #su oracle -c 'echo ALTER SYSTEM SET inmemory_size=100M scope=SPFILE\; | $ORACLE_HOME/bin/sqlplus -S / as sysdba'
      #u oracle -c 'echo shutdown immediate\; | $ORACLE_HOME/bin/sqlplus -S / as sysdba'
      #su oracle -c 'echo startup\; | $ORACLE_HOME/bin/sqlplus -S / as sysdba'
      #su oracle -c 'echo SHOW PARAMETER INMEMORY\; | $ORACLE_HOME/bin/sqlplus -S / as sysdba'

		  echo CREATING SCHEMAS
      su -p oracle -c "$ORACLE_HOME/bin/sqlplus / as sysdba @/create_schemas.sql"
      su -p oracle -c "$ORACLE_HOME/bin/sqlplus personal/passw0rd @/schema_personal.sql"
      su -p oracle -c "$ORACLE_HOME/bin/sqlplus reports/passw0rd as sysdba @/schema_reports.sql"


		fi

		echo "Database ready to use. Enjoy! ;)"

if [ "$1" == "dockerbuild" ]; then
    su oracle -c 'echo shutdown immediate\; | $ORACLE_HOME/bin/sqlplus -S / as sysdba'
else
		##
		## Workaround for graceful shutdown.
		##
		while [ "$END" == '' ]; do
			sleep 1
			trap "su oracle -c 'echo shutdown immediate\; | $ORACLE_HOME/bin/sqlplus -S / as sysdba'" INT TERM
		done
fi
