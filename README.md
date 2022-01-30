## Configuring project

```docker run --name mysql -p 3306:3306 -p 33060:33060 -e MYSQL_DATABASE=`scheduleemaildb` -e MYSQL_ROOT_PASSWORD={your_password}} -d mysql```

# MySQL Configs:

```
mysql -uroot -p
use scheduleemaildb;

SET @@global.time_zone = '+3:00';

CREATE USER 'admindb'@ ''172.17.0.1' IDENTIFIED BY123456';

GRANT ALL PRIVILEGES ON *.* TO 'admindb'@'172.17.0.1' WITH GRANT OPTION;

flush privileges;
````

# Adding Module Mysql:
module add --name=com.mysql --resources="{path_to_project}\jakartaee-update\connector-mysql\mysql-connector-java-8.0.20.jar" --dependencies=javax.api,javax.transaction.api

# Configuring a the JDBC driver for MySQL
/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.cj.jdbc.MysqlXADataSource)

# Put the datasource below in your standalone.xml inside the **<subsystem xmlns="urn:jboss:domain:datasources:6.0">**

```
<datasource jndi-name="java:/SchedulerDS" pool-name="SchedulerDS">
    <connection-url>jdbc:mysql://localhost:3306/scheduleemaildb</connection-url>
    <driver-class>com.mysql.cj.jdbc.Driver</driver-class>
    <driver>mysql</driver>
    <security>
        <user-name>admindb</user-name>
        <password>123456</password>
    </security>
    <validation>
        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
        <validate-on-match>true</validate-on-match>
        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
    </validation>
</datasource>
```
# Add a new Queue

```jms-queue add --queue-address=EmailQueue --entries=java:/jms/Queue/EmailQueue```
# or put the config below into the standalone.xml inside the **<subsystem xmlns="urn:jboss:domain:messaging-activemq:13.0">**

```<jms-queue name="EmailQueue" entries="java:/jms/Queue/EmailQueue"/>```