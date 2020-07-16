#!/bin/bash
clients=(curl wget PowerShell Postman SoapUI Fiddler)

while true
do
  # Generate a random IP address
  ipaddr=$(printf "%d.%d.%d.%d\n" "$((RANDOM % 256))" "$((RANDOM % 256))" "$((RANDOM % 256))" "$((RANDOM % 256))")
  # Select a random client from the list
  client=${clients[$RANDOM % ${#clients[@]}]}

  curl -X POST -F 'client='"$client" -F 'ip='"$ipaddr" http://localhost:9000/kafka/publish
  echo "$(date +"%Y-%m-%d %H:%M:%S,%3N") $client|$ipaddr"
  sleep 0.1
done
