#!/usr/bin/env bash
# wait-for-it.sh from https://github.com/vishnubob/wait-for-it

host="$1"
shift
port="$1"
shift

timeout=30
start_ts=$(date +%s)

until nc -z "$host" "$port"; do
  now_ts=$(date +%s)
  if (( now_ts - start_ts > timeout )); then
    echo "Timeout waiting for $host:$port"
    exit 1
  fi
  echo "Waiting for $host:$port..."
  sleep 2
done

exec "$@"
