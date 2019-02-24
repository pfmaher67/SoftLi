#!/bin/bash

curl -X POST http://localhost:8080/releaseRights/std -H 'Content-Type: application/json' -d @$1

