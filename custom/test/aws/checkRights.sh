#!/bin/bash

if [ -z "${1}" ]; then
  host=localhost
else
  host=$3
fi



  curl "http://$host:8080/checkRightsAWS?appID=AD-2&imageID=ID-3&instanceType=t3.large&instances=1"

echo ' ' 