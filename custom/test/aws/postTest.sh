#!/bin/bash

if [ -z "${3}" ]; then
  host=localhost
else
  host=$3
fi


if [ -z "${2}" ]; then
  action=releaseRights
else
  action=$2
fi


  curl -X POST http://$host:8080/$action/aws -H 'Content-Type: application/json' -d @$1

echo ' ' 