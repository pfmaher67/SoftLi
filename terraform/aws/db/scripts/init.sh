#!/bin/bash

docker run -i --rm mariadb:10.4.2  mysql -h$1 -udbusoftli -pdbusoftlipw < ./scripts/init.sql



