#!/bin/bash

curl -X POST http://localhost:8080/releaseRights/aws -H 'Content-Type: application/json' -d @$1

